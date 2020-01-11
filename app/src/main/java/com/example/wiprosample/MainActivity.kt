package com.example.wiprosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiprosample.adapter.FeedAdapter
import com.example.wiprosample.data_models.Feed
import com.example.wiprosample.retrofit.ResponseWrapper
import com.example.wiprosample.view_models.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.fetchFeed(false)
        recyclerFeed.adapter = FeedAdapter()

        viewModel.feedList.observe(this, Observer {
            when (it) {
                is ResponseWrapper.Loading -> {
                    swipeRefresh.showProgress(true)
                }
                is ResponseWrapper.Success -> {
                    swipeRefresh.showProgress(false)
                    showFeed(it.data.feedList)
                    supportActionBar?.title = it.data.title
                }
                is ResponseWrapper.Error -> {
                    swipeRefresh.showProgress(false)
                    hideViews(recyclerFeed)
                    showViews(group_error)
                    showToast(it.msg)
                }
            }
        })

        btn_reload.setOnClickListener {
            viewModel.fetchFeed(true)
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.fetchFeed(true)
        }
    }

    private fun showFeed(data: List<Feed>) {
        hideViews(group_error)
        showViews(recyclerFeed)
        (recyclerFeed.adapter as FeedAdapter).setData(data)
    }
}
