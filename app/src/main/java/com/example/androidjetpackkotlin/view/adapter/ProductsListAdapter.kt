package com.example.androidjetpackkotlin.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidjetpackkotlin.R
import com.example.androidjetpackkotlin.databinding.ItemsProductsBinding
import com.example.androidjetpackkotlin.model.Model
import com.example.androidjetpackkotlin.utils.getProgressDrawable
import com.example.androidjetpackkotlin.utils.loadImage
import com.example.androidjetpackkotlin.view.ProductClickListener
import kotlinx.android.synthetic.main.items_products.view.*

class ProductsListAdapter(val productList:ArrayList<Model.Products>):RecyclerView.Adapter<ProductsListAdapter.ProductListViewHolder>(),ProductClickListener {

    fun updateProductList(newUserList: List<Model.Products>) {
        productList.clear()
        productList.addAll(newUserList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemsProductsBinding>(inflater, R.layout.items_products, parent, false)
        return ProductListViewHolder(view);
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
       holder.view.product = productList[position]
        holder.view.listener = this
    }

    override fun onProductClicked(v: View) {
        val id = v.productId.text.toString().toInt()
        val salePrice = v.salePrice.text.toString()
        val args = Bundle()
        args.putInt("id", id)
        args.putString("salePrice", salePrice)
        Navigation.findNavController(v).navigate(R.id.actionDetailFragment, args)
    }

    class ProductListViewHolder(var view:ItemsProductsBinding) : RecyclerView.ViewHolder(view.root)

}