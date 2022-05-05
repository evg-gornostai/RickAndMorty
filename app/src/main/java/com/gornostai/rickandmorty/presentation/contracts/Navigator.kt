package com.gornostai.rickandmorty.presentation.contracts

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator{
    if (requireActivity() is Navigator){
        return requireActivity() as Navigator
    } else {
        throw RuntimeException("Activity is not Navigator")
    }
}

interface Navigator {

    fun setFragment(
        newFragment: Fragment,
        addToBackStack: Boolean = false,
        popBackStack: Boolean = false
    )
}