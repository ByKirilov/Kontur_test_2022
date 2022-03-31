package com.kirilov.kontur_test.presentation.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kirilov.kontur_test.R
import com.kirilov.kontur_test.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navFragment.navController

        setContentView(binding.root)
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        navController.
//    }
}