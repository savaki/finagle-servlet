package com.github.savaki.finagle.servlet

import com.twitter.finagle.Service
import org.jboss.netty.handler.codec.http.{HttpResponse, HttpRequest}

/**
 * @author matt.ho@gmail.com
 */
trait ServiceFactory {
  def build: Service[HttpRequest, HttpResponse]
}
