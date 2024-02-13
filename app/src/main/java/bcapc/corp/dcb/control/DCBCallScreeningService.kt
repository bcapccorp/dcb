package bcapc.corp.dcb.control

import android.annotation.TargetApi
import android.app.Service
import android.app.role.RoleManager
import android.content.Intent
import android.os.IBinder
import androidx.core.app.ActivityCompat.startActivityForResult

//@TargetApi(29)
class DCBCallScreeningService : Service() {

    companion object {
        const val REQUEST_ID = 1
    }

    fun requestRole() {
//        val roleManager = getSystemService(ROLE_SERVICE) as RoleManager
//        val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
//        startActivityForResult(intent, REQUEST_ID)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}