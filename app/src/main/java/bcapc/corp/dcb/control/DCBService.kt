package bcapc.corp.dcb.control

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.telecom.ConnectionService

class DCBService : ConnectionService() {
    companion object {
        private val TAG = DCBService::class.java.simpleName
    }



}