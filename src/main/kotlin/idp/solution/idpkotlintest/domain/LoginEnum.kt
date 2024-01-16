package idp.solution.idpkotlintest.domain

internal enum class LoginEnum(private val code: String) {
    LOGIN_INFO("loginInfo");

    @Synchronized
    fun code(): String {
        return code
    }
}