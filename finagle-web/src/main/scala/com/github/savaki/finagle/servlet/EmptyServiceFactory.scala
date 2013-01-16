package com.github.savaki.finagle.servlet

import com.twitter.finagle.Service
import org.jboss.netty.handler.codec.http.{HttpResponseStatus, DefaultHttpResponse, HttpRequest, HttpResponse}
import com.twitter.util.Future
import org.jboss.netty.buffer.ChannelBuffers

/**
 * @author matt.ho@gmail.com
 */
class EmptyServiceFactory extends Service[HttpRequest, HttpResponse] with ServiceFactory {
  def build: Service[HttpRequest, HttpResponse] = this

  def apply(request: HttpRequest): Future[HttpResponse] = {
    val content =
      """
        |uri  = %s
      """.stripMargin.format(request.getUri)

    val response = new DefaultHttpResponse(request.getProtocolVersion, HttpResponseStatus.OK)
    response.setContent(ChannelBuffers.wrappedBuffer(content.getBytes("UTF-8")))

    Future.value {
      response
    }
  }
}
