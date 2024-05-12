package com.example.deliveryapp.database

//@Database(entities = [Customer::class, Address::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
//abstract class CustomerAddressListDatabase: RoomDatabase() {
//    abstract fun customerAddressDao(): CustomerAddressDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: CustomerAddressListDatabase? = null
//
//        fun getInstance(context: Context): CustomerAddressListDatabase {
//            val userInstance = INSTANCE
//            if (userInstance != null) {
//                return userInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    CustomerAddressListDatabase::class.java,
//                    "customerAddressList_database"
//                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
//
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}

