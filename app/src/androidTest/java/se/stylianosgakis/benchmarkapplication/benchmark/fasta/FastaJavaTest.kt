package se.stylianosgakis.benchmarkapplication.benchmark.fasta

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import java.io.File

@RunWith(AndroidJUnit4::class)
class FastaJavaTest {
    @Test
    fun fasta() {
        // Check equality up to the 9th most significant decimal point
        val resultJava = FastaJava().benchmark(1000)
        val resultKotlin = FastaKotlin().benchmark(1000)
        assertEquals(resultKotlin, resultJava)
    }
}