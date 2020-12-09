package com.yunyang.bottombarwithlottie.listener

/**
 * Created by YunYang.
 * Date: 2020/12/8
 * Time: 下午 04:49
 * Des: 选中/未选中监听器
 */
interface OnTabSelectListener {

    fun onTabSelect(position: Int)

    fun onTabReselect(position: Int)

}