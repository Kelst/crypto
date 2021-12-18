package com.gamewar.cryptotrading.data

import androidx.lifecycle.LiveData
import com.gamewar.cryptotrading.database.WatchlistDAO
import com.gamewar.cryptotrading.model.Watchlist

class WatchlistRepository(private val watchlistDAO: WatchlistDAO) {

    suspend fun addCurrency(watchlist: Watchlist) = watchlistDAO.insertCurrency(watchlist)

    suspend fun removeCurrency(watchlist: Watchlist) = watchlistDAO.deleteCurrency(watchlist)

    val getAllCurrencies: LiveData<List<Watchlist>> = watchlistDAO.getAllCurrencies()
}