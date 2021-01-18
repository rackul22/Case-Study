package com.example.androidjetpackkotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidjetpackkotlin.R
import com.example.androidjetpackkotlin.view.adapter.ProductsListAdapter
import com.example.androidjetpackkotlin.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel:ListViewModel
    private val productListAdapter = ProductsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        productList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productListAdapter
        }

        refresh_layout.setOnRefreshListener {
            productList.visibility = View.GONE
            listError.visibility = View.GONE
            loader.visibility = View.VISIBLE
            viewModel.refresh()
            refresh_layout.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.deals.observe(viewLifecycleOwner, Observer {deals ->
            deals?.let {
                productList.visibility = View.VISIBLE
                productListAdapter.updateProductList(deals)
            }
        })

        viewModel.loadError.observe(viewLifecycleOwner, Observer {isError ->
            isError?.let {
                 listError.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {isLoading ->
            isLoading?.let {
                 loader.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    listError.visibility = View.GONE
                    productList.visibility = View.GONE
                }
            }
        })
    }
}