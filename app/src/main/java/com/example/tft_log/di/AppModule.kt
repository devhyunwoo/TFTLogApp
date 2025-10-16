package com.example.tft_log.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.tft_log.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tft.log.data.api.apiService.AsiaRiotApiService
import com.tft.log.data.api.apiService.DragonApiService
import com.tft.log.data.api.apiService.KrRiotApiService
import com.tft.log.data.room.database.TFTDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

val Context.datastore: DataStore<Preferences> by preferencesDataStore(
    name = "TFT_datastore"
)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url

                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("api_key", Constants.API_KEY)
                    .build()

                val newRequest = originalRequest.newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    @AsiaRiotApi
    fun provideAsiaRetrofit(client: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ASIA)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    @KrRiotApi
    fun provideKrRetrofit(client: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_KR)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @DragonApi
    fun provideDragonRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return Retrofit.Builder()
            .baseUrl(Constants.DRAGON_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@AsiaRiotApi retrofit: Retrofit): AsiaRiotApiService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideKrApiService(@KrRiotApi retrofit: Retrofit): KrRiotApiService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideDragonApiService(@DragonApi retrofit: Retrofit): DragonApiService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.datastore
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TFTDatabase {
        return Room.databaseBuilder(context, TFTDatabase::class.java, "TFTDatabase")
            .fallbackToDestructiveMigrationFrom(true).build()
    }
}