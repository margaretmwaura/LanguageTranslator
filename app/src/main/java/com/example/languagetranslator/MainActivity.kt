package com.example.languagetranslator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val introViewPagerAdapter = IntroViewPagerAdapter(supportFragmentManager)
        vpIntro.adapter = introViewPagerAdapter
        vpIntro.setPageTransformer(true,
            ZoomOutPageTransformer()
        )

        tabLayout.setupWithViewPager(vpIntro, true);

        val bg_color = resources.getIntArray(R.array.bg_color)

        vpIntro.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                Log.d("Scrolling","Scrolling is happening");
            }

            override fun onPageSelected(position: Int) {
                if (position === vpIntro.getAdapter()?.getCount()?.minus(1)) {
                    val reg = Intent(this@MainActivity, Splash::class.java)
                    startActivity(reg)
                }
            }

        })
    }
}