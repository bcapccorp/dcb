package bcapc.corp.dcb.control

import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
import android.telecom.PhoneAccount
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import bcapc.corp.dcb.R
import java.util.UUID

class DCBPhoneAccountManager(private val context: Context) {
    companion object {
        const val TAG = "DCBPhoneAccountManager"
    }

    private val dcbSharedPreferences =  DCBSharedPreferences(context)

    fun setupPhoneAccount(context: Context) {
        val uuid = if (dcbSharedPreferences.dataExists(TAG)) {
            dcbSharedPreferences.loadStringData(TAG)
        } else {
            UUID.randomUUID().toString()
        }
        val phoneAccount = createPhoneAccount(uuid)
        val manager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        manager.registerPhoneAccount(phoneAccount)
    }

    private fun createPhoneAccount(uuid: String): PhoneAccount {
        val phoneAccountHandle = PhoneAccountHandle(
            ComponentName(context, DCBService::class.java),
            uuid
        )
        val phoneAccountBuilder = PhoneAccount.builder(
            phoneAccountHandle,
            context.getString(R.string.app_name)
        )
            .setCapabilities(PhoneAccount.CAPABILITY_SELF_MANAGED)
//            .setCapabilities(PhoneAccount.CAPABILITY_CALL_PROVIDER or PhoneAccount.CAPABILITY_SELF_MANAGED)
            .setHighlightColor(ContextCompat.getColor(context, R.color.teal_200))
            .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher_round))
            .setShortDescription(context.getString(R.string.app_name))

        val extras = Bundle()
        extras.putString(TelephonyManager.EXTRA_INCOMING_NUMBER, "1234567890")
        phoneAccountBuilder.setExtras(extras)

        return phoneAccountBuilder.build()
    }
}