package com.emirli.eatup.di

import com.emirli.eatup.model.local.LocalDataSource
import com.emirli.eatup.model.remote.APIService
import com.emirli.eatup.model.remote.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        endPoint: EndPoint,
        authOkHttpClient: AuthOkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(authOkHttpClient.okHttpClient)
            .build()
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: APIService,
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    fun provideAuthInterceptorOkHttpClient(
        localDataSource: LocalDataSource
    ): AuthOkHttpClient {
        return provideAuthOkHttpClient(OkHttpClient.Builder()
            .addInterceptor {
                val token = localDataSource.getToken()
                val request: Request = if (token != null)
                    it.request().newBuilder().addHeader("Authorization", token).build()
                else
                    it.request().newBuilder().build()
                it.proceed(request)
            }
            .build())
    }

    private fun provideAuthOkHttpClient(okHttpClient: OkHttpClient): AuthOkHttpClient {
        return AuthOkHttpClient(okHttpClient)
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideEndPoint(): EndPoint {
        return EndPoint("https://arcane-temple-82877.herokuapp.com/")
    }

}

data class EndPoint(val url: String)
data class AuthOkHttpClient(val okHttpClient: OkHttpClient)
