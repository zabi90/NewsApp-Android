package de.starkling.newsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.children
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import de.starkling.newsapp.fragments.HomeFragment
import de.starkling.newsapp.fragments.HomeFragmentArgs
import de.starkling.newsapp.fragments.HomeFragmentDirections
import de.starkling.newsapp.models.Categories
import de.starkling.newsapp.viewmodels.HomeViewModel
import de.starkling.newsapp_android.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

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
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                navController.navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }
        return true
    }
}
