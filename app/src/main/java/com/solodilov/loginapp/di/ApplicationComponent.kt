package com.solodilov.loginapp.di

import android.app.Application
import com.solodilov.loginapp.presentation.login.LoginFragment
import com.solodilov.loginapp.presentation.payment_list.PaymentListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
	DataModule::class,
	PreferencesModule::class,
	NetworkModule::class,
	ViewModelModule::class,
])
interface ApplicationComponent {

	fun inject(fragment: LoginFragment)
	fun inject(fragment: PaymentListFragment)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application,
		): ApplicationComponent
	}
}