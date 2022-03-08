package aliabbas.com.scalablecodebaseapp.model.repository_commit

import java.util.*

/**
 * Created By Ali Abbas
 * This Class is used for
 *
 */
data class Committer(
    val date: Date? = null,
    val name: String? = null,
    val email: String? = null,
    val gistsUrl: String? = null,
    val reposUrl: String? = null,
    val followingUrl: String? = null,
    val starredUrl: String? = null,
    val login: String? = null,
    val followersUrl: String? = null,
    val type: String? = null,
    val url: String? = null,
    val subscriptionsUrl: String? = null,
    val receivedEventsUrl: String? = null,
    val avatarUrl: String? = null,
    val eventsUrl: String? = null,
    val htmlUrl: String? = null,
    val siteAdmin: Boolean? = null,
    val id: Int? = null,
    val gravatarId: String? = null,
    val nodeId: String? = null,
    val organizationsUrl: String? = null,
    var months: String? = null
)