package com.ashish.android.myapplication.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ashish.android.myapplication.R
import com.ashish.android.myapplication.network.DealsRepository
import com.ashish.android.myapplication.network.TargetAPIService
import com.ashish.android.myapplication.viewmodel.DealsViewModel
import com.ashish.android.myapplication.viewmodel.DealsViewModelFactory
import kotlinx.android.synthetic.main.activity_deal_list.*

class DealListActivity : AppCompatActivity() {

    private lateinit var progress: ProgressDialog

    private lateinit var dealsRecyclerView: RecyclerView

    private lateinit var dealsRecyclerViewAdapter: DealsRecyclerViewAdapter

    private lateinit var dealsViewModel: DealsViewModel

    private lateinit var dealsRepository: DealsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deal_list)

        setSupportActionBar(toolbar)
        toolbar.title = title
        setupProgressBar()
        setupRecyclerView()

        // TODO better use Dagger here to inject instances of  TargetAPIService,  DealsRepository and DealsViewModelFactory
        val dealsRepository = DealsRepository(TargetAPIService.Factory.create())
        val dealsViewModelFactory = DealsViewModelFactory(dealsRepository)

        dealsViewModel =
            ViewModelProviders.of(this, dealsViewModelFactory).get(DealsViewModel::class.java)

        observerLiveData()

        dealsViewModel.fetchDeals()
    }

    private fun observerLiveData() {
        dealsViewModel.dealsLiveData.observe(this, Observer { dealList ->
            dealsRecyclerViewAdapter.setItems(dealList.deals.toMutableList())
        })

        dealsViewModel.noDealAvailableLiveData.observe(this, Observer {
            if (it) {
                dealsRecyclerView.visibility = GONE
                findViewById<TextView>(R.id.deals_empty_view).visibility = VISIBLE
            }
        })

        dealsViewModel.dealsErrorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        dealsViewModel.showProgressBarLiveData.observe(this, Observer {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })
    }

    private fun setupRecyclerView() {
        dealsRecyclerView = findViewById(R.id.deals_recycler_view)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dealsRecyclerView.addItemDecoration(dividerItemDecoration)

        dealsRecyclerViewAdapter = DealsRecyclerViewAdapter(this, mutableListOf()) {
            val intent = Intent(this, DealDetailActivity::class.java).apply {
                putExtra(DealDetailFragment.ARG_DEAL, it)
            }
            startActivity(intent)

        }
        dealsRecyclerView.adapter = dealsRecyclerViewAdapter
    }


    private fun setupProgressBar() {
        progress = ProgressDialog(this)
        progress.setMessage(getString(R.string.please_wait_string))
        progress.setCancelable(false)
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
    }

    private fun showProgressBar() {
        progress.show()
    }

    private fun hideProgressBar() {
        progress.dismiss()
    }
}
