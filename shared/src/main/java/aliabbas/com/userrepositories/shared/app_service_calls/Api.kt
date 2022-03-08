package aliabbas.com.scalablecodebaseapp.shared.app_service_calls

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositoriesTable
import aliabbas.com.scalablecodebaseapp.model.repository_commit.RepositoryCommitsDetailModel
import aliabbas.com.scalablecodebaseapp.model.user_repository.UserRepositoriesModel
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created By Ali Abbas For Network Calls
 * Unauthenticated clients can make 60 requests per hour. To get more requests per hour, we'll need to authenticate.
 * To make more than 60 requests, kindly uncomment @Header code line,
 * and add your github token to get access to more than 60 requests per hour
 */
interface Api {

    //@Headers("Authorization: token 'paste_ur_token_here'")
    @GET
    suspend fun getUserGithubRepositories(@Url url: String): List<UserRepositoriesTable>

    //@Headers("Authorization: token 'paste_ur_token_here'")
    @GET
    suspend fun getRepositoryCommitDetailsCall(@Url url: String): List<RepositoryCommitsDetailModel>


}