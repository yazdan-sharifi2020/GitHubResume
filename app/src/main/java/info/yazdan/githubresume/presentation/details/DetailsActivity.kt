package info.yazdan.githubresume.presentation.details

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import info.yazdan.githubresume.databinding.ActivityDetailsBinding
import info.yazdan.githubresume.presentation.base.BaseActivity
import info.yazdan.githubresume.presentation.common.imageloader.ImageLoaderContext
import info.yazdan.githubresume.presentation.search.USER_NAME
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.lang.Exception
import android.content.Intent
import android.net.Uri
import info.yazdan.githubresume.R

class DetailsActivity : BaseActivity() , KodeinAware, IDetailsActivity {

    override val kodein: Kodein by kodein()
    private val factory: DetailsViewModelFactory by instance()

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel = ViewModelProviders.of(this, factory).get(DetailsViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.listener = this

        val username : String? = intent.extras!!.getString(USER_NAME)
        binding.toolbar.title = username

        viewModel.fetchUsernameInfo(username)
        viewModel.user.observe(this, Observer {user ->
            ImageLoaderContext.imageLoader.bind(binding.profileImage, user.avatar_url)
            binding.name.text = user.name
            binding.bio.text = user.bio
            binding.company.text = user.company
            binding.followers.text = "Followers: ${user.followers}"
            binding.following.text = "Following: ${user.following}"
            binding.publicRepo.text = "Public repositories: ${user.public_repos}"
            binding.publicGist.text = "Public Gists: ${user.public_gists}"
            binding.createdAt.text = "Created at: ${user.created_at}"
            binding.blog.text = "Blog link: ${user.blog}"
            binding.linkButton.setOnClickListener {
                try {
                    val url = user.html_url
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                } catch (e: Exception) {
                    onError(e.message)
                }
            }
        })
    }

    override fun onError(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        finish()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.user.value != null) {
            viewModel.progressState.postValue(false)
        }
    }

}