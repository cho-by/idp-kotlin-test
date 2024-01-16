package idp.solution.idpkotlintest.adapter.out.persistence.repository

import idp.solution.idpkotlintest.adapter.out.persistence.entity.LoginEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LoginJPA : JpaRepository<LoginEntity, String>