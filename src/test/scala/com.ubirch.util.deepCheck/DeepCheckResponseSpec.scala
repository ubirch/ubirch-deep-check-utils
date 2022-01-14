package com.ubirch.util.deepCheck

import com.ubirch.util.deepCheck.model.DeepCheckResponse
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers

/**
  * author: cvandrei
  * since: 2017-06-06
  */
class DeepCheckResponseSpec extends AnyFeatureSpec
  with Matchers {

  Feature("toJsonString()") {

    Scenario("default object") {

      // prepare
      val deepCheckResult = DeepCheckResponse()

      // test
      val json = deepCheckResult.toJsonString

      // verify
      val expected = """{"status":true,"messages":[]}"""
      json should be(expected)

    }

    Scenario("NOK; with two messages") {

      // prepare
      val deepCheckResult = DeepCheckResponse(status = false, messages = Seq("foo", "bar"))

      // test
      val json = deepCheckResult.toJsonString

      // verify
      val expected = """{"status":false,"messages":["foo","bar"]}"""
      json should be(expected)

    }

  }

}
