package com.aci.data.content

import android.content.Context
import com.aci.core.domain.model.Event
import com.aci.core.domain.repository.EventRepository

/** Seeds real ACI events (see `events.json` provenance note) into Room on first launch. */
object EventSeeder {
    suspend fun seedIfNeeded(repository: EventRepository, context: Context) {
        if (!repository.isEmpty()) return
        val events: List<Event> = JsonAssetLoader.load(context, "data/events.json")
        repository.upsertEvents(events)
    }
}
