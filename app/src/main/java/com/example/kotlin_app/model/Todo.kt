package com.example.kotlin_app.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title : String,
    var createdAt: Date
)