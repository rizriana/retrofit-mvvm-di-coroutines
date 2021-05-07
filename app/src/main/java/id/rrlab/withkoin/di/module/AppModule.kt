package id.rrlab.withkoin.di.module

import android.content.Context
import id.rrlab.withkoin.BuildConfig
import id.rrlab.withkoin.BuildConfig.BASE_URL
import id.rrlab.withkoin.data.api.ApiHelper
import id.rrlab.withkoin.data.api.ApiHelperImpl
import id.rrlab.withkoin.data.api.ApiService
import id.rrlab.withkoin.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { provideNetworkHelper(androidContext()) }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideApiService(get()) }
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

private fun provideNetworkHelper(
    context: Context
) = NetworkHelper(context)

private fun provideOkHttpClient(): OkHttpClient =
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit
        .Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(
    retrofit: Retrofit
): ApiService =
    retrofit.create(ApiService::class.java)

private fun provideApiHelper(apiHelper: ApiHelper): ApiHelper =
    apiHelper