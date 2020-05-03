package com.example.languagetranslator.model
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.languagetranslator.view.AboutFragment
import com.example.languagetranslator.view.TranslateFragment
import com.example.languagetranslator.view.AlphabetsFragment
import com.example.languagetranslator.view.ListFragment

class IntroViewPagerAdapter(supportFragmentManager: androidx.fragment.app.FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager)
{
    override fun getItem(position: Int): Fragment{

        Log.d("Position","This is the position $position")
        when (position) {
            0 -> {
                    return ListFragment()
            }
            1 -> {
                    return TranslateFragment()
            }
            2 -> {
                  return AlphabetsFragment()
                }
            }
        return ListFragment()
    }
    override fun getCount(): Int
    {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "LIST"
            1 -> return "TRANSLATE"
            2 -> return "ALPHABETS"
        }
        return null
    }

}