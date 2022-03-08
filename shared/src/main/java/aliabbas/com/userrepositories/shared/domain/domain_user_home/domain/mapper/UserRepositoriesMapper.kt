package aliabbas.com.userrepositories.shared.domain.domain_user_home.domain.mapper

import aliabbas.com.scalablecodebaseapp.database.db.tables.UserRepositories
import aliabbas.com.scalablecodebaseapp.model.user_repository.UserRepositoriesModel
import javax.inject.Inject


// This mapper will transform the domain mapper into local db or remote DTO classes
// Like when user want to save some data from UI layer into local or remote
// data is transformed into some kind of model

class UserRepositoriesMapper @Inject constructor() {
    fun toUserRepositories(userRepositories: UserRepositories): List<UserRepositoriesModel> {
        return userRepositories.listOfRepositories!!
    }
}