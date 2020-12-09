package com.yunyang.bottombarwithlottie.listener

import androidx.annotation.RawRes

/**
 * Created by YunYang.
 * Date: 2020/12/8
 * Time: 下午 04:48
 * Des: 自定义底部导航Tab
 */
interface CustomBottomTabEntity {

    fun getTabTitle(): String?

    @RawRes
    fun getTabIcon(): Int

}