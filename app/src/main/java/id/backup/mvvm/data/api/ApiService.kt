package id.backup.mvvm.data.api

import id.backup.mvvm.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}