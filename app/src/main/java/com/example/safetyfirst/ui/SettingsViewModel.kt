package com.example.safetyfirst.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    var vpnAutoStart by mutableStateOf(
        AppPrefs.getAutoStart(application)
    )

    var threadAlerts by mutableStateOf(
        AppPrefs.getThreatAlerts(application)
    )

    var connectionsUpdates by mutableStateOf(false)

    var weeklyReports by mutableStateOf(
        AppPrefs.getWeeklyReports(application)
    )

    fun toggleVpnAutoStart() {

        vpnAutoStart = !vpnAutoStart

        AppPrefs.setAutoStart(
            getApplication(),
            vpnAutoStart
        )
    }

    fun toggleThreadAlerts() {

        threadAlerts = !threadAlerts

        AppPrefs.setThreatAlerts(
            getApplication(),
            threadAlerts
        )
    }

    fun toggleConnectionsUpdates() {

        connectionsUpdates = !connectionsUpdates
    }

    fun toggleWeeklyReports() {

        weeklyReports = !weeklyReports

        AppPrefs.setWeeklyReports(
            getApplication(),
            weeklyReports
        )
    }
}