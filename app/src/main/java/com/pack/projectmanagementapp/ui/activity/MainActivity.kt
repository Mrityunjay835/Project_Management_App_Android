package com.pack.projectmanagementapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.pack.projectmanagementapp.R
import com.pack.projectmanagementapp.databinding.ActivityMainBinding
import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.ui.fragment.home.HomeFragment
import com.pack.projectmanagementapp.ui.fragment.product.ProductFragment

class MainActivity : AppCompatActivity(), HomeFragment.DataPassListener  {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val initialFragment = HomeFragment()
        loadFragment(initialFragment)
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentListProduct, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onDataPassed(data: Product) {
        val productFragment = ProductFragment.newInstance(data)
        productFragment?.onDataPassed(data)
        loadFragment(productFragment)
    }

}
