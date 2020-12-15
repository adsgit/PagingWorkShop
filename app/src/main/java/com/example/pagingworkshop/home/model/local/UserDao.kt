package com.example.pagingworkshop.home.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pagingworkshop.home.model.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users" )
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE first_name like :firstName AND last_name like :lastName")
    fun findByName(firstName: String, lastName: String): User

    @Query("SELECT * FROM users WHERE uid like :userId")
    fun findById(userId: String)

    @Delete
    fun deleteAll()

    @Query("DELETE FROM users WHERE uid like :userID")
    fun deleteUser(userID: Long)

    @Insert
    fun insertUser(user: User)
}