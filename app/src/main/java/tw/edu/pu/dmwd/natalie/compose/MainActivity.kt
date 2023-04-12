package tw.edu.pu.dmwd.natalie.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import tw.edu.pu.dmwd.natalie.compose.ui.theme.ComposeTheme
data class Points(

    val x: Float,

    val y: Float

)
class MainActivity:ComponentActivity(){
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ComposeTheme {
                //A surface container using the 'background' color from the theme
                Surface (modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background){
                    Greeting("Android")
                }
            }
        }
    }
}




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Greeting(name: String) {

    var X = remember { mutableStateListOf(0f) }

    var Y = remember { mutableStateListOf(0f) }

    var Fingers = remember { mutableStateOf (0) }
    val numberone = ImageBitmap.imageResource(id = R.drawable.n1)
    val numbertwo = ImageBitmap.imageResource(id = R.drawable.n2)
    val numberthree = ImageBitmap.imageResource(id = R.drawable.n3)
    val numberfour = ImageBitmap.imageResource(id = R.drawable.n4)
    val numberfive = ImageBitmap.imageResource(id = R.drawable.n5)

    var numbers = arrayListOf(

        numberone,numbertwo, numberthree, numberfour,

        numberfive    )
    Box(

        modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter { event ->

                Fingers.value = event.getPointerCount()

                X.clear()

                Y.clear()

                for (i in 0..Fingers.value - 1) {

                    X.add( event.getX(i))

                    Y.add (event.getY(i))

                }
                true

            }

    ){

        Canvas(modifier = Modifier){

                //drawCircle(Color.Yellow, 100f, Offset(X.value, Y.value))
            for (i in 0..Fingers.value - 1) {

                drawImage(numbers[i%5],Offset(X[i]-numberone.width/2, Y[i]-numberone.height/2))

            }

            }

        }

    }


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Greeting("Android")
    }
}