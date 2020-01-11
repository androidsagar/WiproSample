package com.example.wiprosample

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.showProgress(show:Boolean){
    this.isRefreshing = show
}

fun Context.showToast(msg:String?){
    Toast.makeText(this, msg?:getString(R.string.something_went_wrong),Toast.LENGTH_SHORT).show()
}

fun showViews(vararg view:View){
    view.forEach { it.visibility = View.VISIBLE }
}

fun hideViews(vararg view:View){
    view.forEach { it.visibility = View.GONE }
}