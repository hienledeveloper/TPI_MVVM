package hienle.developer.tpi_mvvm.core.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Hien on 5/9/24
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    private const val BASE_URL = "https://www.travel.taipei/open-api/"

    @Provides
    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor =
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        val mOkHttpClient =
            OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(mHttpLoggingInterceptor)
                .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }

}

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}