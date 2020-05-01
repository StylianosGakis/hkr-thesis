package benchmark.utils

import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

const val FANNKUCH_SIZE = 8
const val ITERATIONS = 100

// Disregard first 20% of the iterations, to ensure more consistent results
const val WARMED_UP_ITERATIONS = 80

fun benchmarkMemory(benchmark: () -> Any): List<BenchmarkResult> {
    val runtime = Runtime.getRuntime()
    runtime.gc()
    val memoryBefore = runtime.totalMemory() - runtime.freeMemory()
    benchmark()
    val memoryAfter = runtime.totalMemory() - runtime.freeMemory()
    val memoryUsed = memoryAfter - memoryBefore
    return listOf(BenchmarkResult.MemoryResult(1, memoryUsed.toInt()))
}

fun benchmarkSpeed(benchmark: () -> Any): List<BenchmarkResult> {
    val times = mutableListOf<Long>()
    repeat(ITERATIONS) {
        val startTime = System.nanoTime()
        benchmark()
        val endTime = System.nanoTime()
        val elapsedTime = endTime - startTime
        times.add(elapsedTime)
    }
    println("$times")
    val warmedUpResults = times.takeLast(WARMED_UP_ITERATIONS)
    return warmedUpResults.map {
        BenchmarkResult.SpeedResult(it)
    }
}

fun saveResultToFile(
    benchmarkType: BenchmarkType,
    benchmarkClass: BenchmarkClass,
    benchmarkLanguage: BenchmarkLanguage,
    results: List<BenchmarkResult>
) {
    val type = generateFileName(benchmarkType, benchmarkClass, benchmarkLanguage)
    val dateAndTime = SimpleDateFormat("yyyy-MM-dd-HH_mm_ss").format(Date())
    val fileName = "${type}_${dateAndTime}.txt"
    //LogResultsToFileTask().execute(Pair(results, fileName))
    val className = when (benchmarkClass) {
        BenchmarkClass.Faankuch -> "Faankuch"
        BenchmarkClass.NBody -> "NBody"
        BenchmarkClass.Fasta -> "Fasta"
        BenchmarkClass.ReverseComplement -> "ReverseComplement"
    }
    val root = File(
        "src/main/resources/results/$className"
    )
    if (root.exists().not()) {
        root.mkdirs()
    }
    val resultFile = File(root, fileName)
    resultFile.createNewFile()
    FileWriter(resultFile).use {
        writeToFile(results, it)
    }
}

fun writeToFile(results: List<BenchmarkResult>, writer: FileWriter) {
    when (results.first()) {
        is BenchmarkResult.SpeedResult -> {
            val speedResultsInMilliseconds =
                results.map { (it as BenchmarkResult.SpeedResult).time.nanosToMilliseconds() }
            writer.append("Time shown in milliseconds\n")
                .append(speedResultsInMilliseconds.joinToString(separator = "\n"))
                .append("\n---------- ---------- ----------\n")
                .append("Max: ${speedResultsInMilliseconds.max()}\n")
                .append("Min: ${speedResultsInMilliseconds.min()}\n")
                .append("Average: ${speedResultsInMilliseconds.average()}")
        }
        is BenchmarkResult.MemoryResult -> {
            val memoryResults = results.map { it as BenchmarkResult.MemoryResult }
            val memoryResultsStrings =
                memoryResults.map { "${it.memoryAllocationCount} | ${it.memoryAllocationSize}" }
            writer.append("Memory footprint shown as:\ncount of objects allocated | size of objects allocated\n")
                .append(memoryResultsStrings.joinToString(separator = "\n"))
        }
    }
}

/**
 * Return example: "SPEED_FAANKUCH_KOTLIN"
 */
private fun generateFileName(
    benchmarkType: BenchmarkType,
    benchmarkClass: BenchmarkClass,
    benchmarkLanguage: BenchmarkLanguage
): String {
    return "${when (benchmarkType) {
        BenchmarkType.SpeedType -> "SPEED"
        BenchmarkType.MemoryType -> "MEMORY"
    }}_${when (benchmarkClass) {
        BenchmarkClass.Faankuch -> "FAANKUCH"
        BenchmarkClass.NBody -> "NBODY"
        BenchmarkClass.Fasta -> "FASTA"
        BenchmarkClass.ReverseComplement -> "REVERSECOMPLEMENT"
    }}_${when (benchmarkLanguage) {
        BenchmarkLanguage.Kotlin -> "KOTLIN"
        BenchmarkLanguage.Java -> "JAVA"
    }}"
}
