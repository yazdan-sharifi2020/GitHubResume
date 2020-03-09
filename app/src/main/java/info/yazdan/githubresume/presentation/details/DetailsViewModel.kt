package info.yazdan.githubresume.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.yazdan.githubresume.data.repository.UserRepository
import info.yazdan.githubresume.domain.User
import info.yazdan.githubresume.helper.NoInternetException
import info.yazdan.githubresume.helper.coroutines.Coroutines

class DetailsViewModel(private val userRepository: UserRepository) : ViewModel() {

    var progressState: MutableLiveData<Boolean> = MutableLiveData(true)
    var user: MutableLiveData<User> = MutableLiveData()
    lateinit var listener: IDetailsActivity

    fun fetchUsernameInfo(username: String?) {
        Coroutines.main {
            username?.let {
                try {
                    user.postValue(userRepository.fetchUsername(it))
                    progressState.postValue(false)
                } catch (e: NoInternetException) {
                    listener.onError(e.message)
                } catch (e: Exception) {
                    listener.onError(e.message)
                }
            }
        }
    }

}