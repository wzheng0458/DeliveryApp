package com.example.deliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryapp.ui.theme.DeliveryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val addressViewModel = ViewModelProvider(this)[AddressViewModel::class.java]
        setContent {
            DeliveryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController , startDestination = Routes.AddressListComponent ){
                        composable(Routes.AddressListComponent){
                            AddressListComponent(addressViewModel, navController)
                        }
                        composable(Routes.MapScreenComponent+"/{id}"+"/{address}"){
                            val id = it.arguments?.getInt("id")
                            val address = it.arguments?.getString("address")

                            MapScreenComponent(navController, id, address, addressViewModel)
                        }

                    }

                }
            }
        }
    }
}


