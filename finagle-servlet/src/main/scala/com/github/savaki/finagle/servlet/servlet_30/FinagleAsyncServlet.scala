package com.github.savaki.finagle.servlet.servlet_30

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import javax.servlet.AsyncContext
import com.github.savaki.finagle.servlet.FinagleServlet

/**
 * @author matt.ho@gmail.com
 */
class FinagleAsyncServlet extends HttpServlet with FinagleServlet {
  override def service(req: HttpServletRequest, resp: HttpServletResponse) {
    val asyncContext: AsyncContext = req.startAsync()
    val adapter = new FinagleAsyncAdapter(finagleService, asyncContext)
    asyncContext.start(adapter)
  }
}
