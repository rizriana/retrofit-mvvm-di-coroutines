package id.backup.mvvm.utils

import android.view.View

object Ext {
    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }
}