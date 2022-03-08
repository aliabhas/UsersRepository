package aliabbas.com.userrepositories.di.app_dependencies

import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.TestImpl
import aliabbas.com.userrepositories.shared.domain.domain_repository_commit.TestInf
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/*
 * Created by Ali Abbas
 * This module is used to inject activity references
 *
 */
@InstallIn(ViewModelComponent::class)
@Module
abstract class HomeActivityRefMod {

    //With the help of this We can Inject the activity in Dependent classes
    //Making activity reference available for injection.
    @ViewModelScoped
    @Binds
    abstract fun homeActivityReference(homeActivity: TestImpl): TestInf
}