package com.example.androidjetpackkotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidjetpackkotlin.R
import com.example.androidjetpackkotlin.databinding.FragmentDetailBinding
import com.example.androidjetpackkotlin.model.ProductDetail
import com.example.androidjetpackkotlin.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment()  {

    private lateinit var viewModel: DetailsViewModel

    private var productid = 0
    private lateinit var salePrice : String

    private lateinit var dataBinding: FragmentDetailBinding
    private var currentProduct:ProductDetail? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        productid= args?.getInt("id",0)!!
        salePrice = args?.getString("salePrice","")

        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        viewModel.loading.value = true
        viewModel.fetchProductDetails(productid)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.productLiveData.observe(viewLifecycleOwner, Observer { product ->
           currentProduct = product
            product?.let {
                mainLayout.visibility = View.VISIBLE
                discountPrice.text = if(salePrice.isNullOrBlank() || salePrice.equals("null",true)) product.regular_price?.display_string else salePrice
                dataBinding.product = product

            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {isLoading ->
            isLoading?.let {
                loader.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    mainLayout.visibility = View.GONE
                }
            }
        })


    }

}