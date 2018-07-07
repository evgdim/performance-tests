package com.github.evgdim.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder
import io.gatling.http.protocol.HttpProtocolBuilder.toHttpProtocol

class Simulation1 extends Simulation {
    before {
        println("***** Simulation1 start *****")
    }

    after {
        println("***** Simulation1 finished ******")
    }

    val theHttpProtocolBuilder = http
        .baseURL("http://localhost:8080")

    val theScenarioBuilder = scenario("Scenario1")
        .exec(
            http("myRequest1")
                .get("/hello")
        )

    setUp(
        theScenarioBuilder.inject(atOnceUsers(10))
    ).protocols(theHttpProtocolBuilder)
}