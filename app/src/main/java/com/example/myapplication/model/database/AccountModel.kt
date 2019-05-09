package com.example.myapplication.model.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class AccountModel{
    @PrimaryKey var id: Int? = null
    var username: String? = null
    var password: String? = null
}