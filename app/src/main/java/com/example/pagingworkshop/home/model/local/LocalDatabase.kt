package com.example.pagingworkshop.home.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pagingworkshop.home.model.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        var INSTANCE: LocalDatabase? = null
        fun getInstance(context: Context) : LocalDatabase{
            return INSTANCE ?: synchronized(lock = this){
             val instance = Room.databaseBuilder(
                 context.applicationContext,
                 LocalDatabase::class.java,
                 "user_database"
             ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}