package com.github.savaki.finagle.servlet.jetty

import org.eclipse.jetty.continuation.Continuation
import org.jboss.netty.handler.codec.http.{HttpResponse, HttpRequest}
import com.twitter.finagle.Service
import com.github.savaki.finagle.servlet.FinagleServletAdapter
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

/**
 * @author matt.ho@gmail.com
 */
class FinagleJettyAdapter(finagleService: Service[HttpRequest, HttpResponse], continuation: Continuation, val httpServletRequest: HttpServletRequest) extends FinagleServletAdapter(finagleService) {
  def httpServletResponse = continuation.getServletResponse.asInstanceOf[HttpServletResponse]

  def complete() {
    continuation.complete()
  }
}
