package com.dominikdev.tinker.users

import jakarta.persistence.*

@Entity
@Table(name = "experts")
data class Expert(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "identity_email", nullable = false)
    val identityEmail: String
)

@Entity
@Table(name = "clients")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "identity_email", nullable = false)
    val identityEmail: String
)