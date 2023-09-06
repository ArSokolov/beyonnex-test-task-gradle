package sokolov.beyonnex.testtask

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import sokolov.beyonnex.testtask.plugins.configureRouting
import sokolov.beyonnex.testtask.plugins.configureSerialization
import kotlin.test.*

class HttpApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting(CoreApp.create())
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

    @Test
    fun testAnagramCheckTrue() = testApplication {
        application {
            configureRouting(CoreApp.create())
            configureSerialization()
        }
        client.post("/anagrams/check") {
            contentType(ContentType.Application.Json)
            setBody("""["abc", "bca"]""")
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("true", bodyAsText())
        }
    }

    @Test
    fun testAnagramCheckFalse() = testApplication {
        application {
            configureRouting(CoreApp.create())
            configureSerialization()
        }
        client.post("/anagrams/check") {
            contentType(ContentType.Application.Json)
            setBody("""["abc", "aaa"]""")
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("false", bodyAsText())
        }
    }

    @Test
    fun testAnagramFind() = testApplication {
        application {
            val app = CoreApp.create()
            app.checkIsAnagram("abc", "bca")
            configureRouting(app)
            configureSerialization()
        }
        client.get("/anagrams/abc") {
            accept(ContentType.Application.Json)
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("""["bca"]""", bodyAsText())
        }
    }
}