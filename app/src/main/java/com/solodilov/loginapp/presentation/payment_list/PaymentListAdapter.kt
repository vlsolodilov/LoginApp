package com.solodilov.loginapp.presentation.payment_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solodilov.loginapp.databinding.ItemPaymentBinding
import com.solodilov.loginapp.domain.entity.Payment
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class PaymentListAdapter() : ListAdapter<Payment, PaymentViewHolder>(PaymentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder =
        PaymentViewHolder(ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class PaymentViewHolder(
    private val binding: ItemPaymentBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(payment: Payment) {
        binding.title.text = payment.title
        val decimalFormat = DecimalFormat("#.##")
        binding.amount.text = payment.amount?.let { decimalFormat.format(it).toString() } ?: ""
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        binding.time.text = payment.created?.format(formatter) ?: ""
    }
}

private class PaymentDiffCallback : DiffUtil.ItemCallback<Payment>() {

    override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
        return oldItem == newItem
    }
}