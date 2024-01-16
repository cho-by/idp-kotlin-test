package idp.solution.idpkotlintest.adapter.`in`.web

import idp.solution.idpkotlintest.adapter.`in`.web.dto.LoginRequestDTO
import idp.solution.idpkotlintest.application.LoginService
import idp.solution.idpkotlintest.application.port.`in`.LogInUseCase
import idp.solution.idpkotlintest.application.port.`in`.LogOutUseCase
import idp.solution.idpkotlintest.application.port.`in`.LoginCheckUseCase
import idp.solution.idpkotlintest.domain.LoginEnum
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/login")
class LoginController (
    private val loginService: LoginService
) {

    @GetMapping
    fun loginCheck(httpSession: HttpSession): LoginCheckUseCase.Result {
        return loginService.loginCheck(httpSession.getAttribute(LoginEnum.LOGIN_INFO.code()))
    }

    @PostMapping
    fun login(httpSession: HttpSession, @RequestBody loginRequestDTO: LoginRequestDTO): LogInUseCase.Result {
        return loginService.login(httpSession, loginRequestDTO)
    }

    @DeleteMapping
    fun logout(httpSession: HttpSession): LogOutUseCase.Result {
        return loginService.logout(httpSession)
    }
}