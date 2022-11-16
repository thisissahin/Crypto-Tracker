package az.cryptotracker.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VBinding : ViewDataBinding, VM : ViewModel>(
    private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : Fragment() {

    lateinit var viewModel: VM

    private var _vBinding: VBinding? = null
    val binding: VBinding get() = _vBinding!!

    lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity: BaseActivity = context
            this.baseActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vBinding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    open fun setUpViews() {}

    open fun observeData() {}

    private fun init() {
        viewModel = ViewModelProvider(this).get(viewModelClass)
    }

    fun show(message: String, useToast: Boolean = false) {
        baseActivity.show(message, useToast)
    }

    fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }

    fun showKeyboard() {
        baseActivity.showKeyboard()
    }

    override fun onDestroyView() {
        _vBinding = null
        super.onDestroyView()
    }
}