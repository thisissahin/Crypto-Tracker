package az.cryptotracker.presentation.ui.fragment.RateHistoryFragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import az.cryptotracker.R
import az.cryptotracker.databinding.FragmentRateHistoryBinding
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.presentation.base.BaseFragment
import az.cryptotracker.presentation.ui.adapter.RateHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class RateHistoryFragment : BaseFragment<FragmentRateHistoryBinding, RateHistoryViewModel>(
    R.layout.fragment_rate_history, RateHistoryViewModel::class.java
) {

    private val args: RateHistoryFragmentArgs by navArgs()

    private val adapter = RateHistoryAdapter()

    override fun setUpViews() {
        binding.apply {
            historyRv.adapter = adapter
            args.type.let {
                viewModel.getHistoryByType(CryptoType.valueOf(it))
            }
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.history.collectLatest {
                adapter.submitList(it)
            }
        }
    }

}