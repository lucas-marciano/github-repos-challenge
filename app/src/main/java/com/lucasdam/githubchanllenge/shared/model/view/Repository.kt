package com.lucasdam.githubchanllenge.shared.model.view

import android.os.Parcelable
import com.lucasdam.githubchanllenge.shared.model.domain.response.RepositoryItemResponse
import kotlinx.android.parcel.Parcelize

/**
 * @author Lucas Marciano on 05/04/20.
 */

@Parcelize
data class Repository(
    var name: String,
    var stargazersCount: Int,
    var forksCount: Int,
    var owner: Owner
) : Parcelable {
    constructor(response: RepositoryItemResponse) : this(
        response.name,
        response.stargazers_count,
        response.forks_count,
        response.owner
    )
}
