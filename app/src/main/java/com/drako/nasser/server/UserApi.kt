package com.drako.nasser.server

class UserApi {}
/*
class UserApi(private val client: HttpClient) {

    val END_POINT_GET_USER_KTOR="https://api.agify.io/"
    val SUB_END_POINT_GET_USER_KTOR="?name="
    val END_POINT_POST_USER_KTOR=""

    suspend fun getAgeOfPersonByName(userName:String): UserEntity =
        client.get("$END_POINT_GET_USER_KTOR$SUB_END_POINT_GET_USER_KTOR$userName")

    suspend fun addUser(user: UserEntity) {
        client.post<UserEntity>(END_POINT_POST_USER_KTOR) {
            body = user
        }
    }
}*/