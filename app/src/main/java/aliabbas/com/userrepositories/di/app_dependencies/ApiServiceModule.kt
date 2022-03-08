package aliabbas.com.userrepositories.di.app_dependencies

import aliabbas.com.scalablecodebaseapp.shared.app_service_calls.Api
import aliabbas.com.userrepositories.shared.util.Constants
import aliabbas.com.userrepositories.shared.util.Constants.formatDDMMYYYYTHHMMSSZ
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created By Ali Abbas
 * This Class is used for
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Singleton
    @get:Provides
    val requestHeader: OkHttpClient
        get() {
            val applicationJsonCharsetUtf8 = "application/json; charset=utf-8"
            val httpClient = OkHttpClient.Builder()
            httpClient.retryOnConnectionFailure(true)
            httpClient.addInterceptor { chain ->
                val response = chain.proceed(chain.request())
                response.header("Content-ReportCopy", applicationJsonCharsetUtf8)
                response.header("Accept", applicationJsonCharsetUtf8)
                response.header("Connection", "close")
                response
            }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
            return httpClient.build()
        }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat(formatDDMMYYYYTHHMMSSZ)
                        .create()
                )
            )
            .client(client)
            .build().create(Api::class.java)
    }
}