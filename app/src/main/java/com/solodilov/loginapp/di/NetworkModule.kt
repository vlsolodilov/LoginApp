package com.solodilov.loginapp.di

import com.solodilov.loginapp.data.datasource.network.AuthInterceptor
import com.solodilov.loginapp.data.datasource.network.EasyPayApi
import com.solodilov.loginapp.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

	private companion object {

		const val BASE_URL = "https://easypay.world/api-test/"
	}

	@Provides
	fun provideGsonConverterFactory(): GsonConverterFactory =
		GsonConverterFactory.create()

	@Singleton
	@Provides
	fun providesOkHttpClient(preferences: Preferences): OkHttpClient = OkHttpClient.Builder()
		.addNetworkInterceptor(AuthInterceptor(preferences))
		.addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
		.build()


	@Provides
	@Singleton
	fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
		okHttpClient: OkHttpClient,
	): Retrofit =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(gsonConverterFactory)
			.client(okHttpClient)
			.build()

	@Provides
	@Singleton
	fun provideEasyPayApi(
		retrofit: Retrofit,
	): EasyPayApi =
		retrofit.create(EasyPayApi::class.java)
}