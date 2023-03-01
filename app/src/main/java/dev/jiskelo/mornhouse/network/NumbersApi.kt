package dev.jiskelo.mornhouse.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NumbersApi {

    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getNumber(number: Int): String {
        return httpClient.get("$BASE_URL/$number").body()
    }

    suspend fun getRandomNumberFacts(): String {
        return httpClient.get(URL_RANDOM_NUMBER).body()
    }

    companion object {
        private const val BASE_URL = "http://numbersapi.com"
        private const val URL_RANDOM_NUMBER = "$BASE_URL/random/math"
    }
}