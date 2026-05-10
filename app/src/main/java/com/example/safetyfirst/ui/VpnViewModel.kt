package com.example.safetyfirst.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VpnViewModel : ViewModel() {

    private val _vpnOn = MutableStateFlow(false)
    val vpnOn: StateFlow<Boolean> = _vpnOn.asStateFlow()

    fun setVpnOn(on: Boolean) {
        _vpnOn.value = on
    }

    private val _notifications = MutableStateFlow<List<String>>(emptyList())
    val notifications: StateFlow<List<String>> = _notifications

    fun addNotification(msg: String) {
        _notifications.value = _notifications.value + msg
    }

    private val _activeThreat = MutableStateFlow<String?>(null)
    val activeThreat: StateFlow<String?> = _activeThreat.asStateFlow()

    private val acknowledgedThreats =
        mutableSetOf<String>()

    fun clearThreatPopup() {
        _activeThreat.value = null
    }

    private val _events = MutableStateFlow<List<GatewayEvent>>(emptyList())
    val events: StateFlow<List<GatewayEvent>> = _events.asStateFlow()

    private val _highRiskCount = MutableStateFlow(0)
    val highRiskCount: StateFlow<Int> = _highRiskCount.asStateFlow()

    private val _mediumRiskCount = MutableStateFlow(0)
    val mediumRiskCount: StateFlow<Int> = _mediumRiskCount.asStateFlow()

    private val _safeConnectionCount = MutableStateFlow(0)
    val safeConnectionCount: StateFlow<Int> = _safeConnectionCount.asStateFlow()

    fun clearAnalytics() {

        _highRiskCount.value = 0
        _mediumRiskCount.value = 0
        _safeConnectionCount.value = 0
    }

    fun refreshEvents() {

        ApiClient.fetchEvents { result ->

            if (result != null) {

                _events.value = result

                result.forEach { event ->

                    when {

                        event.verdict.contains(
                            "BLACKLIST",
                            ignoreCase = true
                        ) ||

                                event.verdict.contains(
                                    "MALWARE",
                                    ignoreCase = true
                                ) -> {

                            _highRiskCount.value++
                        }

                        event.verdict.contains(
                            "SUSPICIOUS",
                            ignoreCase = true
                        ) -> {

                            _mediumRiskCount.value++
                        }

                        else -> {

                            _safeConnectionCount.value++
                        }
                    }

                    if (

                        event.verdict.contains(
                            "BLACKLIST",
                            ignoreCase = true
                        ) ||

                        event.verdict.contains(
                            "MALWARE",
                            ignoreCase = true
                        )

                    ) {

                        val warning =
                            "Warning!!! Threat detected: ${event.domain}"

                        if (
                            !_notifications.value.contains(warning)
                        ) {

                            addNotification(warning)

                            if (
                                !acknowledgedThreats.contains(
                                    event.domain
                                )
                            ) {

                                _activeThreat.value =
                                    event.domain

                                acknowledgedThreats.add(
                                    event.domain
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}