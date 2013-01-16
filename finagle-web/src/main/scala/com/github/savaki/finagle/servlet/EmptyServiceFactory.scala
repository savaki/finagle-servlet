package com.github.savaki.finagle.servlet

import com.twitter.finagle.Service
import org.jboss.netty.handler.codec.http.{HttpResponseStatus, DefaultHttpResponse}
import com.twitter.util.Future
import org.jboss.netty.buffer.ChannelBuffers
import com.twitter.finagle.http.{Request, Response}

/**
 * @author matt.ho@gmail.com
 */
class EmptyServiceFactory extends Service[Request, Response] with ServiceFactory {
  def build: Service[Request, Response] = this

  def apply(request: Request): Future[Response] = {
    val content =
      """
        |uri  = %s
      """.stripMargin.format(request.uri)

    val response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK)
    response.setContent(ChannelBuffers.wrappedBuffer(content.getBytes("UTF-8")))

    Future.value {
      Response(response)
    }
  }
}
