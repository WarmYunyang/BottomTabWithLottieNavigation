package com.yunyang.bottombar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yunyang.bottombar.entity.TabEntity
import com.yunyang.bottombar.fragment.TestFragment
import com.yunyang.bottombarwithlottie.listener.CustomBottomTabEntity
import com.yunyang.bottombarwithlottie.listener.OnTabSelectListener
import com.yunyang.bottombarwithlottie.view.BottomTabWithLottieNavigation

class FgActivity : AppCompatActivity() {

    private val mTitles = arrayOf("视频", "发现", "我的")

    private val mIcons = arrayOf(
        R.raw.video,
        R.raw.discover,
        R.raw.mine
    )

    private val mTabEntities: ArrayList<CustomBottomTabEntity> = ArrayList()

    lateinit var testFragment0: TestFragment
    lateinit var testFragment1: TestFragment
    lateinit var testFragment2: TestFragment
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fg)

        val fgBtwln: BottomTabWithLottieNavigation = findViewById(R.id.ac_fg_bbtln)

        testFragment0 = TestFragment.newInstance(mTitles[0])
        testFragment1 = TestFragment.newInstance(mTitles[1])
        testFragment2 = TestFragment.newInstance(mTitles[2])

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIcons[i]))
        }

        supportFragmentManager.beginTransaction().add(R.id.fl_content, testFragment0)
            .commitAllowingStateLoss()
        currentFragment = testFragment0

        fgBtwln.apply {
            setTabItems(mTabEntities)
            setOnTabSelectListener(object : OnTabSelectListener {
                override fun onTabSelect(position: Int) {
                    Toast.makeText(this@FgActivity, "onTabSelect  $position", Toast.LENGTH_SHORT)
                        .show()
                    when (position) {
                        0 -> {
                            showFragment(testFragment0)
                        }
                        1 -> {
                            showFragment(testFragment1)
                        }
                        2 -> {
                            showFragment(testFragment2)
                        }
                    }
                }

                override fun onTabReselect(position: Int) {
                    Toast.makeText(this@FgActivity, "onTabReselect  $position", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

    }

    private fun showFragment(fragment: TestFragment) {
        if (fragment != currentFragment) {
            resetFragmentState()
            if (!fragment.isAdded) {
                supportFragmentManager.beginTransaction().add(R.id.fl_content, fragment)
                    .commitAllowingStateLoss()
            } else {
                supportFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss()
            }
            currentFragment = fragment
        }
    }

    private fun resetFragmentState() {
        supportFragmentManager.beginTransaction().hide(currentFragment).commitAllowingStateLoss()
    }

}