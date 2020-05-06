package com.myproject.bananaz.model

import com.google.gson.annotations.SerializedName


data class Products (
    @SerializedName("productName")
    val productName: String?,

    @SerializedName("productImage")
    val productImage: Int?,

    @SerializedName("cost")
    val cost: String?
) {
}