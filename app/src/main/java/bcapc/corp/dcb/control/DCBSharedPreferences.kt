package bcapc.corp.dcb.control

import android.content.Context
import android.content.SharedPreferences
import bcapc.corp.dcb.GenericConstants
import bcapc.corp.dcb.GenericConstants.EMPTY_STRING

class DCBSharedPreferences(
    context:Context,
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "DCB - Decentralized Call Blocker Preferences",
        Context.MODE_PRIVATE
    )
) {
    fun saveStringData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // Função para carregar dados das SharedPreferences
    fun loadStringData(key: String): String {
        return sharedPreferences.getString(key, EMPTY_STRING) ?: EMPTY_STRING
    }

    fun dataExists(key: String): Boolean = sharedPreferences.contains(key)

}