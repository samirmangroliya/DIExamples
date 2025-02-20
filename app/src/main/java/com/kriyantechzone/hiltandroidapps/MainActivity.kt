package com.kriyantechzone.hiltandroidapps

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.kriyantechzone.hiltandroidapps.databinding.ActivityMainBinding
import com.kriyantechzone.hiltandroidapps.di.ExampleInterface
import com.kriyantechzone.hiltandroidapps.di.FirstImplementation
import com.kriyantechzone.hiltandroidapps.di.SecondImplementation
import com.kriyantechzone.hiltandroidapps.view.UserListViewModel
import com.kriyantechzone.hiltandroidapps.view.UsersListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModelUserList by viewModels<UserListViewModel>()

  /*
    @Inject
    @Named("First")
    lateinit var exampleInterfaceImp1:ExampleInterface

    @Inject
    @Named("Second")
    lateinit var exampleInterfaceImp2:ExampleInterface*/

    @Inject
    @FirstImplementation
    lateinit var exampleInterfaceImp1:ExampleInterface

    @Inject
    @SecondImplementation
    lateinit var exampleInterfaceImp2:ExampleInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        exampleInterfaceImp1.sayHello()
        exampleInterfaceImp2.sayHello()

        viewModelUserList.users.observe(this@MainActivity) {
            binding.maincontent.rvuserlist.adapter = UsersListAdapter(it) {
                Log.d("clicked user name", it.login)
            }

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

    override fun onSupportNavigateUp(): Boolean {
      /*  val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()*/
        return true
    }
}