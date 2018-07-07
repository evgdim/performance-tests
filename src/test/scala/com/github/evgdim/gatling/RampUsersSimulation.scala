package com.github.evgdim.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder
import scala.concurrent.duration._

class RampUsersSimulation extends Simulation {
    before {
        println("***** RampUsersSimulation start *****")
    }

    after {
        println("***** RampUsersSimulation finished ******")
    }

    val theHttpProtocolBuilder = http
        .baseURL("http://localhost:8080")

    val theScenarioBuilder = scenario("Scenario1")
        .exec(
            http("myRequest1")
                .get("/hello")
        )

    setUp(
        theScenarioBuilder.inject(rampUsers(20).over(5))
    ).protocols(theHttpProtocolBuilder)
}