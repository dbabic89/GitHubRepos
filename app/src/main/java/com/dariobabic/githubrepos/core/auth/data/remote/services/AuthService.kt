package com.dariobabic.githubrepos.core.auth.data.remote.services

import com.dariobabic.githubrepos.core.auth.data.remote.models.AccessTokenResponse
import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    fun getAccessToken(
        @Query("client_id")
        clientId: String,
        @Query("client_secret")
        clientSecret: String,
        @Query("code")
        code: String
    ): Single<AccessTokenResponse>
}