package com.gamewar.cryptoapi.services

import com.gamewar.cryptoapi.clients.KEY
import com.gamewar.cryptoapi.clients.NomicsClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

class NomicsAPITest {

    private val api = NomicsClient.api

    @Test
    fun `Get all cryptocurrencies`() = runBlocking {
        val response = api.getCurrencies(KEY, "1")
        assertNotNull(response.body())
    }

    @Test
    fun `Get currency details`() = runBlocking {
        val response = api.getCurrencyDetails(KEY, "BTC")
        assertNotNull(response.body())
    }

    @Test
    fun `Get all currencies`() = runBlocking {
        val response = api.getAllCurrencies(KEY)
        assertNotNull(response.body())
    }
}