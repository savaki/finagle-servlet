package com.github.savaki.finagle.servlet.servlet_30

import com.twitter.finagle.Service
import javax.servlet.AsyncContext
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import com.github.savaki.finagle.servlet.FinagleServletAdapter
import com.twitter.finagle.http.{Request, Response}

/**
 * @author matt.ho@gmail.com
 */
class FinagleAsyncAdapter(service: Service[Request, Response], asyncContext: AsyncContext) extends FinagleServletAdapter(service) with Runnable {

  lazy val httpServletRequest = asyncContext.getRequest.asInstanceOf[HttpServletRequest]

  lazy val httpServletResponse = asyncContext.getResponse.asInstanceOf[HttpServletResponse]

  def complete() {
    asyncContext.complete()
  }
}
