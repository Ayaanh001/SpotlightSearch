package com.paraskcd.spotlightsearch

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionRequestActivity : ComponentActivity() {

    private val permission = Manifest.permission.READ_CONTACTS

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                // Check if we can show the dialog again
                val canAskAgain =
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

                if (!canAskAgain) {
                    // User selected "Don't ask again" or OEM blocked repeated dialogs
                    openAppSettings()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            finish()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val granted = ContextCompat.checkSelfPermission(this, permission) ==
                PackageManager.PERMISSION_GRANTED

        if (!granted) {
            requestPermissionLauncher.launch(permission)
        } else {
            finish()
        }
    }

    private fun openAppSettings() {
        Toast.makeText(
            this,
            "Please enable Contacts permission",
            Toast.LENGTH_LONG
        ).show()

        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
