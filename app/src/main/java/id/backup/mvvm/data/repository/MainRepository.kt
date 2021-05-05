package id.backup.mvvm.data.repository

import id.backup.mvvm.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}