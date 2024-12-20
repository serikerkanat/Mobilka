package com.example.assik2
ahahahahhahahaha
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assik2.ui.theme.Assik2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assik2Theme {
                MiniCalculator()
            }
        }
    }
}

@Composable
fun MiniCalculator() {
    val num1 = remember { mutableStateOf("") }
    val num2 = remember { mutableStateOf("") }
    val result = remember { mutableStateOf("") }
    val operation = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Enter first number:")
        TextField(
            value = num1.value,
            onValueChange = { num1.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Enter second number:")
        TextField(
            value = num2.value,
            onValueChange = { num2.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Enter operation (+, -, *, /):")
        TextField(
            value = operation.value,
            onValueChange = { operation.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (num1.value.isNotEmpty() && num2.value.isNotEmpty() && operation.value.isNotEmpty()) {
            val number1 = num1.value.toDoubleOrNull()
            val number2 = num2.value.toDoubleOrNull()

            if (number1 != null && number2 != null) {
                when (operation.value) {
                    "+" -> result.value = (number1 + number2).toString()
                    "-" -> result.value = (number1 - number2).toString()
                    "*" -> result.value = (number1 * number2).toString()
                    "/" -> if (number2 != 0.0) {
                        result.value = (number1 / number2).toString()
                    } else {
                        result.value = "Error: Division by zero"
                    }
                    else -> result.value = "Invalid operation"
                }
            } else {
                result.value = "Invalid input. Please enter valid numbers."
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${result.value}")
    }
}

@Preview(showBackground = true)
@Composable
fun MiniCalculatorPreview() {
    Assik2Theme {
        MiniCalculator()
    }
}
