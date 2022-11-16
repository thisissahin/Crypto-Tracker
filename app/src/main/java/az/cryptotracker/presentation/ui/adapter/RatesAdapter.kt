package az.cryptotracker.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import az.cryptotracker.databinding.ItemRateBinding
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.model.response.CryptoValue
import az.cryptotracker.presentation.base.BaseListAdapter
import az.cryptotracker.presentation.base.BaseViewHolder

class RatesAdapter(
    private val onClick: (CryptoType?) -> Unit,
) : BaseListAdapter<CryptoValue, RatesAdapter.ChatsVH>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new },
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): ChatsVH = ChatsVH(ItemRateBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: ChatsVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ChatsVH(binding: ItemRateBinding) :
        BaseViewHolder<ItemRateBinding>(binding) {

        fun bind(model: CryptoValue) {
            binding.apply {
                this.model = model
                itemView.setOnClickListener {
                    onClick.invoke(model.unit)
                }
                executePendingBindings()
            }
        }
    }


}