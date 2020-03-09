package info.yazdan.githubresume.data.repository

import info.yazdan.githubresume.data.network.Api
import info.yazdan.githubresume.data.network.SafeApiRequest
import info.yazdan.githubresume.domain.User
import info.yazdan.githubresume.helper.mapper.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val api: Api) : SafeApiRequest() {

    suspend fun fetchUsername(username: String): User {
        return withContext(Dispatchers.IO) {
            val response = apiRequest {
                api.fetchUsername(username)
            }
            response.toUser()
        }
    }

}