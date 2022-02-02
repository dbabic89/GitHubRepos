package com.dariobabic.githubrepos.features.user.presentation

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.dariobabic.githubrepos.core.MainActivity
import com.dariobabic.githubrepos.core.components.CoreFragment
import com.dariobabic.githubrepos.core.components.assistedViewModel
import com.dariobabic.githubrepos.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : CoreFragment(), UserCallback {

    @Inject
    lateinit var factory: UserViewModelFactory

    private val navArgs: UserFragmentArgs by navArgs()
    private val viewModel: UserViewModel by assistedViewModel {
        factory.create(navArgs.name, navArgs.isOwner)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.callback = this
        return FragmentUserBinding.inflate(inflater, container, false)
            .also { it.data = viewModel }
            .root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (!navArgs.isOwner) menu.clear()
    }

    override fun startAuthorization() {
        (activity as? MainActivity)?.startGitHubAuthorization()
    }

    override fun logout() {
        (activity as? MainActivity)?.logout()
    }

    override fun onLinkClicked(link: String) {
        openLink(link)
    }
}