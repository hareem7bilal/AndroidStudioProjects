package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.info.observe(this){
            displaySnackbar(it)
        }

        if (savedInstanceState != null) return

        supportFragmentManager.commit {
            add<FragmentHome>(R.id.container, null)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> goToHome()

                R.id.action_tours -> goToTours()

                R.id.action_shop -> goToShop()

                else -> false
            }
        }

        val viewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        viewModel.quantity.observe(this) {
            updateBadge(it)
        }
    }

    private fun displaySnackbar(count: Int) {
        Snackbar.make(binding.root, "Current value $count", Snackbar.LENGTH_LONG).show()
    }

    private fun updateBadge(count: Int) {
        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.action_shop)
        if (count > 0) {
            badge.number = count
            badge.isVisible = true
        } else {
            badge.clearNumber()
            badge.isVisible = false
        }
    }

    private fun goToShop(): Boolean {
        supportFragmentManager.commit {
            replace<FragmentShop>(R.id.container, null, null)
        }

        return true
    }

    private fun goToTours(): Boolean {
        supportFragmentManager.commit {
            replace<FragmentTours>(R.id.container, null, null)
        }

        return true
    }

    private fun goToHome(): Boolean {
        supportFragmentManager.commit {
            replace<FragmentHome>(R.id.container, null, null)
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings -> handleShare()
            else  -> return super.onOptionsItemSelected(item)
        }

    }

    private fun handleShare(): Boolean {
        val intent = Intent().apply{
            action= Intent.ACTION_SEND
            type="text/plain"
            putExtra(Intent.EXTRA_TEXT, "I just purchased ${viewModel.info.value} bottles of olive oil!")

        }
        startActivity(intent)
        return true

    }

}