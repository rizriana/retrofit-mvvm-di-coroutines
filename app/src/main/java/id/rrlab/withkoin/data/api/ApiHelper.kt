package id.rrlab.withkoin.data.api

import id.rrlab.withkoin.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}