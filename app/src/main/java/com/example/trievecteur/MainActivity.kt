package com.example.trievecteur

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                window.navigationBarColor = Color.Black.toArgb()// changer la couleur des touches en noir
//            }
//            window.statusBarColor = Color.Black.toArgb()
            Greeting(this)
        }
    }
}


@Composable
fun Greeting(context: MainActivity) {

    val BoldTheme = darkColors(
        primary = Color(0xFF24AF71  ),
        primaryVariant = Color(0xFF24AF71),
        onPrimary = Color.White,
        surface = Color.White,
        background = Color.White,
    )

    var tabElement = mutableListOf<Int>()
    var vecteur by remember { mutableStateOf(TextFieldValue()) }
    var elements by remember { mutableStateOf(TextFieldValue()) }
    var outPut by remember { mutableStateOf(String()) }
    tabElement = ElementsVecteur(vecteur, context)
    
    MaterialTheme(colors = BoldTheme){
        Scaffold(
            topBar ={
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = "ALGORITHME",
                                modifier = Modifier.padding(20.dp,20.dp,20.dp,0.dp),
                                style = TextStyle(
                                    fontSize = 32.sp,
                                    letterSpacing = 0.12.em,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                            Text(
                                text = "trie vecteur",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
                                fontStyle = FontStyle.Italic
                            )
                        }
                    },
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxSize(),
                    backgroundColor = Color(0xFF24AF71)
                )
            },
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color(0xFF24AF71),
                    elevation = 10.dp
                ) {
                    BottomNavigationItem(
                        selected = true,
                        onClick = {
                            tabElement = mutableListOf<Int>()
                            outPut = ""
                            vecteur = TextFieldValue()
                        },
                        icon ={ Icon(Icons.Default.Refresh,contentDescription ="Refresh")},
                        label = { Text(
                            text = "Refresh",
                            style = MaterialTheme.typography.body1
                        )},
                        selectedContentColor = Color.White,
      git                   unselectedContentColor = Color(0xff206496)
                    )
                    BottomNavigationItem(
                        selected = true,
                        onClick = {
                            tabElement = trieVecteur(tabElement)
                            outPut = output(tabElement)
                        },
                        icon ={ Icon( Icons.Default.PlayArrow, contentDescription = "PlayArrow")},
                        label = { Text(
                            text = "PlayArrow",
                            style = MaterialTheme.typography.body1
                        )},
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color(0xff206496)
                    )
                }
            },
            content = {
                val scrollState = rememberScrollState()
                val scrolbox1 = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.Center,

                    ) {

                    Spacer(modifier = Modifier.height(25.dp))

                    Column() {
                        Text(
                            text = "Output",
                            style = MaterialTheme.typography.button,
                            color = Color(0xFF206496),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Right
                        )
                        Box(
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                .shadow(
                                    elevation = 10.dp,
                                    shape = CutCornerShape(topEnd = 32.dp, bottomStart = 32.dp),
//                    shadowColor
                                    clip = false,
                                )
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxWidth()
                                .height(165.dp)
                                .background(Color.White)
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF206496),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(0.dp, 25.dp,  0.dp,25.dp),

                            ){
                            Text(

                                text = outPut,
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .semantics {
                                        // Ajoute un identifiant sémantique à l'élément
                                        this.contentDescription = "monTexteId"
                                    }.verticalScroll(scrolbox1),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.ExtraBold,
                                style = MaterialTheme.typography.h5
                            )
                        }
                    }


                    Spacer(modifier = Modifier.height(50.dp))

                    Text(
                        text = "Input",
                        style = MaterialTheme.typography.button,
                        color = Color(0xFF206496),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Right
                    )
                    Row(
                        modifier = Modifier
                            .padding(0.dp,0.dp,0.dp,0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 10.dp,
                                    RoundedCornerShape(10.dp, 0.dp, 0.dp, 0.dp,)
                                )
                                .width(80.dp)
                                .height(55.dp)
                                .background(Color(0xFF206496))
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF206496),
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {

                            Text(
                                text = "nombre Elements",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(4.dp)
                            )

                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 10.dp,
                                    RoundedCornerShape(0.dp, 10.dp, 0.dp, 0.dp,)
                                )
                                .fillMaxWidth()
                                .height(55.dp)
                                .background(Color(0xFF206496))
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF206496),
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp,4.dp,0.dp,0.dp,),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "VECTEUR D'ENTRER",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.body1
                                )
                                Text(
                                    text = "Delimiter avec “,”  EXEMPLE  : 10,14,23, ",
                                    style = TextStyle(
                                        fontSize = 10.sp
                                    )
                                )
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 10.dp,
                                    RoundedCornerShape(0.dp, 0.dp, 0.dp, 10.dp,)
                                )
                                .width(80.dp)
                                .height(55.dp)
                                .background(Color(0xFF24AF71))
                        ) {
                            Text(
                                text =" ${tabElement.size} ",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .align(Alignment.CenterStart)

                                    .padding(10.dp),
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 10.dp,
                                    RoundedCornerShape(0.dp, 0.dp, 10.dp, 0.dp,)
                                )
                                .fillMaxWidth()
                                .height(55.dp)
                                .background(Color(0xFF24AF71))
                        ) {
                            TextField(
                                value = vecteur,
                                onValueChange ={vecteur = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                placeholder = { Text(text = "10,20,30,")},
                                textStyle = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            })
    }

}

fun trieVecteur(arr : MutableList<Int>):MutableList<Int>{
    if(arr.size <100){
        selectionSort(arr)
    }else{
        quickSort(arr, 0, arr.size -1)
    }
    return arr
}

fun quickSort(arr: MutableList<Int>, low: Int, high: Int) {
    if (low < high) {
        val pi = randomizedPartition(arr, low, high)
        quickSort(arr, low, pi - 1)
        quickSort(arr, pi + 1, high)
    }
}

fun randomizedPartition(arr: MutableList<Int>, low: Int, high: Int): Int {
    val pivotIndex = Random.nextInt(low, high + 1)
    val pivot = arr[pivotIndex]
    var i = low - 1
    for (j in low until high) {
        if (arr[j] < pivot) {
            i++
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    val temp = arr[i + 1]
    arr[i + 1] = arr[pivotIndex]
    arr[pivotIndex] = temp
    return i + 1
}

fun ElementsVecteur(Vecteur: TextFieldValue, content: Context): MutableList<Int> {
    var TabVect = mutableListOf<Int>()
    var comptEle:Int = 1
    var elt =""
    Vecteur.text.forEach {
        if (it.toString() == ","){
            if ( elt.toBigIntegerOrNull() !=null){
                TabVect.add(elt.toInt())
                comptEle ++
                elt = ""
            }else{
                Toast.makeText(content, "nous avons rencontrez une erreur au ${TabVect.size+1} element", Toast.LENGTH_SHORT).show()
                return TabVect
            }
        }else{
            elt +=it

        }
    }
    return TabVect

}

fun output(TabElemt : MutableList<Int>): String {
    var Out = ""
    TabElemt.forEach{
        Out += " ${it.toString()} "
    }
    return Out
}

fun selectionSort(arr: MutableList<Int>) {
    for (i in 0 until arr.size - 1) {
        var minIndex = i
        for (j in i + 1 until arr.size) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j
            }
        }
        if (minIndex != i) {
            val temp = arr[i]
            arr[i] = arr[minIndex]
            arr[minIndex] = temp
        }
    }
}