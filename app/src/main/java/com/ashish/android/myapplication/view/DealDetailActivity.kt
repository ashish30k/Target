package com.ashish.android.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ashish.android.myapplication.R
import com.ashish.android.myapplication.model.Deal

class DealDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deal_detail)
        //setSupportActionBar(toolbar)


        if (savedInstanceState == null) {
            val fragment = DealDetailFragment().apply {
                arguments = Bundle().apply {
                    val deal = intent.getParcelableExtra<Deal>(DealDetailFragment.ARG_DEAL)
                    putParcelable(
                        DealDetailFragment.ARG_DEAL, deal
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.deal_details_fragment_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, DealListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
