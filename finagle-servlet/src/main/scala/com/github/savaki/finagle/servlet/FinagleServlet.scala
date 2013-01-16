package com.github.savaki.finagle.servlet

import javax.servlet.http.HttpServlet
import com.twitter.finagle.Service
import org.jboss.netty.handler.codec.http.{HttpResponse, HttpRequest}
import javax.servlet.ServletConfig

/**
 * @author matt.ho@gmail.com
 */
trait FinagleServlet {
  self: HttpServlet =>

  var finagleService: Service[HttpRequest, HttpResponse] = null

  override def init(config: ServletConfig) {
    val className: String = config.getInitParameter("service.factory.classname")
    val factory: ServiceFactory = Class.forName(className).newInstance().asInstanceOf[ServiceFactory]
    finagleService = factory.build
  }
}
