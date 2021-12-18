package com.gamewar.cryptotrading.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.gamewar.cryptotrading.R
import com.gamewar.cryptotrading.databinding.ActivityMainBinding
import com.traffappscorelib.wsc.StartActivity

class MainActivity : AppCompatActivity()  {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val isConnectionAvailable = intent.getBooleanExtra("networkAvailable", true)

        if (!isConnectionAvailable){
            Snackbar.make(binding?.root!!, "No internet connection found, try again!", Snackbar.LENGTH_SHORT).show()
        }

        val navView: BottomNavigationView = binding!!.navBottom

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}