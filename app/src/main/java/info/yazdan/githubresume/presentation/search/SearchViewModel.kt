package info.yazdan.githubresume.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    var username: MutableLiveData<String> = MutableLiveData()
}