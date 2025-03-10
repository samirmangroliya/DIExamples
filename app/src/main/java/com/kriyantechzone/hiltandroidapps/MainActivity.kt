package com.kriyantechzone.hiltandroidapps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kriyantechzone.hiltandroidapps.databinding.ActivityMainBinding
import com.kriyantechzone.hiltandroidapps.di.Dev
import com.kriyantechzone.hiltandroidapps.di.Employee
import com.kriyantechzone.hiltandroidapps.di.Management
import com.kriyantechzone.hiltandroidapps.view.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityRetainedScoped
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    private val viewModelUserList by viewModels<UserListViewModel>()

    /*
      @Inject
      @Named("First")
      lateinit var developer:Employee

      @Inject
      @Named("Second")
      lateinit var manager:Employee*/

    @ActivityRetainedScoped
    class AnalyticsAdapter @Inject constructor() {  }

    @Inject
    @Dev
    lateinit var developer: Employee

    @Inject
    @Dev
    lateinit var developer2: Employee

    @Inject
    @Management
    lateinit var manager: Employee

    @Inject
    lateinit var analyticsAdapter: AnalyticsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        Timber.tag("Test").d("Analytics is Called..." + analyticsAdapter.hashCode())

        developer.employeeType()
        manager.employeeType()

        binding.maincontent.btnnext.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }


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

    override fun onSupportNavigateUp(): Boolean {/*  val navController = findNavController(R.id.nav_host_fragment_content_main)
          return navController.navigateUp(appBarConfiguration)
                  || super.onSupportNavigateUp()*/
        return true
    }
}