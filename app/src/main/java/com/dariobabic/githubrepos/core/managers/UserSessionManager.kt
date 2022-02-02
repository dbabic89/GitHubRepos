package com.dariobabic.githubrepos.core.managers

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.dariobabic.githubrepos.core.auth.domain.use_cases.GetAccessTokenUseCase
import com.dariobabic.githubrepos.core.constants.CLIENT_ID
import com.dariobabic.githubrepos.core.constants.CLIENT_SECRET
import com.dariobabic.githubrepos.core.database.DataBase
import com.dariobabic.githubrepos.features.user.domain.use_cases.GetUserDetailsUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class UserSessionManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val dataBase: DataBase
) {
    companion object {
        const val SCOPE = "read:user,public_repo"
        const val GITHUB_AUTH_URL = "https://github.com/login/oauth/authorize"
    }

    fun startGitHubAuthorization() {
        val url = "$GITHUB_AUTH_URL?client_id=$CLIENT_ID&scope=$SCOPE&state=${UUID.randomUUID()}"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        context.startActivity(intent)
    }

    fun getAccessTokenAndUserInfo(accessCode: String): Disposable {
        return getAccessTokenUseCase.setup(CLIENT_ID, CLIENT_SECRET, accessCode)
            .buildUseCaseSingle()
            .flatMapCompletable {
                val accessToken = "token ${it.accessToken}"
                getUserDetailsUseCase.setup(accessToken).buildUseCaseCompletable()
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun logout(): Disposable = Completable.defer {
        val dao = dataBase.userDao()
        dao.removeUser().andThen(dao.removeUserRepos())
    }
        .subscribeOn(Schedulers.io())
        .subscribe()
}