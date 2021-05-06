package id.rrlab.hilt.data.api

import id.rrlab.hilt.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}