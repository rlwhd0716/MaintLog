package com.github.maintlog_android.di

import android.content.Context
import com.github.data.annotation.PublicDataApiKey
import com.github.data.api.PublicDataApiService
import com.github.maintlog_android.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @PublicDataApiKey
    fun provideApiKey(): String = BuildConfig.PUBLIC_DATA_API_KEY

    @Singleton
    @Provides
    fun provideHttpInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }


    // Interceptor-차단기
    @Singleton
    @Provides
    fun provideInterceptor() : Interceptor =
        Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            chain.proceed(original.newBuilder().apply {
                addHeader("accept", "*/*")
                addHeader("Content-Type", "application/json")
                addHeader("Connection", "close")
//                addHeader("Accept-Language", Locale.getDefault().toLanguageTag())
            }.build())
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)
        .retryOnConnectionFailure(true)
        .cache(null)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideGson() : Gson = Gson().newBuilder()
        .setLenient()
        .create()


    /*@Singleton
    @Provides
    fun provideApiService(@ApplicationContext appContext: Context, okHttpClient: OkHttpClient, gson: Gson) : MainApiService {
//        val sslClient = getPinnedCertSslSocketFactory(appContext)
        val baseUrl = BuildConfig.API_SERVER_IP
//        if (sslClient == null) {
//            return Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build().create(ApiService::class.java)
//        } else {
//            val trustManager = TrustEveryoneManager()
            val okHttpsClient = okHttpClient.newBuilder()
//                .sslSocketFactory(sslClient, trustManager)
                .build()
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(MainApiService::class.java)
//        }
    }*/


    @Singleton
    @Provides
    fun provideOtherApiService(@ApplicationContext appContext: Context, okHttpClient: OkHttpClient, gson: Gson) : PublicDataApiService {
        val baseUrl = BuildConfig.PUBLIC_DATA_API
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(PublicDataApiService::class.java)
    }
}