package id.rrlab.hilt.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import id.rrlab.hilt.data.api.ApiHelper
import javax.inject.Inject

@ViewModelScoped
class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}