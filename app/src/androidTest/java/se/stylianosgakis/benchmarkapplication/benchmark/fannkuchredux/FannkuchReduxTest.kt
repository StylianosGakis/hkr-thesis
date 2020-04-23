package se.stylianosgakis.benchmarkapplication.benchmark.fannkuchredux

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FannkuchReduxTest {
    @Test
    fun fannkuch() {
        val n = 7
        val (kotlinChecksum, kotlinMaxRotations) = FannkuchReduxKotlin().benchmark(n)
        assertEquals(16, kotlinMaxRotations)
        assertEquals(-18, kotlinChecksum)
        val (javaChecksum, javaMaxRotations) = FannkuchReduxJava().benchmark(n)
        assertEquals(16, javaMaxRotations)
        assertEquals(-18, javaChecksum)
    }

    @Test
    fun findAllPermutations() {
        val expectedResult = listOf(
            intArrayOf(1, 2, 3),
            intArrayOf(2, 1, 3),
            intArrayOf(3, 1, 2),
            intArrayOf(1, 3, 2),
            intArrayOf(2, 3, 1),
            intArrayOf(3, 2, 1)
        ).map { it.toList() }.toString()
        val kotlinResult = FannkuchReduxKotlin().getAllPermutations(3)
        val javaResult = FannkuchReduxJava().getAllPermutations(3)
        assertEquals(
            expectedResult,
            kotlinResult.map { it.toList() }.toString()
        )
        assertEquals(
            expectedResult,
            javaResult.map { it.toList() }.toString()
        )
        Log.d("TEST kotlin", kotlinResult.map { it.toList() }.toString())
        Log.d("TEST java", javaResult.map { it.toList() }.toString())
    }

    @Test
    fun calculateRotations() {
        val kotlinResult =
            FannkuchReduxKotlin().calculateRotations(intArrayOf(7, 5, 2, 1, 3, 4, 6))
        assertEquals(5, kotlinResult)
        val javaResult =
            FannkuchReduxJava().calculateRotations(intArrayOf(7, 5, 2, 1, 3, 4, 6))
        assertEquals(5, javaResult)
    }
}