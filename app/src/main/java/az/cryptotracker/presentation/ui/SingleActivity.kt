package az.cryptotracker.presentation.ui

import android.os.Bundle
import az.cryptotracker.R
import az.cryptotracker.presentation.base.BaseActivity
import az.cryptotracker.presentation.util.ServiceUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ServiceUtil.startCheckRateService(this)
    }
}