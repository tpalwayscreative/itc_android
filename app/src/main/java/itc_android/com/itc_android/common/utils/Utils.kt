package itc_android.com.itc_android.common.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by mac10 on 6/8/17.
 */

object Utils {

    fun isPalindrome(s: String): Boolean {
        val n = s.length
        for (i in 0..n / 2 - 1) {
            if (s[i] != s[n - i - 1]) {
                return false
            }
        }
        return true
    }

    fun sanitize(`in`: String): String {
        var result: String? = null
        val p = Pattern.compile("[^a-zA-Z0-9]+")
        val m = p.matcher(`in`)
        result = m.replaceAll("")
        return result!!.toLowerCase()
    }

    fun isCheckEmpty(value: String): Boolean {
        val ed_text = value.trim { it <= ' ' }
        if (ed_text.isEmpty() || ed_text.length == 0 || ed_text == "" || ed_text == null) {
            return true
        }
        return false
    }

    fun setAutoOrientationEnabled(context: Context, enabled: Boolean) {
        Settings.System.putInt(context.contentResolver, Settings.System.ACCELEROMETER_ROTATION, if (enabled) 1 else 0)
    }

    fun desirePermissionCode(context: Activity) {
        val permission: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = Settings.System.canWrite(context)
        } else {
            permission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED
        }
        if (permission) {
            setAutoOrientationEnabled(context, true)
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:" + context.packageName)
                context.startActivityForResult(intent, 111)
            } else {
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.WRITE_SETTINGS), 111)
            }
        }
    }

}
