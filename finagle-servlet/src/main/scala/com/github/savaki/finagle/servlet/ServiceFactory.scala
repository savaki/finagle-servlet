package com.github.savaki.finagle.servlet

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import javax.servlet.ServletContext

/**
 * @author matt.ho@gmail.com
 */
trait ServiceFactory {
  def build: Service[Request, Response]

  /**
   * @param servletContext the current servletContext
   */
  def setServletContext(servletContext:ServletContext)
}


