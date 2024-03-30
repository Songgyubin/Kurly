package com.gyub.kurly.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gyub.kurly.R
import com.gyub.kurly.SectionViewModel
import com.gyub.kurly.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity
 *
 * @author   Gyub
 * @created  2024/03/30
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val sectionViewModel: SectionViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}