package com.gamewar.cryptotrading.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlist_table")
data class Watchlist (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val symbol: String,
    val watchlist: String
)