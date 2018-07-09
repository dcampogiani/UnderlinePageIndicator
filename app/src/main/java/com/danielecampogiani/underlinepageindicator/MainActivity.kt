package com.danielecampogiani.underlinepageindicator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            setup()
        }
    }

    private fun setup() {
        pager.adapter = Adapter(getItems(), supportFragmentManager)
        indicator.setTabLayoutAndViewPager(tabs, pager)
    }

    private fun getItems() = listOf(
            Item("Short"),
            Item("This is Longer")
    )
}