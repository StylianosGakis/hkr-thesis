package se.stylianosgakis.benchmarkapplication.benchmark.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import se.stylianosgakis.benchmarkapplication.benchmark.fannkuchredux.FannkuchReduxJava
import se.stylianosgakis.benchmarkapplication.benchmark.fannkuchredux.FannkuchReduxKotlin

object BenchmarkHandler {
    private val fannkuchReduxJava = FannkuchReduxJava()
    private val fannkuchReduxKotlin = FannkuchReduxKotlin()

    suspend fun runBenchmark(
        benchmarkType: BenchmarkType,
        benchmarkClass: BenchmarkClass,
        benchmarkLanguage: BenchmarkLanguage
    ): List<BenchmarkResult> = withContext(Dispatchers.Default) {
        return@withContext when (benchmarkLanguage) {
            BenchmarkLanguage.Kotlin -> {
                when (benchmarkClass) {
                    BenchmarkClass.Faankuch -> {
                        when (benchmarkType) {
                            BenchmarkType.SpeedType -> benchmarkSpeed(fannkuchReduxKotlin::benchmark)
                            BenchmarkType.MemoryType -> benchmarkMemory(fannkuchReduxKotlin::benchmark)
                        }
                    }
                }
            }
            BenchmarkLanguage.Java -> {
                when (benchmarkClass) {
                    BenchmarkClass.Faankuch -> {
                        when (benchmarkType) {
                            BenchmarkType.SpeedType -> benchmarkSpeed(fannkuchReduxJava::benchmark)
                            BenchmarkType.MemoryType -> benchmarkMemory(fannkuchReduxJava::benchmark)
                        }
                    }
                }
            }
        }
    }
}