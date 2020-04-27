package se.stylianosgakis.benchmarkapplication.benchmark.utils

sealed class BenchmarkLanguage {
    object Kotlin : BenchmarkLanguage()
    object Java : BenchmarkLanguage()
}

sealed class BenchmarkClass {
    object Faankuch : BenchmarkClass()
    object NBody : BenchmarkClass()
    object Fasta : BenchmarkClass()
}

sealed class BenchmarkType {
    object SpeedType : BenchmarkType()
    object MemoryType : BenchmarkType()
}

sealed class BenchmarkResult {
    data class SpeedResult(
        val time: Long
    ) : BenchmarkResult()

    data class MemoryResult(
        val memoryAllocationCount: Int,
        val memoryAllocationSize: Int
    ) : BenchmarkResult()
}

fun Long.nanosToMilliseconds() = this.toDouble() / 1_000_000