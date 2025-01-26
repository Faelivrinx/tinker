package com.dominikdev.tinker.users.registration

import org.springframework.stereotype.Service

@Service
class FixedCodeGenerator : CodeGenerator {
    override fun generate(): String = "1234"
    override fun size(): Int = 4
}