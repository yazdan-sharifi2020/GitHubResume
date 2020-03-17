package info.yazdan.githubresume

import org.junit.Test

import org.junit.Assert.*


class ExampleUnitTest {

    @Test
    fun usernameValidation() {
        assertEquals(true, validateUsername("fabpot"))
    }

    private fun validateUsername(username: String) : Boolean {
        if(username.isEmpty()) return false
        if (username.contains(" ") || username.contains("!") || username.contains("-") ||
            username.contains("*") || username.contains("&") || username.contains("^") ||
            username.contains("$") || username.contains("#") || username.contains("±")) return false
        return true
    }

}
