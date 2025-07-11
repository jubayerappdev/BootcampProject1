package com.creativeitinstitute.maadbootcampproject1.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    lifecycleOwner: LifecycleOwner,
    snackBarMsg: LiveData<Int>,
    timeLength: Int = Snackbar.LENGTH_SHORT
) {
    snackBarMsg.observe(lifecycleOwner) { msg ->

        Snackbar.make(this, msg, timeLength).show()


    }


}
fun String?.toTrimedString(): String{
   return this.toString().trim()
}


