package com.vivitasol.carcasamvvm.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore by preferencesDataStore(name = "demo_prefs")
private val KEY_SUSCRIPCION = booleanPreferencesKey("suscripcion")

object PrefsRepo {
    fun suscripcionFlow(context: Context): Flow<Boolean> =
        context.dataStore.data
            .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
            .map { it[KEY_SUSCRIPCION] ?: false }

    suspend fun setSuscripcion(context: Context, value: Boolean) {
        context.dataStore.edit { it[KEY_SUSCRIPCION] = value }
    }
}
