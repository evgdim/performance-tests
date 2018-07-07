package com.github.evgdim.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder.toHttpProtocol
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder
import scala.concurrent.duration._

class MultipleCallsSimulation extends Simulation {
    before {
        println("***** SimpleSimulation start *****")
    }

    after {
        println("***** SimpleSimulation finished ******")
    }

    val theHttpProtocolBuilder = http
        .baseURL("http://localhost:8080")

    val theScenarioBuilder = scenario("Scenario1")
        .exec(
            http("myRequest1")
                .get("/hello?time=100")
              .check(jsonPath("$.id").findAll.saveAs("id"))
        ).pace(2 seconds)
        .exec(http("secondReq").get("/next/${id}"))

    setUp(
        theScenarioBuilder.inject(atOnceUsers(10))
    ).protocols(theHttpProtocolBuilder)
}