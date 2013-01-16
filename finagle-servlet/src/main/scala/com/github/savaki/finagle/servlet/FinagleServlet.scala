package com.github.savaki.finagle.servlet

import javax.servlet.http.HttpServlet
import com.twitter.finagle.Service
import javax.servlet.ServletConfig
import com.twitter.finagle.http.{Request, Response}

/**
 * @author matt.ho@gmail.com
 */
trait FinagleServlet {
  self: HttpServlet =>

  var finagleService: Service[Request, Response] = null

  override def init(config: ServletConfig) {
    val className: String = config.getInitParameter("service.factory.classname")
    val factory: ServiceFactory = Class.forName(className).newInstance().asInstanceOf[ServiceFactory]
    finagleService = factory.build
  }
}
