package com.dominikdev.tinker.users.registration

interface CodeGenerator {
    fun generate() : String
    fun size() : Int
}