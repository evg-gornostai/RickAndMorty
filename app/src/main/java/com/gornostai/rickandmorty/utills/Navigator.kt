package com.gornostai.rickandmorty.utills

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gornostai.rickandmorty.R

object Navigator {
    var currentFragment: Fragment? = null

    fun setFragment(
        newFragment: Fragment,
        activity: AppCompatActivity,
        addToBackStack: Boolean = false
    ) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.replace(R.id.main_fragment_container, newFragment)
        transaction.commit()
        currentFragment = newFragment
    }
}