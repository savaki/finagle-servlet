package com.github.savaki.finagle.servlet

import org.jboss.netty.handler.codec.http._
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import com.twitter.finagle.Service
import scala.collection.JavaConversions._

/**
 * @author matt.ho@gmail.com
 */
abstract class FinagleServletAdapter(finagleService: Service[HttpRequest, HttpResponse]) {
  def httpServletRequest: HttpServletRequest

  def httpServletResponse: HttpServletResponse

  def complete()

  def run() {
    val request: HttpRequest = toHttpRequest(httpServletRequest)
    finagleService(request).onSuccess {
      response => onSuccess(response)
    }.onFailure {
      exception => onFailure(exception)
    }
  }

  def onSuccess(response: HttpResponse) {
    val statusCode: Int = response.getStatus.getCode
    httpServletResponse.setStatus(statusCode)

    response.getHeaders.foreach {
      entry => httpServletResponse.setHeader(entry.getKey, entry.getValue)
    }

    val content: Array[Byte] = response.getContent.array()
    if (content != null) {
      httpServletResponse.getOutputStream.write(content)
      httpServletResponse.setContentLength(content.size)
    }

    complete()
  }

  def onFailure(throwable: Throwable) {
    httpServletResponse.setStatus(500)
    complete()
  }

  def toHttpRequest(httpServletRequest: HttpServletRequest): HttpRequest = {
    val method = HttpMethod.valueOf(httpServletRequest.getMethod)
    val result = new DefaultHttpRequest(HttpVersion.HTTP_1_1, method, httpServletRequest.getRequestURI)

    httpServletRequest.getHeaderNames.foreach {
      headerName =>
        val headerValue: String = httpServletRequest.getHeader(headerName)
        result.setHeader(headerName, headerValue)
    }

    val contentType: String = httpServletRequest.getContentType
    if (contentType != null) {
      result.setHeader("Content-Type", contentType)
    }

    val contentLength: Int = httpServletRequest.getContentLength
    if (contentLength > 0) {
      result.setHeader("Content-Length", contentLength)
    }

    result
  }
}
