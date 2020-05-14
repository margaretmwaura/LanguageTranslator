package com.example.languagetranslator.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.languagetranslator.R


class Alert : AppCompatActivity() {
    private var alert : AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_alert)
        Log.d("AlertDialogActivity","AlertDialog activity has been started")

        val alert= AlertDialog.Builder(this)
        val factory = LayoutInflater.from(this)
        val view: View = factory.inflate(R.layout.alert_dialog, null)
        alert.setCancelable(true)
        alert.setView(view)
        alert.show()
    }

}