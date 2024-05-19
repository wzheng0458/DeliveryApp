package com.example.deliveryapp.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.R
import com.example.deliveryapp.Screens
import com.example.deliveryapp.login.AccountViewModel
import com.example.deliveryapp.logindatabase.AccountEvent
import com.example.deliveryapp.logindatabase.AccountState


@Composable
fun ViewAccountScreen(
    state: AccountState,
    navController: NavController,
    viewModel: AccountViewModel,
    onEvent: (AccountEvent) -> Unit
) {

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                IconButton(onClick = { onEvent(AccountEvent.SortAccount) }) {
                    Icon(
                        imageVector = Icons.Rounded.Sort,
                        contentDescription = "Sort Account",
                        modifier = Modifier.size(35.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.username.value = ""
                state.password.value = ""
                state.passwordConfirmation.value = ""
                state.email.value = ""
                state.phoneNumber.value = ""
                state.id.value = ""
                navController.navigate(Screens.AddAccountScreen.name)
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add new account")
            }
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.account.size) { index ->
                AccountItem(
                    state = state,
                    index = index,
                    onEvent = onEvent
                )
            }

        }

    }

}

@Composable
fun AccountItem(
    state: AccountState,
    index: Int,
    onEvent: (AccountEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = state.account[index].username,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.account[index].password,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = state.account[index].passwordConfirmation,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = state.account[index].email,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = state.account[index].phoneNumber,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = state.account[index].id,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

        IconButton(
            onClick = {
                onEvent(AccountEvent.DeleteAccount(state.account[index]))
            }
        ) {

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Account",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )

        }

    }
}