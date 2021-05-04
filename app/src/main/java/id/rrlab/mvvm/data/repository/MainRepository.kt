package id.rrlab.mvvm.data.repository

import id.rrlab.mvvm.data.api.ApiHelper
import id.rrlab.mvvm.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {
    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }
}