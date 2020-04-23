package se.stylianosgakis.benchmarkapplication.benchmark.utils

sealed class BenchmarkLanguage {
    object Kotlin : BenchmarkLanguage()
    object Java : BenchmarkLanguage()
}

sealed class BenchmarkClass {
    object Faankuch : BenchmarkClass()
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
        val memoryAllocationSize: Int,
        val memoryBytesPerObject: Double
    ) : BenchmarkResult()
}

/*fun BenchmarkResult.SpeedResult.detailedString() = "Average: %.4f, min: %.4f, max: %.4f"
    .format(
        averageTime.nanosToMilliseconds(),
        minTime.nanosToMilliseconds(),
        maxTime.nanosToMilliseconds()
    )*/



fun BenchmarkResult.MemoryResult.detailedString() =
    "mem count: $memoryAllocationCount, mem alloc size: $memoryAllocationSize, mem bytes per object: $memoryBytesPerObject"

fun Long.nanosToMilliseconds() = this.toDouble() / 1_000_000