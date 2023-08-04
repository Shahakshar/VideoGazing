package com.example.videogazing.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Videos::class], version = 1, exportSchema = false)
abstract class VideosDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

    companion object {

        private var instance: VideosDatabase? = null

        fun getInstance(context: Context) : VideosDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(context.applicationContext, VideosDatabase::class.java,
                    "note_database")
                    .build()


            return instance!!
        }

    }

}