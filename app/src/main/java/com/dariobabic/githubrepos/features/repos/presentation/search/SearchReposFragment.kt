package com.dariobabic.githubrepos.features.repos.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.dariobabic.githubrepos.R
import com.dariobabic.githubrepos.core.components.CoreFragment
import com.dariobabic.githubrepos.core.constants.IS_OWNER
import com.dariobabic.githubrepos.core.constants.OWNER_NAME
import com.dariobabic.githubrepos.core.constants.REPO_NAME
import com.dariobabic.githubrepos.databinding.FragmentSearchReposBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchReposFragment : CoreFragment(), SearchReposCallback {

    private val viewModel: SearchReposViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.callback = this
        return FragmentSearchReposBinding
            .inflate(inflater, container, false)
            .also { it.data = viewModel }
            .root
    }

    override fun openRepo(name: String) {
        val bundle = bundleOf(REPO_NAME to name)
        navigate(R.id.action_searchRepositoriesFragment_to_repoDetailsFragment, bundle)
    }

    override fun openOwner(ownerName: String) {
        val bundle = bundleOf(OWNER_NAME to ownerName, IS_OWNER to true)
        navigate(R.id.userFragment, bundle)
    }
}