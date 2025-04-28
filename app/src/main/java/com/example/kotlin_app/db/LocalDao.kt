package com.example.kotlin_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocalDao {
    @Query("SELECT * FROM Todo ORDER BY createdAt DESC")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo)

    @Query("DELETE FROM Todo where id = :id")
    fun deleteTodo(id: Int)
}