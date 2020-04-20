package com.example.languagetranslator.model


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.languagetranslator.view.TranslateFragment
import com.example.languagetranslator.view.AboutFragment
import com.example.languagetranslator.view.ListFragment


class IntroViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager)
{
    override fun getItem(position: Int): Fragment {

        Log.d("Position","This is the position $position")
        when (position) {
            0 -> return TranslateFragment()
            1 -> return ListFragment()
            2 -> return AboutFragment()
        }
        return ListFragment() //does not happen
    }
    override fun getCount(): Int
    {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "HOME"
            1 -> return "A-Z"
            2 -> return "ABOUT"
        }
        return null
    }

}