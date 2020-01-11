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

        viewModel.feedList.observe(this, Observer {
             when(it){
                 is ResponseWrapper.Loading -> {swipeRefresh.isRefreshing = true}
                 is ResponseWrapper.Success->{
                     swipeRefresh.isRefreshing = false
                     showFeed(it.data)
                 }
             }

        })

        swipeRefresh.setOnRefreshListener {
            viewModel.fetchFeed()
        }
    }

    private fun showFeed(data:MutableList<Feed>){
        recyclerFeed.adapter = FeedAdapter(data)
    }
}
