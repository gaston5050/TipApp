package com.example.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipapp.ui.theme.TipAppTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipAppTheme {

                        tipApp()


                }
            }
        }
    }

//Desarrollo del ejercico con dos funciones
@Composable
fun tipApp(){
    calcularPropina2()
}


//Funcion padre
@Composable
fun calcularPropina2(){

    var montoIngresado by remember{ mutableStateOf("")}
    var monto: Double = montoIngresado.toDoubleOrNull() ?: 0.0

    var porcentajeIngresado by remember{ mutableStateOf("")}

    var porcentaje: Double = porcentajeIngresado.toDoubleOrNull() ?: 0.0



    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(text= stringResource(R.string.calculate_tip), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(16.dp))

        Text(text= "Subtotal")
        ingresoDatos(montoIngresado, {montoIngresado = it})

        Text(text= stringResource(R.string.porcentaje_de_propina))

        IngresoPorcentaje(porcentajeIngresado, {porcentajeIngresado = it})


        Spacer(modifier = Modifier.padding(16.dp))
        if(monto != 0.0 && porcentaje != 0.0) {
            Text(text = "Total", fontWeight = FontWeight.Bold)
            Text(
                text = "%.2f".format(calculoPropina(monto, porcentaje)),
                fontWeight = FontWeight.Bold
            )
        }


    }



}

fun calculoPropina(monto: Double, porcentaje: Double): Double{

    return monto + (monto * porcentaje)/100

}


@Composable
fun ingresoDatos(
    montoIngresado: String,
    cambioMonto: (String) -> Unit,
    modifier: Modifier = Modifier
){

    TextField(
        value = montoIngresado,
        onValueChange = cambioMonto,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        modifier = modifier
    )



}
@Composable
fun IngresoPorcentaje(porcentajeIngresado: String,
                      cambioPorcentaje: (String) -> Unit,
                      modifier: Modifier = Modifier){

    TextField(
        value = porcentajeIngresado,
        onValueChange = cambioPorcentaje,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
        modifier = modifier

    )
}






//Aca desarrolle todo el ejercicio en una sola funcion
/*
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
*/

@Preview(showBackground = true)
@Composable
fun previsualizacion() {
//    calcularPropina()
    calcularPropina2()
}
/*
fun editarCampoPropina(modifier: Modifier){
    TextField(value = ""
        , onValueChange = {}
        , modifier = modifier)
}
 */