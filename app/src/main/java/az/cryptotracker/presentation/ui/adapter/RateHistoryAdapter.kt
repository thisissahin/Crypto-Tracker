package az.cryptotracker.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import az.cryptotracker.databinding.ItemRateBinding
import az.cryptotracker.databinding.ItemRateHistoryBinding
import az.cryptotracker.domain.model.CryptoRate
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.model.response.CryptoValue
import az.cryptotracker.presentation.base.BaseListAdapter
import az.cryptotracker.presentation.base.BaseViewHolder

class RateHistoryAdapter(
) : BaseListAdapter<CryptoRate, RateHistoryAdapter.ChatsVH>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new },
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): ChatsVH = ChatsVH(ItemRateHistoryBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: ChatsVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ChatsVH(binding: ItemRateHistoryBinding) :
        BaseViewHolder<ItemRateHistoryBinding>(binding) {

        fun bind(model: CryptoRate) {
            binding.apply {
                this.model = model
                executePendingBindings()
            }
        }
    }


}