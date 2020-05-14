package com.example.languagetranslator.view

import android.Manifest
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Network
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private val STORAGE_REQUEST_CODE = 101

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPermissions()

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

            }

        })
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)

        Log.e("Value", ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE).toString());

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("MainActivity", "Permission to store denied")
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE))
            {
                showPermissionReasonAndRequest(
                        "Notice",
                        "Hi, we will request STORAGE permission. " +
                                "This is required for authenticating your device, " +
                                "please grant it.",
                        WRITE_EXTERNAL_STORAGE,
                        STORAGE_REQUEST_CODE
                )
            }
            else
            {
                makeRequest()
            }
        }
        else
        {
            gettingDataFromTheApi()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(WRITE_EXTERNAL_STORAGE),
            STORAGE_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        Log.e("Value", ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE).toString());
        if (isUserCheckNeverAskAgain()) {
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return
        }

        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

            Log.i("TAG", "Permission has been denied by user")
        } else {
            gettingDataFromTheApi()
            Log.i("TAG", "Permission has been granted by user")

        }

    }

    private fun isUserCheckNeverAskAgain() =
        !ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
    private fun Activity.showPermissionReasonAndRequest(
        title: String,
        message: String,
        permission: String,
        requestCode: Int
    ) {

        val builder = AlertDialog.Builder(this)

        builder.setTitle(title)

        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes"){dialogInterface, which ->
            ActivityCompat.requestPermissions(
                    this@showPermissionReasonAndRequest,
                    arrayOf(permission),
                    requestCode
            )
        }

        builder.setNegativeButton("No"){dialogInterface, which ->
        }

        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun gettingDataFromTheApi()
    {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val work  = OneTimeWorkRequestBuilder<AudioDataWorker>()
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance().enqueue(work)

        WorkManager.getInstance().getWorkInfoByIdLiveData(work.id)
                .observe(this, Observer { workInfo ->
                    // Check if the current work's state is "successfully finished"
                    if (workInfo != null && workInfo.state.isFinished)
                    {
                        if(workInfo.state == WorkInfo.State.SUCCEEDED)
                        {
                            val alert= androidx.appcompat.app.AlertDialog.Builder(this)
                            val factory = LayoutInflater.from(this)
                            val view: View = factory.inflate(R.layout.alert_dialog, null)
                            alert.setCancelable(true)
                            alert.setView(view)
                            alert.show()
                        }

                    }
                })
    }



}