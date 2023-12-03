package com.solodilov.loginapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solodilov.loginapp.presentation.common.ViewModelFactory
import com.solodilov.loginapp.presentation.login.LoginViewModel
import com.solodilov.loginapp.presentation.payment_list.PaymentListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentListViewModel::class)
    fun bindPaymentListViewModel(viewModel: PaymentListViewModel): ViewModel

}