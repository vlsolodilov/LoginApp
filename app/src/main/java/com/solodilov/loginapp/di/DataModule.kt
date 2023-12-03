package com.solodilov.loginapp.di

import com.solodilov.loginapp.data.preferences.Preferences
import com.solodilov.loginapp.data.preferences.PreferencesImpl
import com.solodilov.loginapp.data.repository.PaymentRepositoryImpl
import com.solodilov.loginapp.domain.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

	@Singleton
	@Binds
	fun bindScheduleRepository(impl: PaymentRepositoryImpl): PaymentRepository

	@Singleton
	@Binds
	fun bindPreferences(impl: PreferencesImpl): Preferences

}