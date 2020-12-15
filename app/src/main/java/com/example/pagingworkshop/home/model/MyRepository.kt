package com.example.pagingworkshop.home.model

import com.example.pagingworkshop.home.model.entities.User
import com.example.pagingworkshop.home.model.local.UserDao

class MyRepository(val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(userId: Long) {
        userDao.deleteUser(userId)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAll()
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }

    suspend fun getUserByName(user: User) {
        userDao.findByName(user.firstName!!, user.lastName!!)
    }
}