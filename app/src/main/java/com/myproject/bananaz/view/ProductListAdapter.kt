package com.myproject.bananaz.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myproject.bananaz.R
import com.myproject.bananaz.model.Products
import kotlinx.android.synthetic.main.product_item.view.*
import yuvadon.demos.countries.util.getProgressDrawable
import yuvadon.demos.countries.util.loadImage

class ProductListAdapter(var productsList : ArrayList<Products>) :
    RecyclerView.Adapter<ProductListAdapter.ProductsViewHolder>()  {


    fun updateProducts(newProducts: List<Products>){
        productsList.clear()
        productsList.addAll(newProducts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
            holder.bind(productsList[position])
    }

    class ProductsViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val name = view.productName
        private val cost = view.cost
        private val image = view.productImage

        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(product: Products) {
            name.text = product.productName
            cost.text = product.cost
            image.loadImage(product.productImage, progressDrawable)
        }
    }
}