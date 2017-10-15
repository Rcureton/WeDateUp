package com.datenyc.mom.datenyc.ui.launch

import com.ge.cbyge.ui.WeDateUpSingleFragmentActivity


class LaunchActivity : WeDateUpSingleFragmentActivity() {

    override fun createFragment() = LaunchFragment.newInstance()

}
