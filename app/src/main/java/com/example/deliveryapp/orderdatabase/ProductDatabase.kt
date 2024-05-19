package com.example.deliveryapp.orderdatabase


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deliveryapp.R


@Database(entities = [Product::class], version = 2, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
//    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                ).build().also { INSTANCE = it }
            }
        }

        suspend fun insertSampleProducts(context: Context) {
            val productDao = getDatabase(context).productDao()

            val products = listOf(
                Product("B1", "Teh O Ice", 2.00, 2.80, 0, R.drawable.teh_o, "Beverage", 100),
                Product("B2", "Ice Lemon Tea", 2.00, 3.50, 0, R.drawable.ice_lemon_tea, "Beverage", 100),
                Product("B3", "Cham Ice", 2.00, 2.50, 0, R.drawable.cham_ice, "Beverage", 100),
                Product("B4", "Thai Milk Tea", 2.00, 3.00, 0, R.drawable.thai_milk_tea, "Beverage", 100),
                Product("B5", "3 Layer Tea", 2.00, 4.00,  0, R.drawable.__layer_tea, "Beverage", 100),
                Product("B6", "Kopi Ice", 2.00, 2.30, 0, R.drawable.kopi_ice, "Beverage", 100),
                Product("B7", "Milo Ice", 2.00, 3.00,  0, R.drawable.milo_ice, "Beverage", 100),
                Product("B8", "100 Plus", 2.00, 2.50,  0, R.drawable._00plus, "Beverage", 100),
                Product("B9", "Coca Cola", 2.00, 2.50, 0, R.drawable.cola, "Beverage", 100),
                Product("B10", "Soya Bean Milk", 2.00, 2.50,  0, R.drawable.soya_bean_milk, "Beverage", 100),
                Product("F1", "Half Cooked Egg", 1.00, 2.00,  0, R.drawable.half_cooked_egg, "Breakfast", 100),
                Product("F2", "Toast", 1.00, 2.00, 0, R.drawable.toast, "Breakfast", 100),
                Product("F3", "Egg and Toast", 1.50, 2.50,  0, R.drawable.egg_and_toast, "Breakfast", 100),
                Product("F4", "Sandwich", 1.00, 2.50,  0, R.drawable.sandwich, "Breakfast", 100),
                Product("F5", "Chicken Congee", 2.00, 4.00,  0, R.drawable.chicken_congee, "Breakfast", 100),
                Product("F6", "Century Egg and Pork Congee", 3.00, 7.00, 0, R.drawable.century_egg_and_pork_congee, "Breakfast", 100),
                Product("F7", "Barbecue Pork Bun", 1.00, 2.50, 0, R.drawable.barbecue_pork_buns, "Breakfast", 100),
                Product("F8", "Da Bao", 2.00, 5.00,  0, R.drawable.da_bao, "Breakfast", 100),
                Product("F9", "Siomai", 2.00, 4.00,  0, R.drawable.siomai, "Breakfast", 100),
                Product("F10", "Sticky Rice Chicken", 2.00, 4.00, 0, R.drawable.sticky_rice_chicken, "Breakfast", 100),
                Product("C1", "YangZhou Fried Rice", 4.00, 8.00,  0, R.drawable.yangzhou_fried_rice, "Chinese Food", 100),
                Product("C2", "Chicken Rice", 4.00, 10.80,  0, R.drawable.chicken_rice, "Chinese Food", 100),
                Product("C3", "Barbecue Pork Rice", 4.00, 11.00,  0, R.drawable.barbecue_pork_rice, "Chinese Food", 100),
                Product("C4", "Hokkien Mee", 4.00, 10.00,  0, R.drawable.hokkien_mee, "Chinese Food", 100),
                Product("C5", "Shrimp Noodles", 4.00, 10.00,  0, R.drawable.shrimp_noodles, "Chinese Food", 100),
                Product("C6", "Char Kway Teow", 4.00, 10.00,  0, R.drawable.char_kway_teow, "Chinese Food", 100),
                Product("C7", "Chilli Pan Mee Pasta", 4.00, 10.00,  0, R.drawable.chilli_pan_mee_pasta, "Chinese Food", 100),
                Product("C8", "Hot & Spicy Pan Mee Soup", 4.00, 10.00,  0, R.drawable.hot___spicy_pan_mee_soup, "Chinese Food", 100),
                Product("C9", "Bak Kut Teh", 8.00, 17.00,  0, R.drawable.bak_kut_teh, "Chinese Food", 100),
                Product("C10", "Bak Kut Teh and Egg", 8.00, 18.00,  0, R.drawable.bak_kut_teh_and_egg, "Chinese Food", 2),
                Product("M1", "Nasi Lemak Ayam", 4.00, 9.80,  0, R.drawable.nasi_lemak_ayam, "Malaysia Food", 100),
                Product("M2", "Sate", 4.00, 8.00,  0, R.drawable.sate, "Malaysia Food", 100),
                Product("M3", "Roti Canai", 1.00, 2.00,  0, R.drawable.roti_canai, "Malaysia Food", 100),
                Product("M4", "Roti Telur", 1.500, 4.50,  0, R.drawable.roti_telur, "Malaysia Food", 100),
                Product("W1","Spaghetti Bolognese", 4.00, 14.40,  0, R.drawable.spaghetti_bolognese, "Western", 100),
                Product("W2", "Spaghetti Carbonara", 4.00, 15.00,  0, R.drawable.spaghetti_carbonara, "Western", 100),
                Product("W3", "Chicken Chop", 8.00, 15.00,  0, R.drawable.chicken_chop, "Western", 100),
                Product("W4", "Fish And Chisp", 8.00, 15.00,  0, R.drawable.fish_and_chisp, "Western", 100),
                Product("W5", "Beef Chop", 8.00, 17.00,  0, R.drawable.beef_chop, "Western", 0),
                Product("W6", "Burger Chicken", 4.00, 8.00,  0, R.drawable.burger_chicken, "Western", 100),
                Product("W7", "Burger Chicken Special", 4.00, 9.00,  0, R.drawable.burger_chicken_special, "Western", 100),
                Product("W8", "Burger Beef", 4.00, 10.00,  0, R.drawable.burger_beef, "Western", 100),
                Product("W9", "American Burger", 10.00, 20.40,  0, R.drawable.burger_beef_special, "Western", 100),
                Product("W10", "Hotdog", 2.00, 5.00,  0, R.drawable.hotdog, "Western", 100),
                Product("S1", "Nugget", 2.00, 4.00,  0, R.drawable.nugget, "Snack", 100),
                Product("S2", "Fries", 2.00, 4.00,  0, R.drawable.fries, "Snack", 100),
                Product("S3", "Fried Chicken Wings", 2.00, 6.00,  0, R.drawable.fried_chicken_wings, "Snack", 100),
                Product("S4", "Deep Fried Wantan", 2.00, 6.00,  0, R.drawable.deep_fried_wantan, "Snack", 100),
                Product("S5", "Deep Fried Dumpling", 2.00, 6.00,  0, R.drawable.deep_fried_dumpling, "Snack", 100),
            )

            productDao.insertAll(products)
        }
    }
}

