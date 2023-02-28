package dev.jiskelo.mornhouse.di

import dev.jiskelo.mornhouse.data.AppDatabase
import dev.jiskelo.mornhouse.data.dao.NumberDao
import dev.jiskelo.mornhouse.network.NumbersApi
import dev.jiskelo.mornhouse.presenter.first.FirstViewModel
import dev.jiskelo.mornhouse.presenter.second.SecondViewModel
import dev.jiskelo.mornhouse.repositories.NumberRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single <NumberDao> {
        AppDatabase.getDatabase(androidContext()).numberDao()
    }

    single<NumbersApi> {
        NumbersApi()
    }
}

val repositoryModule = module {
    single<NumberRepository> { NumberRepository(numberDao = get()) }
}

val viewModelModule = module {
    viewModel { FirstViewModel(api = get(), repository = get()) }
    viewModel { SecondViewModel() }
}


