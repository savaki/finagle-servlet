package com.github.savaki.finagle.servlet

import com.twitter.finagle.Service
import org.jboss.netty.handler.codec.http.{HttpResponseStatus, DefaultHttpResponse}
import com.twitter.util.Future
import org.jboss.netty.buffer.ChannelBuffers
import com.twitter.finagle.http.{Request, Response}
import javax.servlet.ServletContext

/**
 * @author matt.ho@gmail.com
 */
class EmptyServiceFactory extends Service[Request, Response] with ServiceFactory {
  /**
   * @param servletContext the current servletContext
   */
  def setServletContext(servletContext: ServletContext) {
    // intentionally left blank
  }

  def build: Service[Request, Response] = this

  def apply(request: Request): Future[Response] = {
    val content =
      """
        |# http properties
        |uri  = %s
      """.stripMargin.format(request.uri)

    val response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK)
    response.setContent(ChannelBuffers.wrappedBuffer(content.getBytes("UTF-8")))

    Future.value {
      Response(response)
    }
  }
}
