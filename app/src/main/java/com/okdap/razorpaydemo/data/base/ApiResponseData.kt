package com.okdap.razorpaydemo.data.base


/**
 * Sealed class to observe changes on [android.app.Activity]
 *
 */
sealed class ApiResponseData {
    /**
     * contains data which comes on success call
     *
     * @property statusCode code to identify weather api succeed or fail
     * @property apiCode code to identify the api which is called based on [com.app.core.util.ApiCodes]
     * @property msg any error or success message
     */
    class API_SUCCEED(val statusCode: Int, val apiCode: Int, val msg: String?) : ApiResponseData()

    /**
     * called when there is any API error
     * @property exception observe any error in api
     * @property apiCode code to identify the api which is called based on [com.app.core.util.ApiCodes]
     */
    class API_EXCEPTION(val exception: ApiError, val apiCode: Int?=0) : ApiResponseData()

    /**
     *called when there is no internet connection
     *
     * @property apiCode code to identify the api which is called based on [com.app.core.util.ApiCodes]
     * @property msg any error or success messag
     */
    class NO_INTERNET(val apiCode: Int, val msg: String?) : ApiResponseData()
}

/**
 * Sealed class to observe changes on [BaseViewModel]
 *
 */
sealed class API_VIEWMODEL_DATA<T> {
    /**
     * contains data which comes on success call
     *
     * @property data any contained data on success callback
     * @property apiCode code to identify the api which is called based on [com.app.core.util.ApiCodes]
     */
    class API_SUCCEED<T>(val data: T?, val apiCode: Int, val statusCode: Int) : API_VIEWMODEL_DATA<T>()

    /**
     *called when there is any API error
     * @property apiCode code to identify the api which is called based on [com.app.core.util.ApiCodes]
     */
    class API_EXCEPTION<T>(val data: T?, val apiCode: Int?=0) : API_VIEWMODEL_DATA<T>()

    /**
     * called when there is no internet connection
     * @property apiCode code to identify the api which is called based on [com.app.core.util.ApiCodes]
     */
    class NO_INTERNET<T>(val apiCode: Int) : API_VIEWMODEL_DATA<T>()
}
