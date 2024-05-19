package com.example.deliveryapp.delivery

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun AlertDialogBox(
    onDismissRequest: () -> Unit,
    onConfirmation:() -> Unit,
    dialogTitle: String,
    dialogText: String,
    dialogIcon: ImageVector
){
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = { onConfirmation() }
            )
            {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
        TextButton(
            onClick = { onDismissRequest() }
        )
        {
            Text(text = "Cancel")
        }
    },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        icon = {
           Icon(dialogIcon, contentDescription = "Dialog Icon")
        }
    )
}

