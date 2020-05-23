package com.danielecampogiani.underlinepageindicator

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class Adapter(
    private val data: List<Item>,
    fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = ItemFragment(data[position])

    override fun getCount() = data.size

    override fun getPageTitle(position: Int) = data[position].name
}