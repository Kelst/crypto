package com.gamewar.cryptotrading.data

import com.gamewar.cryptoapi.clients.KEY
import com.gamewar.cryptoapi.clients.NomicsClient
import com.gamewar.cryptoapi.models.responses.CurrencyResponse

class NomicsRepository {

    val api = NomicsClient.api

    suspend fun getCurrencies(pageNo: String): List<CurrencyResponse>? {
        val response = api.getCurrencies(KEY, pageNo)
        return response.body()
    }

    suspend fun fetchCurrencies(ids: String): List<CurrencyResponse>? {
        val response = api.fetchCurrencies(KEY, ids)
        return response.body()
    }

    suspend fun getCurrencyDetails(currencyId: String): List<CurrencyResponse>? {
        val response = api.getCurrencyDetails(KEY, currencyId)
        return response.body()
    }

    suspend fun getAllCurrencies(): List<CurrencyResponse>? {
        val response = api.getAllCurrencies(KEY)
        return response.body()
    }
}