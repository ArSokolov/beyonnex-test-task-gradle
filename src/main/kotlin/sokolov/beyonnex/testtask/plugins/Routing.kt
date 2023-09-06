package sokolov.beyonnex.testtask.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import sokolov.beyonnex.testtask.CoreApp

fun Application.configureRouting(coreApp: CoreApp) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/anagrams/check") {
            val stringsToCheck = call.receive<List<String>>()
            val left = stringsToCheck.getOrNull(0)
            val right = stringsToCheck.getOrNull(1)
            if (left != null && right != null) {
                val result = coreApp.checkIsAnagram(left, right)
                call.respond(result)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }

        }
        get("/anagrams/{toFind}") {
            val string = call.parameters["toFind"]
            if (string != null) {
                val result = coreApp.findAnagramsFor(string)
                call.respond(result)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}
