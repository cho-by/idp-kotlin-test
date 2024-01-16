package idp.solution.idpkotlintest.application

import idp.solution.idpkotlintest.adapter.`in`.web.dto.LoginRequestDTO
import idp.solution.idpkotlintest.adapter.out.persistence.entity.LoginEntity
import idp.solution.idpkotlintest.adapter.out.persistence.repository.LoginJPA
import idp.solution.idpkotlintest.application.port.`in`.LogInUseCase
import idp.solution.idpkotlintest.application.port.`in`.LogOutUseCase
import idp.solution.idpkotlintest.application.port.`in`.LoginCheckUseCase
import idp.solution.idpkotlintest.domain.LoginEnum
import idp.solution.idpkotlintest.domain.LoginVO
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service

@Service
class LoginService (
    private val loginJPA: LoginJPA,
):LoginCheckUseCase, LogInUseCase, LogOutUseCase {

    override fun loginCheck(loginVO: Any?): LoginCheckUseCase.Result {
        if (loginVO is LoginVO) {
            return LoginCheckUseCase.Result(email = loginVO.email, success = true)
        }

        return LoginCheckUseCase.Result(email = "", success = false)
    }

    override fun login(httpSession: HttpSession, loginRequestDTO: LoginRequestDTO): LogInUseCase.Result {
        val loginEntity: LoginEntity = loginJPA.findById(loginRequestDTO.email).orElse(LoginEntity(email = ""))

        if (loginEntity.email != "") {
            httpSession.setAttribute(LoginEnum.LOGIN_INFO.code(), LoginVO(email=loginEntity.email))

            return LogInUseCase.Result(email = loginEntity.email, success = true)
        }

        httpSession.invalidate()

        return LogInUseCase.Result(email = "", success = false)
    }

    override fun logout(httpSession: HttpSession): LogOutUseCase.Result {
        httpSession.invalidate()

        return LogOutUseCase.Result(success = true)
    }
}