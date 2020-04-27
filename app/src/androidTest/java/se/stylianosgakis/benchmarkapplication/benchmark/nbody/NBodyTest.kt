package se.stylianosgakis.benchmarkapplication.benchmark.nbody

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NBodyTest {
    @Test
    fun nbody() {
        // Check equality up to the 9th most significant decimal point
        val resultJava = NBodyJava().benchmark()
        assertEquals(-0.169096567, resultJava, 0.000000001)
        val resultKotlin = NBodyKotlin().benchmark()
        assertEquals(-0.169096567, resultKotlin, 0.000000001)
        assertEquals(resultJava, resultKotlin, 0.000000001)
    }
}