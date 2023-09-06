package sokolov.beyonnex.testtask

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import sokolov.beyonnex.testtask.plugins.configureAdministration
import sokolov.beyonnex.testtask.plugins.configureHTTP
import sokolov.beyonnex.testtask.plugins.configureRouting
import sokolov.beyonnex.testtask.plugins.configureSerialization

fun main() {

    val coreApp = CoreApp.create()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = { this.module(coreApp) })
        .start(wait = true)
}

fun Application.module(coreApp: CoreApp) {
    configureSerialization()
    configureHTTP()
    configureAdministration()
    configureRouting(coreApp)
}


