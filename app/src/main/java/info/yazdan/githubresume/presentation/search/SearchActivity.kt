package info.yazdan.githubresume.presentation.search

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import info.yazdan.githubresume.R
import info.yazdan.githubresume.databinding.ActivitySearchBinding
import info.yazdan.githubresume.presentation.base.BaseActivity
import info.yazdan.githubresume.presentation.details.DetailsActivity
import info.yazdan.githubresume.presentation.util.forceClearFocus
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

const val USER_NAME = "username"

class SearchActivity : BaseActivity() , KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: SearchViewModelFactory by instance()

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.generateButton.setOnClickListener {
            fetchUsername()
        }

        binding.usernameInput.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                fetchUsername()
                forceClearFocus(binding.usernameInput)
            }
            true
        }
    }

    private fun fetchUsername() {
        if (viewModel.username.value == null || viewModel.username.value?.isEmpty()!!) {
            binding.usernameInput.error = resources.getString(R.string.please_enter_username)
            return
        }
        val intent = Intent(this, DetailsActivity::class.java)
        val activityOptions: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, binding.usernameInput, USER_NAME)
        val content = viewModel.username.value
        intent.putExtra(USER_NAME, content)
        startActivity(intent, activityOptions.toBundle())
    }
}