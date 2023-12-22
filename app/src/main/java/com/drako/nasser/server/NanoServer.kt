package com.drako.nasser.server

import fi.iki.elonen.NanoHTTPD

class NanoServer : NanoHTTPD(8080) {
    override fun serve(session: IHTTPSession): Response {
        val uri = session.uri;
        val responseText = "[server] is running: $uri"
        return newFixedLengthResponse(responseText);
    }
}

fun main() {
    val server = NanoServer();
    server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
    println("[server] is running in port 8080")
}