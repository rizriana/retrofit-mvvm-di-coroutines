package id.rrlab.withkoin.data.repository

import id.rrlab.withkoin.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() =
        apiHelper.getUsers()
}