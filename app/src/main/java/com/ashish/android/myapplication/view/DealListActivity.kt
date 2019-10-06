package com.ashish.android.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashish.android.myapplication.R
import kotlinx.android.synthetic.main.tool_bar.*

class DealListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deal_list)

        setSupportActionBar(my_toolbar)
        my_toolbar.title = title

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, DealListFragment())
                .commit()
        }
    }
}
