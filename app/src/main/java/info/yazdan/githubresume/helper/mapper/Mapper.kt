package info.yazdan.githubresume.helper.mapper

import info.yazdan.githubresume.data.network.response.GithubUserResponse
import info.yazdan.githubresume.domain.User

fun GithubUserResponse.toUser() : User {
    return User(
        this.id,
        this.node_id,
        this.avatar_url,
        this.html_url,
        this.name,
        this.company,
        this.blog,
        this.bio,
        this.public_repos,
        this.public_gists,
        this.followers,
        this.following,
        this.created_at,
        this.updated_at
    )
}