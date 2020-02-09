package com.example.infrastructure.core.di

import com.example.data.feature.common.source.NotesLocalDataSource
import com.example.data.feature.common.source.NotesRemoteDataSource
import com.example.infrastructure.core.BASE_URL
import com.example.infrastructure.feature.notes.apiservice.NotesApiService
import com.example.infrastructure.feature.notes.database.NotesDatabase
import com.example.infrastructure.feature.notes.datasource.NotesLocalDataSourceImpl
import com.example.infrastructure.feature.notes.datasource.NotesRemoteDataSourceImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {

    single { provideOkhttpClient() }

    single { provideRetrofit(okHttpClient = get()) }

    single { provideNotesApiService(retrofit = get()) }

    single<NotesRemoteDataSource> { NotesRemoteDataSourceImpl(notesApiService = get()) }


    single { NotesDatabase.getInstance(get()).notesDatabaseDao }
    single<NotesLocalDataSource> { NotesLocalDataSourceImpl(notesDao = get()) }
}

/**
 * provides notes service.
 */
private fun provideNotesApiService(retrofit: Retrofit): NotesApiService = retrofit.create(NotesApiService::class.java)

/**
 * provides retrofit.
 */
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

/**
 * Http client.
 */
private fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()
