package com.dominikdev.tinker.security

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "identity_users")
data class IdentityUserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    private val username: String,

    @Column(nullable = false)
    private val password: String,

    @Column(nullable = false)
    private val enabled: Boolean,

    @Column(unique = true, nullable = false)
    val email: String, // Custom field

    @Column(nullable = false)
    private val notBlocked: Boolean,

    @ElementCollection(fetch = FetchType.EAGER)
    private val authorities: Collection<GrantedAuthority>

) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities
    override fun getPassword(): String = password
    override fun getUsername(): String = username

    override fun isAccountNonLocked(): Boolean = notBlocked
    override fun isAccountNonExpired(): Boolean = true
}