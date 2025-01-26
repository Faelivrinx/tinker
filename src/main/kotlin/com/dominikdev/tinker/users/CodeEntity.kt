package com.dominikdev.tinker.users

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "registration_codes")
data class CodeEntity (
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val code: String
) {
    constructor() : this(UUID.randomUUID(), "")

}