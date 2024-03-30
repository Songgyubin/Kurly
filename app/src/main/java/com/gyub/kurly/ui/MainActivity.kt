package com.gyub.kurly.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyub.kurly.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity
 *
 * @author   Gyub
 * @created  2024/03/30
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}