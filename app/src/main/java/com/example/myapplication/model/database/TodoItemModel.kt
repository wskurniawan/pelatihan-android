package com.example.myapplication.model.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class TodoItemModel{
    @PrimaryKey var id: Int? = null
    var namaKegiatan: String? = null
    var timestamp: Long? = null
    var username: String? = null
}