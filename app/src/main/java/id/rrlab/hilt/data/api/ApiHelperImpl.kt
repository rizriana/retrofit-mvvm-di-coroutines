package id.rrlab.hilt.data.api

import id.rrlab.hilt.data.model.User
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): Response<List<User>> {
        return apiService.getUsers()
    }
}