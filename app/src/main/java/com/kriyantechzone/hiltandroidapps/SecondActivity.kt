package com.kriyantechzone.hiltandroidapps

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.kriyantechzone.hiltandroidapps.databinding.ActivityMainBinding
import com.kriyantechzone.hiltandroidapps.databinding.ActivitySecondBinding
import com.kriyantechzone.hiltandroidapps.di.Dev
import com.kriyantechzone.hiltandroidapps.di.Employee
import com.kriyantechzone.hiltandroidapps.di.Management
import com.kriyantechzone.hiltandroidapps.view.UserListViewModel
import com.kriyantechzone.hiltandroidapps.view.UsersListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    @Inject
    @Dev
    lateinit var developer: Employee

    @Inject
    @Management
    lateinit var manager:Employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        developer.employeeType()
        manager.employeeType()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
      /*  val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()*/
        return true
    }
}