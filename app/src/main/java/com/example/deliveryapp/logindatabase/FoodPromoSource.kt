package com.example.deliveryapp.logindatabase

import com.example.deliveryapp.R

class FoodPromoSource {
    fun loadFoodPromoData(): List<FoodPromoData> {
        return listOf<FoodPromoData>(
            FoodPromoData(R.drawable.spaghettibolognese),
            FoodPromoData(R.drawable.americanburger),
            FoodPromoData(R.drawable.nasilemak),
            FoodPromoData(R.drawable.chickenrice),
            FoodPromoData(R.drawable.roticanai),
            FoodPromoData(R.drawable.icedcoffee)
        )
    }
}
