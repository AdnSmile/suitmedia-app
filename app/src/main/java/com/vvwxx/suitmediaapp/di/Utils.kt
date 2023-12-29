package com.vvwxx.suitmediaapp.di

object Utils {

    fun isPalindrome(name: String): Boolean {
        val nameWithoutSpace = name.replace("\\s".toRegex(), "")
        val nameReversed = nameWithoutSpace.reversed()
        return nameWithoutSpace.equals(nameReversed, ignoreCase = true)
    }

}