package benchmark.fasta

import java.lang.Integer.min

/*
 * The Computer Language Benchmarks Game
 * http://benchmarksgame.alioth.debian.org/
 *
 * modified by Mehmet D. AKIN
 * further modified for the purpose of this paper by Stylianos Gakis
 */
private const val IM = 139968
private const val IA = 3877
private const val IC = 29573
private const val LINE_LENGTH = 60
private const val ALU = ("GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGG"
        + "GAGGCCGAGGCGGGCGGATCACCTGAGGTCAGGAGTTCGAGA"
        + "CCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACTAAAAAT"
        + "ACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCA"
        + "GCTACTCGGGAGGCTGAGGCAGGAGAATCGCTTGAACCCGGG"
        + "AGGCGGAGGTTGCAGTGAGCCGAGATCGCGCCACTGCACTCC"
        + "AGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAA")
private const val BUFFER_SIZE = 1024
private val IUB = arrayOf(
    Frequency('a', 0.27),
    Frequency('c', 0.12),
    Frequency('g', 0.12),
    Frequency('t', 0.27),
    Frequency('B', 0.02),
    Frequency('D', 0.02),
    Frequency('H', 0.02),
    Frequency('K', 0.02),
    Frequency('M', 0.02),
    Frequency('N', 0.02),
    Frequency('R', 0.02),
    Frequency('S', 0.02),
    Frequency('V', 0.02),
    Frequency('W', 0.02),
    Frequency('Y', 0.02)
)
private val HomoSapiens = arrayOf(
    Frequency('a', 0.3029549426680),
    Frequency('c', 0.1979883004921),
    Frequency('g', 0.1975473066391),
    Frequency('t', 0.3015094502008)
)

class FastaKotlin {
    fun benchmark() = benchmark(100000)

    fun benchmark(n: Int): String {
        makeCumulative(HomoSapiens)
        makeCumulative(IUB)
        val stringBuilder = StringBuilder()
        stringBuilder.append(">ONE Homo sapiens alu\n")
        makeRepeatFasta(ALU, n * 2, stringBuilder)
        stringBuilder.append(">TWO IUB ambiguity codes\n")
        makeRandomFasta(IUB, n * 3, stringBuilder)
        stringBuilder.append(">THREE Homo sapiens frequency\n")
        makeRandomFasta(HomoSapiens, n * 5, stringBuilder)
        return stringBuilder.toString()
    }

    private var seed = 42
    private var charBufferIndex = 0
    private val charBuffer = CharArray(BUFFER_SIZE)

    // pseudo-random number generator
    private fun pseudoRandom(max: Double): Double {
        seed = (seed * IA + IC) % IM
        return max * seed / IM
    }

    private fun makeCumulative(frequencies: Array<Frequency>) {
        var cumulativePercentage = 0.0
        for (frequency in frequencies) {
            cumulativePercentage += frequency.chance
            frequency.chance = cumulativePercentage
        }
    }

    // naive
    private fun selectRandom(frequencies: Array<Frequency>): Char {
        val length = frequencies.size
        val random = pseudoRandom(1.0)
        for (frequency in frequencies) {
            if (random < frequency.chance) {
                return frequency.character
            }
        }
        return frequencies[length - 1].character
    }

    private fun makeRandomFasta(
        frequencies: Array<Frequency>,
        charsLeftToWrite: Int,
        stringBuilder: StringBuilder
    ) {
        var mutableCharsLeftToWrite = charsLeftToWrite
        charBufferIndex = 0
        while (mutableCharsLeftToWrite > 0) {
            val currentCharsToWrite = min(mutableCharsLeftToWrite, LINE_LENGTH)
            if (BUFFER_SIZE - charBufferIndex < currentCharsToWrite) {
                stringBuilder.append(String(charBuffer))
                charBufferIndex = 0
            }
            repeat(currentCharsToWrite) {
                charBuffer[charBufferIndex++] = selectRandom(frequencies)
            }
            charBuffer[charBufferIndex++] = '\n'
            mutableCharsLeftToWrite -= LINE_LENGTH
        }
        if (charBufferIndex != 0) {
            stringBuilder.append(String(charBuffer))
        }
    }

    private fun makeRepeatFasta(
        alu: String,
        charsLeftToWrite: Int,
        stringBuilder: StringBuilder
    ) {
        var mutableCharsLeftToWrite = charsLeftToWrite
        charBufferIndex = 0
        var aluIndex = 0
        while (mutableCharsLeftToWrite > 0) {
            val currentCharsToWrite = min(mutableCharsLeftToWrite, LINE_LENGTH)
            if (BUFFER_SIZE - charBufferIndex < currentCharsToWrite) {
                stringBuilder.append(String(charBuffer))
                charBufferIndex = 0
            }
            repeat (currentCharsToWrite) {
                if (aluIndex == alu.length) {
                    aluIndex = 0
                }
                charBuffer[charBufferIndex++] = alu[aluIndex++]
            }
            charBuffer[charBufferIndex++] = '\n'
            mutableCharsLeftToWrite -= LINE_LENGTH
        }
        if (charBufferIndex != 0) {
            stringBuilder.append(String(charBuffer))
        }
    }
}

class Frequency(var character: Char, var chance: Double)