package se.stylianosgakis.benchmarkapplication.benchmark.fasta

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FastaTest {
    @Test
    fun fasta() {
        val resultJava = FastaJava().benchmark(1000)
        val resultKotlin = FastaKotlin().benchmark(1000)
        assertEquals(resultKotlin, resultJava)
    }
}