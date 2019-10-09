package com.ashish.android.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ashish.android.myapplication.R
import com.ashish.android.myapplication.common.dagger.AppModule
import com.ashish.android.myapplication.common.dagger.DaggerAppComponent
import com.ashish.android.myapplication.common.dagger.NetworkModule
import com.ashish.android.myapplication.common.dagger.RoomModule
import com.ashish.android.myapplication.data.datasource.DealsDataSource
import com.ashish.android.myapplication.viewmodel.DealsViewModel
import com.ashish.android.myapplication.viewmodel.DealsViewModelFcatory
import kotlinx.android.synthetic.main.tool_bar.*
import javax.inject.Inject

class DealListActivity : AppCompatActivity() {
    private lateinit var dealsViewModel: DealsViewModel
    @Inject
    lateinit var dealsDataSource: DealsDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deal_list)
        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .roomModule(RoomModule(application))
            .networkModule(NetworkModule)
            .build()
            .inject(this)

        dealsViewModel =
            ViewModelProviders.of(this, DealsViewModelFcatory(dealsDataSource)).get(DealsViewModel::class.java)

        setSupportActionBar(my_toolbar)
        my_toolbar.title = title

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, DealListFragment())
                .commit()
        }
    }

    fun getDealsViewModel() = dealsViewModel
}
