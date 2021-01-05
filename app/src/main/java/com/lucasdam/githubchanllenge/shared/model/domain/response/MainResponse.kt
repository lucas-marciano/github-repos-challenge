package com.lucasdam.githubchanllenge.shared.model.domain.response

import com.lucasdam.githubchanllenge.shared.model.view.Owner

/**
 * @author Lucas Marciano on 05/04/20.
 */

data class RepositoryResponse(
    var results: List<RepositoryItemResponse>
)

data class RepositoryItemResponse(
    var name: String,
    var stargazers_count: String,
    var forks_count: String,
    var owner: Owner
)
