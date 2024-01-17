package idp.solution.idpkotlintest.adapter.out.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import idp.solution.idpkotlintest.adapter.out.persistence.entity.LoginEntity
import idp.solution.idpkotlintest.adapter.out.persistence.entity.QLoginEntity.loginEntity
import org.springframework.stereotype.Repository

@Repository
class LoginQuerydsl (
    private val jpaQueryFactory: JPAQueryFactory,
) {
    fun findById(email: String): LoginEntity {
        val list: List<LoginEntity> = jpaQueryFactory
            .selectFrom(loginEntity)
            .where(loginEntity.email.eq(email))
            .limit(1)
            .fetch()

        return if (list.isNotEmpty()) list.get(0) else LoginEntity(email = "")
    }
}