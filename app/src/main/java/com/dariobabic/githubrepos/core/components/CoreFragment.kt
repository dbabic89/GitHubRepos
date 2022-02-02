package com.dariobabic.githubrepos.core.components

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dariobabic.githubrepos.R

open class CoreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
            else -> navigate(R.id.userFragment)
        }

        return super.onOptionsItemSelected(item)
    }

    fun navigate(destinationId: Int, bundle: Bundle? = null) {
        findNavController().navigate(destinationId, bundle)
    }

    fun openLink(link: String) {
        val implicit = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(implicit)
    }
}