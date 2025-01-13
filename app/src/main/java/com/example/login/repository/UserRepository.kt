package com.example.login.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.login.model.User
import com.example.login.utils.DataStoreManger
import com.example.login.utils.DataStoreManger.Companion
import com.example.login.utils.preferenceDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val dataStore: DataStore<Preferences>) {

  private companion object {
        val email = stringPreferencesKey("Email")
        val password = stringPreferencesKey("Password")
        val mobileNumber = stringPreferencesKey("mobileNumber")
        val nameUser = stringPreferencesKey("Name")
    }
    suspend fun saveToDataStore(userDetails: User) {
        dataStore.edit {
            it[DataStoreManger.email] = userDetails.emailAddress
            it[DataStoreManger.password] = userDetails.password
            it[DataStoreManger.mobileNumber] = userDetails.mobileNumber
            it[DataStoreManger.nameUser] = userDetails.name
        }
    }



    fun getFromDataStore() {
       dataStore.data.map { preferences ->
            User(
                emailAddress = preferences[DataStoreManger.email] ?: "",
                password = preferences[DataStoreManger.password] ?: "",
                mobileNumber = preferences[DataStoreManger.mobileNumber] ?: "",
                name = preferences[DataStoreManger.nameUser] ?: ""
            )
        }
    }
}