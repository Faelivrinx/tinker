package com.dominikdev.tinker.security

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
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
    private var enabled: Boolean,

    @Column(unique = true, nullable = false)
    val email: String, // Custom field

    @Column(nullable = false)
    private var notBlocked: Boolean,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "identity_authorities", // Custom table name
        joinColumns = [JoinColumn(name = "identity_id")] // Foreign key column
    )
    @Column(name = "authority") // Column name for elements in the collection
    private val authorities: Collection<String> = listOf()

) :  UserDetails {

    protected constructor() : this(0, "", "", false,"", false, listOf())

    fun enableAccount() {
        this.enabled = true
    }

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities.map { SimpleGrantedAuthority(it) }
    override fun getPassword(): String = password
    override fun getUsername(): String = username

    override fun isAccountNonLocked(): Boolean = notBlocked
    override fun isAccountNonExpired(): Boolean = true
}