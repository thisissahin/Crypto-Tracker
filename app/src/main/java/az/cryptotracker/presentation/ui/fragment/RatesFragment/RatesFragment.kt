package az.cryptotracker.presentation.ui.fragment.RatesFragment

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import az.cryptotracker.R
import az.cryptotracker.databinding.FragmentRatesBinding
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.presentation.base.BaseFragment
import az.cryptotracker.presentation.ui.adapter.RatesAdapter
import az.cryptotracker.presentation.ui.dialog.DialogSetRange
import az.cryptotracker.presentation.util.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class RatesFragment : BaseFragment<FragmentRatesBinding, RatesViewModel>(
    R.layout.fragment_rates, RatesViewModel::class.java
) {

    private val adapter = RatesAdapter { item ->
        item?.let {
            showRangeDialog(it)
        }
    }

    override fun setUpViews() {
        binding.apply {
            ratesRv.adapter = adapter
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.exchangeRates.collectLatest {
                Log.d("TAG", "observeData: ")
                when (it) {
                    is ResultWrapper.Error -> show(it.error ?: "")
                    is ResultWrapper.Loading -> {
                        // showLoading()
                    }
                    is ResultWrapper.Success -> {
                        it.data?.body()?.let { response ->
                            adapter.submitList(response.getList())
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun showRangeDialog(type: CryptoType) {
        val dialog = DialogSetRange(type) {
            findNavController().navigate(RatesFragmentDirections.actionNavigationToRateHistory(type.name))
        }
        dialog.show(childFragmentManager, "dialog")
    }

}