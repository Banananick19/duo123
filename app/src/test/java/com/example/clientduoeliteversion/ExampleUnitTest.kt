package com.example.clientduoeliteversion

import com.example.clientduoeliteversion.Screens.validIp
import com.example.clientduoeliteversion.Screens.validPort
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun validIp_isCorrect() {
        assertEquals(true, validIp("0.0.0.0"))
        assertEquals(true, validIp("0.241.2.2"))
        assertEquals(true, validIp("10.241.2.2"))
        assertEquals(false, validIp("256.241.2.2"))
        assertEquals(false, validIp("10.241.2.2.2"))
        assertEquals(false, validIp("10.241.345.2"))
    }

    @Test
    fun validPort_isCorrect() {
        assertEquals(true, validPort((123).toString()))
        assertEquals(false, validPort((-123).toString()))
        assertEquals(false, validPort((225123).toString()))
        assertEquals(false, validPort((0).toString()))
    }
}