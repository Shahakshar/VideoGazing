package com.example.videogazing.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Insert
    suspend fun insert(videos: Videos)

    @Query("Select * from videos_detail")
    fun getAllVideo(): List<Videos>

}