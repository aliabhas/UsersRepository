package aliabbas.com.userrepositories.shared.domain.domain_repository_commit

import android.util.Log
import javax.inject.Inject

/**
 * Created By Ali Abbas on on 07,January,2022
 * This Class is used for
 *
 */
class TestImpl @Inject constructor() : TestInf {
    override suspend fun helloTestFun() {
        Log.i("TestImpl", "helloTestFun: ")
    }
}