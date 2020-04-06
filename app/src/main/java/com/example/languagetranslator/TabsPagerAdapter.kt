package com.example.languagetranslator


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter


class IntroViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager)
{
    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> return ListFragment() //ChildFragment1 at position 0
            1 -> return AboutFragment() //ChildFragment2 at position 1
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