package com.example.androidjetpackkotlin.model

 class Product {
    private var regular_price: Regular_price? = null
    var image_url: String? = null
    var description: String? = null
    var id: String? = null
    var title: String? = null
    var aisle: String? = null

    fun getRegular_price(): Regular_price? {
        return regular_price
    }

    fun setRegular_price(regular_price: Regular_price?) {
        this.regular_price = regular_price
    }

    override fun toString(): String {
        return "ClassPojo [regular_price = $regular_price, image_url = $image_url, description = $description, id = $id, title = $title, aisle = $aisle]"
    }

    class Regular_price {
    var amount_in_cents: String? = null
    var currency_symbol: String? = null
    var display_string: String? = null
       override fun toString(): String {
       return "ClassPojo [amount_in_cents = $amount_in_cents, currency_symbol = $currency_symbol, display_string = $display_string]"  }
}
}
