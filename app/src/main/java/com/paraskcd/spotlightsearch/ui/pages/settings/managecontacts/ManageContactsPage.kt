package com.paraskcd.spotlightsearch.ui.pages.settings.managecontacts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paraskcd.spotlightsearch.PermissionRequestActivity
import com.paraskcd.spotlightsearch.icons.PersonBook
import com.paraskcd.spotlightsearch.ui.components.BaseRowContainer
import com.paraskcd.spotlightsearch.ui.components.GroupSurface
import com.paraskcd.spotlightsearch.ui.components.RowWithIcon
import com.paraskcd.spotlightsearch.ui.screens.SettingsRepoViewModel

@Composable
fun ManageContactsPage(navController: NavController, vm: SettingsRepoViewModel = hiltViewModel()) {
    val state = vm.globalSearchConfigState.collectAsState().value ?: return
    val context = LocalContext.current

    LazyColumn {
        item {
            Text(
                "Manage Contacts",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        item {
            val rows = listOf("globalSwitch")
            GroupSurface(rows.size) { i, shape ->
                when (rows[i]) {
                    "globalSwitch" -> {
                        BaseRowContainer(
                            shape = shape,
                            onClick = {
                                val newValue = !state.contactsEnabled

                                if (newValue) {
                                    val permissionCheck = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.READ_CONTACTS
                                    )

                                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                                        vm.setContacts(true)
                                    } else {
                                        // Launch PermissionRequestActivity
                                        val intent = Intent(context, PermissionRequestActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        context.startActivity(intent)
                                    }
                                } else {
                                    vm.setContacts(false)
                                }
                            }
                        ) {
                            RowWithIcon(
                                icon = PersonBook,
                                text = "Enable contacts"
                            )
                            Switch(
                                checked = state.contactsEnabled,
                                onCheckedChange = { newValue ->
                                    if (newValue) {
                                        val permissionCheck = ContextCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.READ_CONTACTS
                                        )

                                        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                                            vm.setContacts(true)
                                        } else {
                                            // Launch PermissionRequestActivity
                                            val intent = Intent(context, PermissionRequestActivity::class.java)
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            context.startActivity(intent)
                                        }
                                    } else {
                                        vm.setContacts(false)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}