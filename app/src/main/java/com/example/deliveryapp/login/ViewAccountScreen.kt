import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.R
import com.example.deliveryapp.logindatabase.Account
import com.example.loginapp.ui.theme.AccountViewModel

@Composable
fun ViewAccountScreen(navController: NavController,viewModel: AccountViewModel){

    val accountList by viewModel.accountList.observeAsState()
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordConfirmation by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var Id by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        // Input fields and Add button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier= Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )
            OutlinedTextField(
                modifier= Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )
            OutlinedTextField(
                modifier= Modifier.fillMaxWidth(),
                value = passwordConfirmation,
                onValueChange = { passwordConfirmation = it },
                label = { Text("Confirm Password") }
            )
            OutlinedTextField(
                modifier= Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )
            OutlinedTextField(
                modifier= Modifier.fillMaxWidth(),
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") }
            )
            OutlinedTextField(
                modifier= Modifier.fillMaxWidth(),
                value = Id,
                onValueChange = { Id = it },
                label = { Text("ID") }
            )
            Button(
                onClick = {

                    viewModel.addAccount( username,password,passwordConfirmation,
                        email,phoneNumber,Id)
                    username = ""
                    password = ""
                    passwordConfirmation = ""
                    email = ""
                    phoneNumber = ""
                    Id = ""
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Add Account")
            }
        }

        // Displaying the list of accounts
        accountList?.let { accounts ->
            LazyColumn {
                itemsIndexed(accounts) { _, item ->
                    AccountItem(item = item, onDelete = {
                        viewModel.deleteAccount(item.id)
                    })
                }
            }
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No items yet",
            fontSize = 16.sp
        )
    }
}

@Composable
fun AccountItem(item: Account, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "ID: ${item.id}",
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(
                text = "Username: ${item.username}",
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = "Password: ${item.password}",
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = "Confirm Password: ${item.passwordConfirmation}",
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = "Email: ${item.email}",
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = "Phone: ${item.phoneNumber}",
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.trash),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}