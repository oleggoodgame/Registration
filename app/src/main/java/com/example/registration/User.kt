package com.example.registration

data class User(val name: String, val password: String, val number: String, val calendar: String) {
    override fun toString(): String {
        val length = password.length
        var passwords =""
        for (i in 0 until length) {
            passwords += '*'
        }
        return "User Information(name='$name', password='$passwords', number=$number, calendar='$calendar')"
    }
}