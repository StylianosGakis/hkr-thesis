package se.stylianosgakis.benchmarkapplication.benchmark.reversecomplement

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReverseComplementTest {
    @Test
    fun reverseComplement() {
        val resultJava = ReverseComplementJava().benchmark()
        val resultKotlin = ReverseComplementKotlin().benchmark()
        assertEquals(resultKotlin, resultJava)
    }
}