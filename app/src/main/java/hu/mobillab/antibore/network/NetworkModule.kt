package hu.mobillab.antibore.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBoredApiUrl() = "https://www.boredapi.com/api/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("BaseUrl") baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(GsonConverterFactory.create())
        client(okHttpClient)
    }.build()

    @Singleton
    @Provides
    fun provideOccupationApi(retrofit: Retrofit): OccupationApi = retrofit.create(OccupationApi::class.java)
}