package com.example.kotlin_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kotlin_app.db.Todo

@Dao
interface LocalDao {
    @Query("SELECT * FROM Todo ORDER BY createdAt DESC")
    fun getAllTodo(): LiveData<List<Todo>>

    @Insert
    suspend fun addTodo(todo: Todo)

    @Query("DELETE FROM Todo where id = :id")
    suspend fun deleteTodo(id: Int)

    @Update
    suspend fun updateTodo(todo: Todo)
}
