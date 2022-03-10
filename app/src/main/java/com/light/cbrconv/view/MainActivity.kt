package com.light.cbrconv.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.light.cbrconv.App
import com.light.cbrconv.R
import com.light.cbrconv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var vb:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        App.instance

        initFragment()
    }


    private fun initFragment() {
        vb?.fragmentContainer?.id?.let {
            supportFragmentManager.beginTransaction()
                .replace(it, MainFragment.newInstance())
                .commit()
        }
    }


}