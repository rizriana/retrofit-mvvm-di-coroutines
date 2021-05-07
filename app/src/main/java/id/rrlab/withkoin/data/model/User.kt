package id.rrlab.withkoin.data.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "avatar")
    val avatar: String? = null
)
