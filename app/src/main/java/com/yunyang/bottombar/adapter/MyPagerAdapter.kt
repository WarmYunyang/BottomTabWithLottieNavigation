package com.yunyang.bottombar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yunyang.bottombar.fragment.TestFragment

/**
 * Created by YunYang.
 * Date: 2020/12/9
 * Time: 下午 02:07
 * Des: VP+TAB
 */
class MyPagerAdapter(fm: FragmentManager, private val mTabTitles: Array<String>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabFragments: ArrayList<Fragment> = ArrayList()

    init {
        for (i in mTabTitles.indices) {
            tabFragments.add(TestFragment.newInstance(mTabTitles[i]))
        }
    }

    override fun getItem(position: Int) = tabFragments[position]

    override fun getCount() = mTabTitles.size

    override fun getPageTitle(position: Int) = mTabTitles[position]

}