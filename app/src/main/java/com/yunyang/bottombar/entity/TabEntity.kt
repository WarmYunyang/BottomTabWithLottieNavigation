package com.yunyang.bottombar.entity

import com.yunyang.bottombarwithlottie.listener.CustomBottomTabEntity

/**
 * Created by YunYang.
 * Date: 2020/12/9
 * Time: 上午 11:07
 * Des: TabEntity
 */
data class TabEntity(val title: String, val iconRes: Int) : CustomBottomTabEntity {

    override fun getTabTitle(): String? {
        return title
    }

    override fun getTabIcon(): Int {
        return iconRes
    }

}