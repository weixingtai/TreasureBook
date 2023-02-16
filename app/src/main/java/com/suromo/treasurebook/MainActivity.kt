package com.suromo.treasurebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.suromo.treasurebook.strategy.AllEvenStrategy
import com.suromo.treasurebook.strategy.ITreasureStrategy
import com.suromo.treasurebook.ui.theme.TreasureBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TreasureBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    val strategy: ITreasureStrategy = AllEvenStrategy()
    val list: List<Int> = listOf(1,2,3,4,5,6,7,8,9,0,12)
    strategy.initOpenHistory(list)
    strategy.initPeriod(7)
    strategy.runStrategy()
    val periodList:List<Int> = strategy.getPeriodList()
    val comparedList:List<Boolean> = strategy.getComparedResultList()
    val missPhaseNum = strategy.getMissPhaseNum()
    val prediction = strategy.getNextPrediction()

    Column {
        Text(text = "periodList $periodList")
        Text(text = "comparedList $comparedList")
        Text(text = "missPhaseNum $missPhaseNum")
        Text(text = "prediction $prediction")
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TreasureBookTheme {
        Greeting("Android")
    }
}