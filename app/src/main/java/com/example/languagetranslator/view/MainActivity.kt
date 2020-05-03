package com.example.languagetranslator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.work.*
import com.example.languagetranslator.R
import com.example.languagetranslator.model.IntroViewPagerAdapter
import com.example.languagetranslator.presenter.ViewModelFactory
import com.example.languagetranslator.presenter.ZoomOutPageTransformer
import com.example.languagetranslator.work.AudioDataWorker
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() , HasSupportFragmentInjector
{
//    @Inject
//    lateinit var mViewModelFactory : ViewModelFactory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        WorkManager.getInstance().enqueue(
            OneTimeWorkRequestBuilder<AudioDataWorker>().build()
        )
        val introViewPagerAdapter = IntroViewPagerAdapter(supportFragmentManager)
        vpIntro.adapter = introViewPagerAdapter
        vpIntro.setPageTransformer(true,
            ZoomOutPageTransformer()
        )

        tabLayout.setupWithViewPager(vpIntro, true);

        vpIntro.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                Log.d("Scrolling","Scrolling is happening at position ${position}");
            }

            override fun onPageSelected(position: Int) {
//                if (position === vpIntro.getAdapter()?.getCount()?.minus(1)) {
//                    val reg = Intent(this@MainActivity, Splash::class.java)
//                    startActivity(reg)
//                }
            }

        })
    }
}