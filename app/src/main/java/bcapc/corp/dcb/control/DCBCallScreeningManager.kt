package bcapc.corp.dcb.control

import android.annotation.TargetApi
import android.app.Activity
import android.app.Service
import android.app.role.RoleManager
import android.content.Intent
import android.os.Build
import android.telecom.TelecomManager
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import bcapc.corp.dcb.R
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log


class DCBCallScreeningManager {

    companion object {
        const val TAG = "DCBCallScreeningManager"
        const val REQUEST_ID = 1
    }

    private fun getCurrentDialerPackage(activity: ComponentActivity) =
        activity.getSystemService(TelecomManager::class.java).defaultDialerPackage

    fun setupCallScreening(activity: ComponentActivity) {
        val code = Build.VERSION.SDK_INT
        if (
            getCurrentDialerPackage(activity) !== activity.packageName
        ) {
            when {
                code < Build.VERSION_CODES.Q -> {
                    changeOldPhoneDealer(activity)
                }

                code >= Build.VERSION_CODES.Q -> {
                    changeNewPhoneDealer(activity)
                }
            }
        } else {
            Log.d(TAG, "Current Dialer is DCB")
        }

    }

    private fun changeOldPhoneDealer(activity: ComponentActivity) {
        val changedDialer = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
        changedDialer.putExtra(
            TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME,
            activity.packageName
        )
        startActivityForResult(activity, changedDialer) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (getCurrentDialerPackage(activity) == activity.packageName) {
                    Toast.makeText(
                        activity, R.string.default_dial_change_refuse, Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    activity, R.string.default_dial_change_refuse, Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.Q)
    private fun changeNewPhoneDealer(activity: ComponentActivity) {
        val roleManager = activity.getSystemService(Service.ROLE_SERVICE) as RoleManager
        val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
        startActivityForResult(activity, intent) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (getCurrentDialerPackage(activity) == activity.packageName) {
                    Toast.makeText(
                        activity, R.string.default_dial_change_refuse, Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    activity, R.string.default_dial_change_refuse, Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun startActivityForResult(
        activity: ComponentActivity,
        intent: Intent,
        result: (ActivityResult) -> Unit
    ) {
        activity.run {
            activity.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult(), result
            ).launch(intent)
        }
    }


}