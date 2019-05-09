package com.example.myapplication.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.myapplication.model.database.AccountModel

@Dao
interface AccountDao {
    @Insert
    fun insertAccount(dataAccount: AccountModel)

    @Query("SELECT * FROM AccountModel WHERE username = :username")
    fun getAccount(username: String): AccountModel
}