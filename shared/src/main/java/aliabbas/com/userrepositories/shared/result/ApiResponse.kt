package aliabbas.com.userrepositories.shared.result

/**
 * Created By Ali Abbas
 * This Class is used for setting up the success response of failure response
 * based on result we received from the server
 * and work accordingly
 *
 */
sealed class ApiResponse {
    //Means Received Data without any error
    data class ApiResponseSuccess(val responseData: Any) : ApiResponse()

    //Means Error occurred while receiving the data
    data class ApiFailure(val response: String = "No Data Found") : ApiResponse()

    //Means receiving the data is in progress
    object ProgressLoadingState : ApiResponse()

    //Means receiving the data is in progress
    object NetworkNotAvailable : ApiResponse()
}