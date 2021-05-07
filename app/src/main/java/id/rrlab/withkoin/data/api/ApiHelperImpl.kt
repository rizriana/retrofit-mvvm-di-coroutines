package id.rrlab.withkoin.data.api

import id.rrlab.withkoin.data.model.User
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): Response<List<User>> =
        apiService.getUsers()
}