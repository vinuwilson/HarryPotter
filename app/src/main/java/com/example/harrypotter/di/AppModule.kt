package com.example.harrypotter.di

import android.content.Context
import androidx.room.Room
import com.example.harrypotter.data.database.CharacterDao
import com.example.harrypotter.data.database.CharacterListDatabase
import com.example.harrypotter.data.database.Converters
import com.example.harrypotter.data.database.repository.DbCharacterListRepository
import com.example.harrypotter.data.remote.api.HarryPotterApi
import com.example.harrypotter.data.remote.repository.CharacterListRepositoryImpl
import com.example.harrypotter.data.remote.repository.RemoteCharacterListService
import com.example.harrypotter.domain.repository.CharacterListRepository
import com.example.harrypotter.util.Constants.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideCharacterListApi(retrofit: Retrofit): HarryPotterApi = retrofit.create(HarryPotterApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideCharacterListRepository(
        service: RemoteCharacterListService,
        dbCharacterListRepository: DbCharacterListRepository
    ): CharacterListRepository =
        CharacterListRepositoryImpl(service, dbCharacterListRepository)

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideTypeConverters() = Converters()

    @Singleton
    @Provides
    fun provideCharacterDao(database: CharacterListDatabase): CharacterDao = database.characterListDao()

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ): CharacterListDatabase = Room.databaseBuilder(
        context,
        CharacterListDatabase::class.java,
        "character_List_db"
    )
        .fallbackToDestructiveMigration()
        .addTypeConverter(converters)
        .build()
}