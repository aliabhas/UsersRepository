package aliabbas.com.userrepositories

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * We create a custom [AndroidApplication] class that extends  [DaggerApplication].
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as [DaggerApplication] will do that for us.
 */
@HiltAndroidApp
class AndroidApplication : Application() {
}