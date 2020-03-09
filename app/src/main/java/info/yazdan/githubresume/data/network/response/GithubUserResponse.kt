package info.yazdan.githubresume.data.network.response

import com.google.gson.annotations.SerializedName

class GithubUserResponse {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("node_id")
    var node_id : String? = null

    @SerializedName("avatar_url")
    var avatar_url : String? = null

    @SerializedName("html_url")
    var html_url : String? = null

    @SerializedName("name")
    var name : String? = null

    @SerializedName("company")
    var company : String? = null

    @SerializedName("blog")
    var blog : String? = null

    @SerializedName("bio")
    var bio : String? = null

    @SerializedName("public_repos")
    var public_repos : Int = 0

    @SerializedName("public_gists")
    var public_gists : Int = 0

    @SerializedName("followers")
    var followers : Int = 0

    @SerializedName("following")
    var following : Int = 0

    @SerializedName("created_at")
    var created_at : String? = null

    @SerializedName("updated_at")
    var updated_at : String? = null

}