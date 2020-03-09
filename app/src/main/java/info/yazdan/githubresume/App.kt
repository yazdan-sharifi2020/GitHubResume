package info.yazdan.githubresume

import android.app.Application
import info.yazdan.githubresume.data.network.Api
import info.yazdan.githubresume.data.network.NetworkConnectionInterceptor
import info.yazdan.githubresume.data.repository.UserRepository
import info.yazdan.githubresume.presentation.details.DetailsViewModelFactory
import info.yazdan.githubresume.presentation.search.SearchViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { Api(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { SearchViewModelFactory() }
        bind() from provider { DetailsViewModelFactory(instance()) }
    }
}