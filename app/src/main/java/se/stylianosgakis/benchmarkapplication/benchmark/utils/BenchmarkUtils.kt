package se.stylianosgakis.benchmarkapplication.benchmark.utils

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Debug
import android.os.Environment
import android.os.SystemClock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.util.*

const val FANNKUCH_SIZE = 8
const val ITERATIONS = 100

// Disregard first 20% of the iterations, to ensure more consistent results
const val WARMED_UP_ITERATIONS = 80

fun benchmarkMemory(benchmark: () -> Any): List<BenchmarkResult> {
    Debug.resetThreadAllocCount()
    Debug.resetThreadAllocSize()
    Debug.startAllocCounting()
    benchmark()
    Debug.stopAllocCounting()
    val memoryAllocCount = Debug.getThreadAllocCount()
    val memoryAllocSize = Debug.getThreadAllocSize()
    return listOf(BenchmarkResult.MemoryResult(memoryAllocCount, memoryAllocSize))
}

fun benchmarkSpeed(benchmark: () -> Any): List<BenchmarkResult> {
    val times = mutableListOf<Long>()
    repeat(ITERATIONS) {
        val startTime = SystemClock.elapsedRealtimeNanos()
        benchmark()
        val endTime = SystemClock.elapsedRealtimeNanos()
        val elapsedTime = endTime - startTime
        times.add(elapsedTime)
    }
    Timber.d("$times")
    val warmedUpResults = times.takeLast(WARMED_UP_ITERATIONS)
    return warmedUpResults.map {
        BenchmarkResult.SpeedResult(it)
    }
}

suspend fun saveResultToFile(
    benchmarkType: BenchmarkType,
    benchmarkClass: BenchmarkClass,
    benchmarkLanguage: BenchmarkLanguage,
    results: List<BenchmarkResult>,
    context: Context
) = withContext(Dispatchers.IO) {
    val type = generateFileName(benchmarkType, benchmarkClass, benchmarkLanguage)
    val dateAndTime = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(Date())
    val fileName = "${type}_${dateAndTime}.txt"
    //LogResultsToFileTask().execute(Pair(results, fileName))
    val root = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.absolutePath)
    if (root.exists().not()) {
        root.mkdirs()
    }
    val resultFile = File(root, fileName)
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
    }}_${when (benchmarkLanguage) {
        BenchmarkLanguage.Kotlin -> "KOTLIN"
        BenchmarkLanguage.Java -> "JAVA"
    }}"
}
