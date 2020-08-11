package com.ubirch.util.deepCheck.util

import com.ubirch.util.deepCheck.model.ServiceCheckResponse


/**
  * author: cvandrei
  * since: 2017-07-28
  */
object ServiceCheckResponseUtil {

  /**
    * Accept a [[ServiceCheckResponse]], take all it's messages and add a service specific prefix to each.
    *
    * @param servicePrefix e.g. "user-service"
    * @param res           the [[ServiceCheckResponse]] whose message will get a service specific prefix
    * @return converted [[ServiceCheckResponse]]
    */
  def addServicePrefix(servicePrefix: String, res: ServiceCheckResponse): ServiceCheckResponse = {

    // TODO unit tests
    val m = res.messages map (s"[$servicePrefix] " + _)
    res.copy(messages = m)

  }

  /**
    * Merges a number of [[ServiceCheckResponse]]s into one.
    *
    * @param responses sequence of [[ServiceCheckResponse]]s to merge
    * @return all responses merged into one
    */
  def merge(responses: Seq[ServiceCheckResponse]): ServiceCheckResponse = {

    // TODO unit tests
    val resultingStatus: Boolean = responses.forall(n => n.status)
    val resultingMessages: Seq[String] = responses.foldLeft(Nil: Seq[String]) { (m: Seq[String], n: ServiceCheckResponse) =>
      m ++ n.messages
    }

    ServiceCheckResponse(
      status = resultingStatus,
      messages = resultingMessages
    )

  }

}
