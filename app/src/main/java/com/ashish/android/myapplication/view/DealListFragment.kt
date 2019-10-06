package com.ashish.android.myapplication.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ashish.android.myapplication.R
import com.ashish.android.myapplication.network.DealsRepository
import com.ashish.android.myapplication.network.TargetAPIService
import com.ashish.android.myapplication.viewmodel.DealsViewModel
import com.ashish.android.myapplication.viewmodel.DealsViewModelFactory

class DealListFragment : Fragment() {
    private lateinit var progress: ProgressDialog

    private lateinit var dealsRecyclerView: RecyclerView

    private lateinit var dealsRecyclerViewAdapter: DealsRecyclerViewAdapter

    private lateinit var dealsViewModel: DealsViewModel

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.deal_list, container, false)
        rootView = view;
        setupProgressBar()
        setupRecyclerView()
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO better use Dagger here to inject instances of  TargetAPIService,  DealsRepository and DealsViewModelFactory
        val dealsRepository = DealsRepository(TargetAPIService.create())
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
                dealsRecyclerView.visibility = View.GONE
                rootView.findViewById<TextView>(R.id.deals_empty_view).visibility = View.VISIBLE
            }
        })

        dealsViewModel.dealsErrorLiveData.observe(this, Observer {
            Toast.makeText(this.activity, it, Toast.LENGTH_LONG).show()
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
        dealsRecyclerView = rootView.findViewById(R.id.deals_recycler_view)
        val dividerItemDecoration = DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL)
        dealsRecyclerView.addItemDecoration(dividerItemDecoration)

        dealsRecyclerViewAdapter = DealsRecyclerViewAdapter(this.context, mutableListOf()) {

            val intent = Intent(this.activity, DealDetailActivity::class.java).apply {
                putExtra(DealDetailFragment.ARG_DEAL, it)
            }
            startActivity(intent)
        }
        dealsRecyclerView.adapter = dealsRecyclerViewAdapter
    }

    private fun setupProgressBar() {
        progress = ProgressDialog(this.activity)
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