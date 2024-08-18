package com.example.harrypotter.di

import com.example.harrypotter.data.remote.api.HarryPotterApi
import com.example.harrypotter.data.remote.repository.CharacterListRepositoryImpl
import com.example.harrypotter.data.remote.repository.RemoteCharacterListService
import com.example.harrypotter.domain.repository.CharacterListRepository
import com.example.harrypotter.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCharacterListApi(retrofit: Retrofit) = retrofit.create(HarryPotterApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideCharacterListRepository(service: RemoteCharacterListService): CharacterListRepository =
        CharacterListRepositoryImpl(service)
}