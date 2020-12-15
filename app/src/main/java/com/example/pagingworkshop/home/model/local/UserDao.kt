package com.example.pagingworkshop.home.model.local

import androidx.room.*
import com.example.pagingworkshop.home.model.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users" )
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE first_name like :firstName AND last_name like :lastName")
    fun findByName(firstName: String, lastName: String): User

    @Query("SELECT * FROM users WHERE uid like :userId LIMIT 1")
    fun findById(userId: String)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Delete
    fun deleteUser(userID: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
}