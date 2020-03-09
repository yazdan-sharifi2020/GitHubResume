package info.yazdan.githubresume.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.yazdan.githubresume.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(userRepository) as T
    }

}