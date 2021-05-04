package id.rrlab.mvvm.data.api

import id.rrlab.mvvm.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>
}