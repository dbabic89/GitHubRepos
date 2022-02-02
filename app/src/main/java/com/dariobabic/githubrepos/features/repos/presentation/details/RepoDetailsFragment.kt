package com.dariobabic.githubrepos.features.repos.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.navArgs
import com.dariobabic.githubrepos.R
import com.dariobabic.githubrepos.core.components.CoreFragment
import com.dariobabic.githubrepos.core.components.assistedViewModel
import com.dariobabic.githubrepos.core.constants.IS_OWNER
import com.dariobabic.githubrepos.core.constants.OWNER_NAME
import com.dariobabic.githubrepos.databinding.FragmentRepoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepoDetailsFragment : CoreFragment(), RepoDetailsCallback {

    @Inject
    lateinit var factory: RepoDetailsViewModelFactory

    private val navArgs: RepoDetailsFragmentArgs by navArgs()
    private val viewModel: RepoDetailsViewModel by assistedViewModel {
        factory.create(navArgs.repoName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.callback = this
        return FragmentRepoDetailsBinding
            .inflate(inflater, container, false)
            .also { it.data = viewModel }
            .root
    }

    override fun openRepoLink(link: String) {
        openLink(link)
    }

    override fun openOwner(ownerName: String) {
        val bundle = bundleOf(OWNER_NAME to ownerName, IS_OWNER to true)
        navigate(R.id.action_userGlobal, bundle)
    }
}