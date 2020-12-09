package com.yunyang.bottombar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.yunyang.bottombar.adapter.MyPagerAdapter
import com.yunyang.bottombar.entity.TabEntity
import com.yunyang.bottombarwithlottie.listener.CustomBottomTabEntity
import com.yunyang.bottombarwithlottie.listener.OnTabSelectListener
import com.yunyang.bottombarwithlottie.view.BottomTabWithLottieNavigation

class VpFgActivity : AppCompatActivity() {

    private val mTitles = arrayOf("视频", "发现", "我的")

    private val mIcons = arrayOf(
        R.raw.video,
        R.raw.discover,
        R.raw.mine
    )

    private val mTabEntities: ArrayList<CustomBottomTabEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vp_fg)

        val vpFgvp: ViewPager = findViewById(R.id.ac_vp_fg_vp)
        val vpFgBtwln: BottomTabWithLottieNavigation = findViewById(R.id.ac_vp_fg_bbtln)

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIcons[i]))
        }

        with(vpFgvp) {
            adapter = MyPagerAdapter(supportFragmentManager, mTitles)
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    vpFgBtwln.setCurrentTab(position)
                }
            })
        }

        with(vpFgBtwln) {
            setTabItems(mTabEntities)
            setOnTabSelectListener(object : OnTabSelectListener {
                override fun onTabSelect(position: Int) {
                    vpFgvp.currentItem = position
                }

                override fun onTabReselect(position: Int) {

                }
            })
        }

    }

}