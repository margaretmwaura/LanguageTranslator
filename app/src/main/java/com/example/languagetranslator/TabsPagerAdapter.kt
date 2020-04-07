package com.example.languagetranslator


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter


class IntroViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager)
{
    override fun getItem(position: Int): Fragment {

        Log.d("Position","This is the position $position")
        when (position) {
            0 -> return ListFragment()
            1 -> return AboutFragment()
        }
        return ListFragment() //does not happen
    }
    override fun getCount(): Int
    {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }

}