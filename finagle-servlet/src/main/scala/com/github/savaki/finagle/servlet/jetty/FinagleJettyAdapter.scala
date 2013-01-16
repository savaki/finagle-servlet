package com.github.savaki.finagle.servlet.jetty

import org.eclipse.jetty.continuation.Continuation
import com.twitter.finagle.Service
import com.github.savaki.finagle.servlet.FinagleServletAdapter
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.twitter.finagle.http.{Request, Response}

/**
 * @author matt.ho@gmail.com
 */
class FinagleJettyAdapter(finagleService: Service[Request, Response], continuation: Continuation, val httpServletRequest: HttpServletRequest) extends FinagleServletAdapter(finagleService) {
  def httpServletResponse = continuation.getServletResponse.asInstanceOf[HttpServletResponse]

  def complete() {
    continuation.complete()
  }
}
