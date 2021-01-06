package com.lucasdam.githubchanllenge.shared.services

import com.lucasdam.githubchanllenge.shared.model.domain.response.RepositoryResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author Lucas Marciano on 05/04/20.
 */

interface MainService {
    @GET("repositories?q=language:kotlin&sort=stars")
    fun fetchAllRepositories(): Single<RepositoryResponse>
}
