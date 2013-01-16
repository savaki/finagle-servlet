package com.github.savaki.finagle.servlet

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}

/**
 * @author matt.ho@gmail.com
 */
trait ServiceFactory {
  def build: Service[Request, Response]
}


