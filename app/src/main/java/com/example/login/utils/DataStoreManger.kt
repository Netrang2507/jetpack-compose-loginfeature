package com.example.login.utils

import android.content.Context

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import androidx.datastore.preferences.preferencesDataStore
import com.example.login.model.UserDetails
import kotlinx.coroutines.flow.map


const val UserDataStore = "userData"
val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = UserDataStore)
class DataStoreManger(val context: Context) {

    companion object {
        val email = stringPreferencesKey("Email")
        val password = stringPreferencesKey("Password")
        val mobileNumber = stringPreferencesKey("mobileNumber")
        val nameUser = stringPreferencesKey("Name")
    }

    suspend fun saveToDataStore(userDetails: UserDetails) {
        context.preferenceDataStore.edit {
            it[email] = userDetails.emailAddress
            it[password] = userDetails.password
            it[mobileNumber] = userDetails.mobileNumber
            it[nameUser] = userDetails.name
        }
    }



    fun getFromDataStore() = context.preferenceDataStore.data.map {
        UserDetails(
            emailAddress = it[email] ?: "",
            password = it[password] ?: "",
            mobileNumber = it[mobileNumber] ?: "",
            name = it[nameUser] ?: ""
        )
    }
    suspend fun clearDataStore() = context.preferenceDataStore.edit {
        it.clear()
    }
}

