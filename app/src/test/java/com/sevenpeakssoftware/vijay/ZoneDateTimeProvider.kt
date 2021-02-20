package com.sevenpeakssoftware.vijay

import org.threeten.bp.zone.TzdbZoneRulesProvider
import org.threeten.bp.zone.ZoneRulesProvider

object ZoneDateTimeProvider {

    fun Any.loadTimeZone() {
        if(ZoneRulesProvider.getAvailableZoneIds().isEmpty()) {
            val stream = this.javaClass.classLoader!!.getResourceAsStream("TZDB.dat")
            stream.use(::TzdbZoneRulesProvider).apply {
                ZoneRulesProvider.registerProvider(this)
            }
        }
    }
}