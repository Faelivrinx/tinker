package com.dominikdev.tinker.users

import org.springframework.data.jpa.repository.JpaRepository

interface ClientsRepository : JpaRepository<Client, Long>

interface ExpertsRepository : JpaRepository<Expert, Long>
