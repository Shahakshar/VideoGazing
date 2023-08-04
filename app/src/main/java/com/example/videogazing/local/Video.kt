package com.example.videogazing.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Videos_Detail")
data class Videos(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String?,
    val videoUrl: String
)
