package com.lucasdam.githubchanllenge.shared.model.domain.response

import com.lucasdam.githubchanllenge.shared.model.view.Owner

/**
 * @author Lucas Marciano on 05/04/20.
 */

data class RepositoryResponse(
    var items: List<RepositoryItemResponse>
)

data class RepositoryItemResponse(
    var name: String,
    var stargazers_count: Int,
    var forks_count: Int,
    var owner: Owner
)
