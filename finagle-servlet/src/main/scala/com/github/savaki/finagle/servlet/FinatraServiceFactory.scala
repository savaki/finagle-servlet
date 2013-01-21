package com.github.savaki.finagle.servlet

import com.twitter.finatra.{View, AppService, ControllerCollection, Controller}
import com.twitter.finagle.{SimpleFilter, Service}
import com.twitter.finagle.http.{Response, Request}
import javax.servlet.ServletContext

/**
 * @author matt.ho@gmail.com
 */
trait FinatraServiceFactory extends ServiceFactory {
  private[this] val controllers: ControllerCollection = new ControllerCollection
  private[this] var filters: Seq[SimpleFilter[Request, Response]] = Seq.empty

  /**
   * @return the resource path within the servlet container that contains where the views will be stored
   */
  def path: String

  /**
   * @param servletContext the current servletContext
   */
  def setServletContext(servletContext: ServletContext) {
    System.setProperty("local_docroot", servletContext.getRealPath("/"))
    View.baseTemplatePath = servletContext.getRealPath(path)
  }

  def addFilter(filter: SimpleFilter[Request, Response]) {
    filters = filters ++ Seq(filter)
  }

  def register(controller: Controller): FinatraServiceFactory = {
    controllers.add(controller)
    this
  }

  def allFilters(baseService: Service[Request, Response]): Service[Request, Response] = {
    filters.foldRight(baseService) {
      (b, a) => b andThen a
    }
  }

  def build: Service[Request, Response] = {
    val service: AppService = new AppService(controllers)
    allFilters(service)
  }
}

