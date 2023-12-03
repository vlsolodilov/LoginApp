package com.solodilov.loginapp.presentation.payment_list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.solodilov.loginapp.App
import com.solodilov.loginapp.R
import com.solodilov.loginapp.databinding.FragmentPaymentListBinding
import com.solodilov.loginapp.domain.entity.Payment
import com.solodilov.loginapp.presentation.common.*
import javax.inject.Inject

class PaymentListFragment: Fragment(R.layout.fragment_payment_list) {
    private val binding by viewBinding(FragmentPaymentListBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PaymentListViewModel by viewModels { viewModelFactory }

    private var paymentListAdapter: PaymentListAdapter? = null

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
        paymentListAdapter = PaymentListAdapter()
        binding.rvPaymentList.adapter = paymentListAdapter
        binding.swipeContainer.setOnRefreshListener {
            viewModel.getData()
            binding.swipeContainer.isRefreshing = false
        }
        binding.errorLayout.tryButton.setOnClickListener { viewModel.getData() }
    }

    private fun handleState(state: UiState<List<Payment>>) = with(binding) {
        progressBar.isVisible = state is UiState.Loading
        errorLayout.root.isVisible = state is UiState.Error
        rvPaymentList.isVisible = state is UiState.Success

        state
            .onSuccess { data -> paymentListAdapter?.submitList(data) }
            .onError { error -> showToast(error.message.toString()) }
    }

    override fun onDestroyView() {
        paymentListAdapter = null
        super.onDestroyView()
    }
}