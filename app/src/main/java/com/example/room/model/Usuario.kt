package com.example.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario (

    @ColumnInfo(name = "Nome")
    var nome  : String,

    @ColumnInfo(name = "Email")
    var email : String

 ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}