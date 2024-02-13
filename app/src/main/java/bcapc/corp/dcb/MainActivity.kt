package bcapc.corp.dcb

import android.content.ComponentName
import android.graphics.drawable.Icon
import android.os.Bundle
import android.telecom.PhoneAccount
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import bcapc.corp.dcb.control.DCBCallScreeningManager
import bcapc.corp.dcb.control.DCBService
import bcapc.corp.dcb.control.DCBSharedPreferences
import bcapc.corp.dcb.ui.theme.DecentralizedCallBlockerTheme
import java.util.UUID

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DecentralizedCallBlockerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun setup() {
        val dcbCallScreeningManager = DCBCallScreeningManager()
        dcbCallScreeningManager.setupCallScreening(this)
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DecentralizedCallBlockerTheme {
        Greeting("Android")
    }
}