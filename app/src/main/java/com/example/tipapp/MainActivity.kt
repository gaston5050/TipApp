package com.example.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipapp.ui.theme.TipAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipAppTheme {

                        calcularPropina()


                }
            }
        }
    }


@Composable
fun calcularPropina( subtotal: Double = 0.0, modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)) {

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){



        Text(text = "Calculador de propinas", modifier = Modifier.padding(16.dp))

        // uso remember para que el valor actualizado de la Var se guarde entre recomposiciones de la app
        // es mutable para que se pueda actualizar el valor
        var subtotalTexto by remember {
            mutableStateOf("")
        }

        // asigno un valor predeterminado por si la variable es null
        var subtotal = subtotalTexto.toDoubleOrNull()  ?:  0.0
        // firma del metodo lambda  para clacular la propina
        var calcular: (Double) -> Double= {it  + it * 0.15}
        var total: String = "%.2f".format(calcular(subtotal.toDouble()))

        TextField(
            singleLine = true,

            value = subtotalTexto,
            onValueChange = { subtotalTexto = it},
            label = { Text("IMPORTE") }
        )
        //editarCampoPropina()
        Text(text = "$${total}")


    }
}


@Preview(showBackground = true)
@Composable
fun previsualizacion() {
    calcularPropina()
}
/*
fun editarCampoPropina(modifier: Modifier){
    TextField(value = ""
        , onValueChange = {}
        , modifier = modifier)
}
 */