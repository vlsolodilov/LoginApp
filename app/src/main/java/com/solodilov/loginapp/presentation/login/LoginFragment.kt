package com.solodilov.loginapp.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.solodilov.loginapp.App
import com.solodilov.loginapp.R
import com.solodilov.loginapp.databinding.FragmentLoginBinding
import com.solodilov.loginapp.presentation.common.*
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        collectFlow(viewModel.uiState, ::handleState)
    }

    private fun initViews() {
        binding.signInButton.setOnClickListener {
            viewModel.login(
                binding.login.text.toString(),
                binding.password.text.toString(),
            )
        }
    }

    private fun handleState(state: UiState<Boolean?>) = with(binding) {
        progressBar.isVisible = state is UiState.Loading
        loginLayout.isVisible = state !is UiState.Loading

        state
            .onSuccess { data ->
                when (data) {
                    true -> startPaymentListFragment()
                    false -> showToast(getString(R.string.error))
                    else -> {}
                }
            }
            .onError { error -> showToast(error.message.toString()) }
    }

    private fun startPaymentListFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_paymentListFragment)
        viewModel.clearState()
    }
}