package com.yunyang.bottombarwithlottie.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.airbnb.lottie.LottieAnimationView
import com.yunyang.bottombarwithlottie.R
import com.yunyang.bottombarwithlottie.listener.CustomBottomTabEntity
import com.yunyang.bottombarwithlottie.listener.OnTabSelectListener

/**
 * Created by YunYang.
 * Date: 2020/12/8
 * Time: 下午 04:58
 * Des: BottomTabWithLottieNavigation
 */
class BottomTabWithLottieNavigation @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private var mCurrentTab = 0
    private var mTabCount = 0

    /** title */
    private var mTextSize = 0f
    private var mTextSelectColor = 0
    private var mTextUnSelectColor = 0
    private var mTextBold = 0
    private var mTextAllCaps = false
    private var mTextVisible = true

    /** icon */
    private var mIconWidth = 0f
    private var mIconHeight = 0f
    private var mIconMargin = 0f

    /** anim */
    // 设置播放速度。如果速度<0，则动画将向后播放
    private var mAnimSpeed = 1.0f

    private var mTabsContainer: LinearLayoutCompat = LinearLayoutCompat(context).also {
        it.orientation = LinearLayoutCompat.HORIZONTAL
    }

    private val mTabItems: ArrayList<CustomBottomTabEntity> = ArrayList()

    init {
        addView(mTabsContainer)
        initialize(attributeSet)
    }

    private fun initialize(attributeSet: AttributeSet?) {
        attributeSet?.let { attr ->
            val typedArray: TypedArray =
                context.obtainStyledAttributes(attr, R.styleable.BottomTabWithLottieNavigation)
            try {
                // title
                mTextSize = typedArray.getDimension(
                    R.styleable.BottomTabWithLottieNavigation_btwln_textSize,
                    12f.sp2Px()
                )
                mTextSelectColor = typedArray.getColor(
                    R.styleable.BottomTabWithLottieNavigation_btwln_textSelectColor,
                    Color.parseColor("#ffffff")
                )
                mTextUnSelectColor = typedArray.getColor(
                    R.styleable.BottomTabWithLottieNavigation_btwln_textUnselectColor,
                    Color.parseColor("#AAffffff")
                )
                mTextBold = typedArray.getInt(
                    R.styleable.BottomTabWithLottieNavigation_btwln_textBold,
                    TEXT_BOLD_NONE
                )
                mTextAllCaps = typedArray.getBoolean(
                    R.styleable.BottomTabWithLottieNavigation_btwln_textAllCaps,
                    false
                )
                mTextVisible = typedArray.getBoolean(
                    R.styleable.BottomTabWithLottieNavigation_btwln_textVisible,
                    true
                )
                // icon
                mIconWidth = typedArray.getDimension(
                    R.styleable.BottomTabWithLottieNavigation_btwln_iconWidth,
                    0f.dp2Px()
                )
                mIconHeight = typedArray.getDimension(
                    R.styleable.BottomTabWithLottieNavigation_btwln_iconHeight,
                    0f.dp2Px()
                )
                mIconMargin = typedArray.getDimension(
                    R.styleable.BottomTabWithLottieNavigation_btwln_iconMargin,
                    2.5f.dp2Px()
                )
                mAnimSpeed = typedArray.getFloat(
                    R.styleable.BottomTabWithLottieNavigation_btwln_animSpeed,
                    1.0f
                )
            } catch (e: Exception) {
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setTabItems(tabItems: ArrayList<CustomBottomTabEntity>?) {
        check(!(tabItems == null || tabItems.size == 0)) { "tabItems can not be NULL or EMPTY !" }
        with(mTabItems) {
            clear()
            addAll(tabItems)
        }
        initTabItems()
    }

    private fun initTabItems() {
        mTabsContainer.removeAllViews()
        this.mTabCount = mTabItems.size
        var tabView: View
        for (i in 0 until mTabCount) {
            tabView = View.inflate(context, R.layout.layout_bottombar_item, null)
            tabView.tag = i
            addTab(i, tabView)
        }
        updateTabStyles()
    }

    private fun addTab(i: Int, tabView: View) {
        val tvTabTitle: AppCompatTextView = tabView.findViewById(R.id.tv_tab_title)
        tvTabTitle.text = mTabItems[i].getTabTitle()
        val ivTabIcon: LottieAnimationView = tabView.findViewById(R.id.img_tab_icon)
        with(ivTabIcon) {
            setAnimation(mTabItems[i].getTabIcon())
            speed = mAnimSpeed
            cancelAnimation()
            progress = 0f
        }

        tabView.setOnClickListener {
            val position = it.tag as Int
            if (mCurrentTab != position) {
                setCurrentTab(position)
                mListener?.onTabSelect(position)
            } else {
                mListener?.onTabReselect(position)
            }
        }

        /** 每一个Tab的布局参数  */
        mTabsContainer.addView(
            tabView,
            i,
            LinearLayoutCompat.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f)
        )
    }

    private fun updateTabStyles() {
        for (i in 0 until mTabCount) {
            val tabView = mTabsContainer.getChildAt(i)
            val tvTabTitle: AppCompatTextView = tabView.findViewById(R.id.tv_tab_title)
            with(tvTabTitle) {
                if (mTextVisible) {
                    visibility = View.VISIBLE
                    setTextColor(if (i == mCurrentTab) mTextSelectColor else mTextUnSelectColor)
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)
                    if (mTextAllCaps) {
                        text = text.toString().toUpperCase()
                    }
                    when (mTextBold) {
                        TEXT_BOLD_BOTH -> {
                            paint.isFakeBoldText = true
                        }
                        TEXT_BOLD_NONE -> {
                            paint.isFakeBoldText = false
                        }
                    }
                } else {
                    visibility = View.GONE
                }
            }

            val ivTabIcon: LottieAnimationView = tabView.findViewById(R.id.img_tab_icon)
            with(ivTabIcon) {
                if (i == mCurrentTab) {
                    playAnimation()
                } else {
                    cancelAnimation()
                    progress = 0f
                }
                val lp = LinearLayoutCompat.LayoutParams(
                    if (mIconWidth <= 0) LinearLayout.LayoutParams.WRAP_CONTENT else mIconWidth.toInt(),
                    if (mIconHeight <= 0) LinearLayout.LayoutParams.WRAP_CONTENT else mIconHeight.toInt()
                )
                lp.bottomMargin = mIconMargin.toInt()
                layoutParams = lp
            }
        }
    }

    private fun updateTabSelection(position: Int) {
        for (i in 0 until mTabCount) {
            val tabView = mTabsContainer.getChildAt(i)
            val isSelect = i == position
            val tvTabTitle: AppCompatTextView = tabView.findViewById(R.id.tv_tab_title)
            tvTabTitle.setTextColor(if (i == mCurrentTab) mTextSelectColor else mTextUnSelectColor)
            if (mTextBold == TEXT_BOLD_WHEN_SELECT) {
                tvTabTitle.paint.isFakeBoldText = isSelect
            }
            val ivTabIcon: LottieAnimationView = tabView.findViewById(R.id.img_tab_icon)
            with(ivTabIcon) {
                if (isSelect) {
                    playAnimation()
                } else {
                    cancelAnimation()
                    progress = 0f
                }
            }
        }
    }

    private var mListener: OnTabSelectListener? = null

    fun setOnTabSelectListener(listener: OnTabSelectListener?) {
        mListener = listener
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable("instanceState", super.onSaveInstanceState())
        bundle.putInt("mCurrentTab", mCurrentTab)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var stateRestore = state
        if (stateRestore is Bundle) {
            val bundle = stateRestore
            mCurrentTab = bundle.getInt("mCurrentTab")
            stateRestore = bundle.getParcelable("instanceState")
            if (mCurrentTab != 0 && mTabsContainer.childCount > 0) {
                updateTabSelection(mCurrentTab)
            }
        }
        super.onRestoreInstanceState(stateRestore)
    }

    private fun Float.dp2Px(): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics)

    private fun Float.sp2Px(): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, resources.displayMetrics)

    // setter and getter
    fun setCurrentTab(currentTab: Int) {
        this.mCurrentTab = currentTab
        updateTabSelection(currentTab)
    }

    fun setTextSize(textSize: Float) {
        this.mTextSize = textSize.sp2Px()
        updateTabStyles()
    }

    fun setTextSelectColor(textSelectColor: Int) {
        mTextSelectColor = textSelectColor
        updateTabStyles()
    }

    fun setTextUnSelectColor(textUnSelectColor: Int) {
        this.mTextUnSelectColor = textUnSelectColor
        updateTabStyles()
    }

    fun setTextBold(textBold: Int) {
        mTextBold = textBold
        updateTabStyles()
    }


    fun setTextAllCaps(textAllCaps: Boolean) {
        mTextAllCaps = textAllCaps
        updateTabStyles()
    }

    fun setTextVisible(textVisible: Boolean) {
        mTextVisible = textVisible
        updateTabStyles()
    }

    fun setIconWidth(iconWidth: Float) {
        mIconWidth = iconWidth.dp2Px()
        updateTabStyles()
    }

    fun setIconHeight(iconHeight: Float) {
        mIconHeight = iconHeight.dp2Px()
        updateTabStyles()
    }

    fun setIconMargin(iconMargin: Float) {
        mIconMargin = iconMargin.dp2Px()
        updateTabStyles()
    }

    fun getTabCount(): Int {
        return mTabCount
    }

    fun getCurrentTab(): Int {
        return mCurrentTab
    }

    fun getTextSize(): Float {
        return mTextSize
    }

    fun getTextSelectColor(): Int {
        return mTextSelectColor
    }

    fun getTextUnSelectColor(): Int {
        return mTextUnSelectColor
    }

    fun getTextBold(): Int {
        return mTextBold
    }

    fun isTextAllCaps(): Boolean {
        return mTextAllCaps
    }

    fun isTextVisible(): Boolean {
        return mTextVisible
    }

    fun getIconWidth(): Float {
        return mIconWidth
    }

    fun getIconHeight(): Float {
        return mIconHeight
    }

    fun getIconMargin(): Float {
        return mIconMargin
    }

    fun getIconView(tab: Int): LottieAnimationView? {
        val tabView = mTabsContainer.getChildAt(tab)
        return tabView.findViewById(R.id.img_tab_icon)
    }

    fun getTitleView(tab: Int): TextView? {
        val tabView = mTabsContainer.getChildAt(tab)
        return tabView.findViewById<AppCompatTextView?>(R.id.tv_tab_title)
    }

    companion object {

        const val TEXT_BOLD_NONE = 0
        const val TEXT_BOLD_WHEN_SELECT = 1
        const val TEXT_BOLD_BOTH = 2

    }

}