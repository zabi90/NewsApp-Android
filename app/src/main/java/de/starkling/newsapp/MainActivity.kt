package de.starkling.newsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.TransitionManager
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
        navigation_view.setupWithNavController(navController)

        navigation_view.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.general -> {

                }
                R.id.business -> {

                }
                R.id.science -> {

                }
                R.id.technology -> {

                }
                R.id.health -> {

                }
                R.id.entertainment -> {

                }
                R.id.sports -> {

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
