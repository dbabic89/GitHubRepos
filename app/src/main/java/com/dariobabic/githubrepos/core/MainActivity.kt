package com.dariobabic.githubrepos.core

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.dariobabic.githubrepos.R
import com.dariobabic.githubrepos.core.constants.GITHUB_ACCESS_CODE
import com.dariobabic.githubrepos.core.managers.UserSessionManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userSessionManager: UserSessionManager
    private lateinit var navController: NavController

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationUi()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val authorizationCode = intent?.data?.getQueryParameter(GITHUB_ACCESS_CODE) ?: return

        compositeDisposable.add(userSessionManager.getAccessTokenAndUserInfo(authorizationCode))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun startGitHubAuthorization() = userSessionManager.startGitHubAuthorization()

    fun logout() = compositeDisposable.add(userSessionManager.logout())

    private fun setupNavigationUi() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) ?: return
        navController = navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}