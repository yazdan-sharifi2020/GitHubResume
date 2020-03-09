package info.yazdan.githubresume.domain

data class User(
    var id: Int,
    var node_id : String? = null,
    var avatar_url : String? = null,
    var html_url : String? = null,
    var name : String? = null,
    var company : String? = null,
    var blog : String? = null,
    var bio : String? = null,
    var public_repos : Int,
    var public_gists : Int,
    var followers : Int,
    var following : Int,
    var created_at : String? = null,
    var updated_at : String? = null
)