package com.myproject.bananaz.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.bananaz.R
import com.myproject.bananaz.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var viewModel = HomeViewModel()
    var productListAdapter = ProductListAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        actionBar.let {
//            title = "yuva"
//        }
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.refresh()

        productsList.apply {
            layoutManager = GridLayoutManager(context, 2)
//            layoutManager =LinearLayoutManager(context)
            adapter =productListAdapter
            setHasFixedSize(true)
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.productsList.observe(this, Observer {

            products -> products?.let {
            productsList.visibility = View.VISIBLE
            productListAdapter.updateProducts(it) }
        })
    }
}
