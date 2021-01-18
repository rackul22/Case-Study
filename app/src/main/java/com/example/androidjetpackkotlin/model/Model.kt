package com.example.androidjetpackkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

 class Model {

    private lateinit var products: Array<Products>

    fun getProducts(): Array<Products> {
        return products
    }

    fun setProducts(products: Array<Products>) {
        this.products = products
    }

    override fun toString(): String {
        return "ClassPojo [products = $products]"
    }

    public class Products {

        var uuid:Int? = 0

        private var regular_price: Regular_price? = null

        private var sale_price : Sale_price? = null

        var image_url: String? = null

        var description: String? = null

        var id: Int? = 0

        var title: String? = null

        var aisle: String? = null

        fun getRegular_price(): Regular_price? {
            return regular_price
        }

        fun setSale_price(sale_price: Sale_price?) {
            this.sale_price = sale_price
        }


        fun getSale_price(): Sale_price? {
            return sale_price
        }

        fun setRegular_price(regular_price: Regular_price?) {
            this.regular_price = regular_price
        }

        override fun toString(): String {
            return "ClassPojo [regular_price = $regular_price, image_url = $image_url, description = $description, id = $id, title = $title, aisle = $aisle]"
        }
    }

    class Regular_price {

        var amount_in_cents: String? = null

        var currency_symbol: String? = null

        var display_string: String? = null

        override fun toString(): String {
            return "ClassPojo [amount_in_cents = $amount_in_cents, currency_symbol = $currency_symbol, display_string = $display_string]"
        }
    }

    class Sale_price {

        var amount_in_cents: String? = null

        var currency_symbol: String? = null

        var display_string: String? = null

        override fun toString(): String {
            return "ClassPojo [amount_in_cents = $amount_in_cents, currency_symbol = $currency_symbol, display_string = $display_string]"
        }
    }
}


