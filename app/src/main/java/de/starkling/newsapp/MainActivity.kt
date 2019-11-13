package de.starkling.newsapp

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import de.starkling.newsapp.base.BaseActivity
import de.starkling.newsapp.extensions.toast
import de.starkling.newsapp.fragments.HomeFragmentDirections
import de.starkling.newsapp.models.Categories
import de.starkling.newsapp_android.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {


    private lateinit var navController: NavController

    @Inject
    lateinit var syncWork:PeriodicWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navigation_view.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {

                R.id.general -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentSelf(Categories.GENERAL.getValue())
                    navController.navigate(action)

                }
                R.id.business -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentSelf(Categories.BUSINESS.getValue())
                    navController.navigate(action)
                }
                R.id.science -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentSelf(Categories.SCIENCE.getValue())
                    navController.navigate(action)
                }
                R.id.technology -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentSelf(Categories.TECHNOLOGY.getValue())
                    navController.navigate(action)
                }
                R.id.health -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentSelf(Categories.HEALTH.getValue())
                    navController.navigate(action)
                }
                R.id.entertainment -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentSelf(Categories.ENTERTAINMENT.getValue())
                    navController.navigate(action)
                }
                R.id.sports -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentSelf(Categories.SPORTS.getValue())
                    navController.navigate(action)
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START, true)
            true
        }

        navigation_view.getHeaderView(0).findViewById<ImageView>(R.id.imageViewClose)
            .setOnClickListener {
                drawer_layout.closeDrawer(GravityCompat.START, true)
            }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.searchFragment -> {
                    supportActionBar?.hide()
                }
                else -> {
                    supportActionBar?.show()
                }
            }
        }

        setupWorkManagerListener()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun inject() {
        component.inject(this)
    }

    private fun setupWorkManagerListener(){

        val workManager = WorkManager.getInstance(applicationContext)

        workManager.getWorkInfoByIdLiveData(syncWork.id).observe(this, Observer { workInfo->
            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                toast("New Update available")
            }
        })
    }
}

