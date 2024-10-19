package com.practicum.testcleanarchitecture.di

import android.content.Context
import com.google.gson.Gson
import com.practicum.testcleanarchitecture.data.LocalStorage
import com.practicum.testcleanarchitecture.data.NetworkClient
import com.practicum.testcleanarchitecture.data.SearchHistoryStorage
import com.practicum.testcleanarchitecture.data.SharedPreferencesSearchHistoryStorage
import com.practicum.testcleanarchitecture.data.network.IMDbApiService
import com.practicum.testcleanarchitecture.data.network.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<IMDbApiService> {
        Retrofit.Builder()
            .baseUrl("https:/tv-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDbApiService::class.java)
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single<LocalStorage> {
        LocalStorage(
            sharedPreferences = get()
        )
    }

    single<SearchHistoryStorage> {
        SharedPreferencesSearchHistoryStorage(prefs = get(), gson = get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(imdbService = get(), context = androidContext())
    }

}