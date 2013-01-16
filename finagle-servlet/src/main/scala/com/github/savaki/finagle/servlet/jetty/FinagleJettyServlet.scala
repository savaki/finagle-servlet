package com.github.savaki.finagle.servlet.jetty

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import org.eclipse.jetty.continuation.{ContinuationSupport, Continuation}
import com.github.savaki.finagle.servlet.FinagleServlet

/**
 * @author matt.ho@gmail.com
 */
class FinagleJettyServlet extends HttpServlet with FinagleServlet {
  override def service(request: HttpServletRequest, response: HttpServletResponse) {
    val continuation: Continuation = ContinuationSupport.getContinuation(request)
    continuation.suspend()
    val adapter = new FinagleJettyAdapter(finagleService, continuation, request)
    adapter.run()
  }
}
