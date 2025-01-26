package com.dominikdev.tinker.users

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CodesRepositories : CrudRepository<CodeEntity, UUID> {
}