package id.rrlab.withkoin.data.api

import id.rrlab.withkoin.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}