package az.cryptotracker.presentation.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import az.cryptotracker.R
import az.cryptotracker.databinding.DialogSetRangeBinding
import az.cryptotracker.domain.model.CryptoRange
import az.cryptotracker.domain.model.CryptoRate
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.use_case.GetCheckRateByType
import az.cryptotracker.domain.use_case.InsertCheckRate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DialogSetRange(
    private val type: CryptoType,
    private val navigateHistory: (CryptoType) -> Unit
) : DialogFragment() {

    @Inject
    lateinit var getCheckRateByType: GetCheckRateByType

    @Inject
    lateinit var insertCheckRate: InsertCheckRate

    private lateinit var binding: DialogSetRangeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSetRangeBinding.inflate(inflater)

        setDefaultValues()

        binding.saveValue.setOnClickListener {
            val minValue = binding.minValue.text.toString()
            val maxValue = binding.maxValue.text.toString()

            if (minValue.isEmpty() || maxValue.isEmpty()) {
                Toast.makeText(context, getString(R.string.empty_field_warning), Toast.LENGTH_LONG)
                    .show()
            } else {
                val rate = CryptoRange(type.name, minValue.toDouble(), maxValue.toDouble())
                lifecycleScope.launch {
                    insertCheckRate.invoke(rate)
                }
                dismiss()
            }
        }

        binding.navigateRateHistory.setOnClickListener {
            navigateHistory.invoke(type)
            dismiss()
        }

        return binding.root
    }

    private fun setDefaultValues() {
        lifecycleScope.launch(IO) {
            val crypto = getCheckRateByType.invoke(type)

            crypto?.minValue?.let {
                binding.minValue.setText(it.toString())
            }
            crypto?.maxValue?.let {
                binding.maxValue.setText(it.toString())
            }
        }
    }
}