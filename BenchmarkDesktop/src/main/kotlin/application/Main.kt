package application

import benchmark.utils.BenchmarkClass
import benchmark.utils.BenchmarkHandler.runBenchmark
import benchmark.utils.BenchmarkLanguage
import benchmark.utils.BenchmarkType
import benchmark.utils.saveResultToFile

val benchmarkMap = mapOf(
    1 to Triple(BenchmarkType.SpeedType, BenchmarkClass.Faankuch, BenchmarkLanguage.Kotlin),
    2 to Triple(BenchmarkType.MemoryType, BenchmarkClass.Faankuch, BenchmarkLanguage.Kotlin),
    3 to Triple(BenchmarkType.SpeedType, BenchmarkClass.Faankuch, BenchmarkLanguage.Java),
    4 to Triple(BenchmarkType.MemoryType, BenchmarkClass.Faankuch, BenchmarkLanguage.Java),
    5 to Triple(BenchmarkType.SpeedType, BenchmarkClass.NBody, BenchmarkLanguage.Kotlin),
    6 to Triple(BenchmarkType.MemoryType, BenchmarkClass.NBody, BenchmarkLanguage.Kotlin),
    7 to Triple(BenchmarkType.SpeedType, BenchmarkClass.NBody, BenchmarkLanguage.Java),
    8 to Triple(BenchmarkType.MemoryType, BenchmarkClass.NBody, BenchmarkLanguage.Java),
    9 to Triple(BenchmarkType.SpeedType, BenchmarkClass.Fasta, BenchmarkLanguage.Kotlin),
    10 to Triple(BenchmarkType.MemoryType, BenchmarkClass.Fasta, BenchmarkLanguage.Kotlin),
    11 to Triple(BenchmarkType.SpeedType, BenchmarkClass.Fasta, BenchmarkLanguage.Java),
    12 to Triple(BenchmarkType.MemoryType, BenchmarkClass.Fasta, BenchmarkLanguage.Java),
    13 to Triple(BenchmarkType.SpeedType, BenchmarkClass.ReverseComplement, BenchmarkLanguage.Kotlin),
    14 to Triple(BenchmarkType.MemoryType, BenchmarkClass.ReverseComplement, BenchmarkLanguage.Kotlin),
    15 to Triple(BenchmarkType.SpeedType, BenchmarkClass.ReverseComplement, BenchmarkLanguage.Java),
    16 to Triple(BenchmarkType.MemoryType, BenchmarkClass.ReverseComplement, BenchmarkLanguage.Java)
)

fun main() {
    //runAll()
    runMenu()
}

fun runAll() {
    for (benchmarkTriple in benchmarkMap.values) {
        if (benchmarkTriple.first is BenchmarkType.MemoryType) {
            activityBenchmark(benchmarkTriple.first, benchmarkTriple.second, benchmarkTriple.third)
        }
    }
}

fun runMenu() {
    while (true) {
        val benchmarkChoice = pickBenchmarkMenu()
        benchmarkMap[benchmarkChoice]?.run {
            activityBenchmark(first, second, third)
        }
    }
}

fun pickBenchmarkMenu(): Int {
    print(
        """
        Pick benchmark:
        1. Kotlin Faankuch Speed
        2. Kotlin Faankuch Memory
        3. Java Faankuch Speed
        4. Java Faankuch Memory
        5. Kotlin NBody Speed
        6. Kotlin NBody Memory
        7. Java NBody Speed
        8. Java NBody Memory
        9. Kotlin Fasta Speed
        10. Kotlin Fasta Memory
        11. Java Fasta Speed
        12. Java Fasta Memory
        13. Kotlin ReverseComplement Speed
        14. Kotlin ReverseComplement Memory
        15. Java ReverseComplement Speed
        16. Java ReverseComplement Memory
        >> 
    """.trimIndent()
    )
    return readLine()!!.toInt()
}

private fun activityBenchmark(
    benchmarkType: BenchmarkType,
    benchmarkClass: BenchmarkClass,
    benchmarkLanguage: BenchmarkLanguage
) {
    println("Running: $benchmarkType, $benchmarkClass, $benchmarkLanguage")
    val results = runBenchmark(benchmarkType, benchmarkClass, benchmarkLanguage)
    println("Results: $results")
    results?.let {
        saveResultToFile(
            benchmarkType,
            benchmarkClass,
            benchmarkLanguage,
            results
        )
    }
}