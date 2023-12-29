package com.drako.nasser.server

private const val TIME_OUT = 60_000
/*
private val ktorHttpClient = HttpClient(Android) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>",message)
            }
        }
        level = LogLevel.ALL
    }
    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }

    install(DefaultRequest){
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}

*/