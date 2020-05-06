package com.myproject.bananaz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myproject.bananaz.R
import com.myproject.bananaz.model.Products

class HomeViewModel : ViewModel() {

    val productsList = MutableLiveData<List<Products>>()

    fun refresh(){
        fetchProductDetails()
    }

    fun fetchProductDetails(){


        val mockData = listOf(Products("Banana",R.drawable.banana1, "500"),
            Products("Banana2" ,R.drawable.banana4, "600"),
            Products("Banana3",R.drawable.banana1, "800"),
            Products("Banana4",R.drawable.banana4, "700")
        )

//        productsList.postValue(mockData)
        productsList.value = mockData
    }

}