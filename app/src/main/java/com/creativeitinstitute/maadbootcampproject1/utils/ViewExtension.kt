package com.creativeitinstitute.maadbootcampproject1.utils

import android.view.View
import android.widget.TextView
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

fun TextView.showChar(
    lifecycleOwner: LifecycleOwner,
    charMsg: LiveData<String>,
) {
    charMsg.observe(lifecycleOwner) { msg ->
        if (msg.isEmpty()) {
            inVisible()
        } else {
            visible()
            text = "${msg.length} char"
        }
    }
}
fun String?.toTrimedString(): String{
   return this.toString().trim()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}


