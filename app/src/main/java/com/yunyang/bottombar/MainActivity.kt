package com.yunyang.bottombar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yunyang.bottombar.entity.TabEntity
import com.yunyang.bottombarwithlottie.listener.CustomBottomTabEntity
import com.yunyang.bottombarwithlottie.listener.OnTabSelectListener
import com.yunyang.bottombarwithlottie.view.BottomTabWithLottieNavigation

class MainActivity : AppCompatActivity(), OnTabSelectListener {

    private val mTitles = arrayOf("视频", "发现", "我的")

    private val mIcons = arrayOf(
        R.raw.video,
        R.raw.discover,
        R.raw.mine
    )

    private val mTabEntities: ArrayList<CustomBottomTabEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainBtwln: BottomTabWithLottieNavigation = findViewById(R.id.main_btwln)
        val mainBtwln1: BottomTabWithLottieNavigation = findViewById(R.id.main_btwln_1)
        val mainBtwln2: BottomTabWithLottieNavigation = findViewById(R.id.main_btwln_2)
        val mainBtwln3: BottomTabWithLottieNavigation = findViewById(R.id.main_btwln_3)

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIcons[i]))
        }

        mainBtwln.configBbwln()
        mainBtwln1.configBbwln()
        mainBtwln2.configBbwln()
        mainBtwln3.configBbwln()
    }

    fun BottomTabWithLottieNavigation.configBbwln() {
        setTabItems(mTabEntities)
        setOnTabSelectListener(this@MainActivity)
    }

    override fun onTabSelect(position: Int) {
        Toast.makeText(this@MainActivity, "onTabSelect  $position", Toast.LENGTH_SHORT).show()
    }

    override fun onTabReselect(position: Int) {
        Toast.makeText(this@MainActivity, "onTabReselect  $position", Toast.LENGTH_SHORT).show()
    }

    fun onFragmentSelect(view: View) {
        startActivity(Intent(this@MainActivity, FgActivity::class.java))
    }

    fun onVpFgSelect(view: View) {
        startActivity(Intent(this@MainActivity, VpFgActivity::class.java))
    }

}