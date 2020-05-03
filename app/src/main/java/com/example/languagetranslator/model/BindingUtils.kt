package com.example.languagetranslator.model

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("GivenNameInEnglish")
fun TextView.setNameInEnglish(item : WordInstance?)
{
    item?.let {
        text = item!!.name_in_english
    }
}

@BindingAdapter("GivenNameInMyLangauge")
fun TextView.setNameInMyLnaguage(item : WordInstance?)
{
    item?.let {
        text = item!!.name_in_your_language
    }
}