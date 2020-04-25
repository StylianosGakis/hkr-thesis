package se.stylianosgakis.benchmarkapplication.application

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import se.stylianosgakis.benchmarkapplication.benchmark.utils.BenchmarkClass
import se.stylianosgakis.benchmarkapplication.benchmark.utils.BenchmarkHandler.runBenchmark
import se.stylianosgakis.benchmarkapplication.benchmark.utils.BenchmarkLanguage
import se.stylianosgakis.benchmarkapplication.benchmark.utils.BenchmarkType
import se.stylianosgakis.benchmarkapplication.benchmark.utils.saveResultToFile
import se.stylianosgakis.benchmarkapplication.benchmark.utils.setVisible
import se.stylianosgakis.benchmarkapplication.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var binding: ActivityMainBinding
    private val benchmarking = MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestStoragePermission()
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        benchmarking.observe(this) { benchmarking ->
            binding.progressBar.setVisible(benchmarking)
            binding.fannkuchJavaMemory.isEnabled = benchmarking.not()
            binding.fannkuchJavaSpeed.isEnabled = benchmarking.not()
            binding.fannkuchKotlinMemory.isEnabled = benchmarking.not()
            binding.fannkuchKotlinSpeed.isEnabled = benchmarking.not()
            binding.nBodyJavaMemory.isEnabled = benchmarking.not()
            binding.nBodyJavaSpeed.isEnabled = benchmarking.not()
            binding.nBodyKotlinMemory.isEnabled = benchmarking.not()
            binding.nBodyKotlinSpeed.isEnabled = benchmarking.not()
        }
    }

    private fun setupClickListeners() {
        /************/
        /* Fannkuch */
        /************/
        binding.fannkuchKotlinSpeed.setOnClickListener {
            activityBenchmark(
                BenchmarkType.SpeedType,
                BenchmarkClass.Faankuch,
                BenchmarkLanguage.Kotlin
            )
        }
        binding.fannkuchKotlinMemory.setOnClickListener {
            activityBenchmark(
                BenchmarkType.MemoryType,
                BenchmarkClass.Faankuch,
                BenchmarkLanguage.Kotlin
            )
        }
        binding.fannkuchJavaSpeed.setOnClickListener {
            activityBenchmark(
                BenchmarkType.SpeedType,
                BenchmarkClass.Faankuch,
                BenchmarkLanguage.Java
            )
        }
        binding.fannkuchJavaMemory.setOnClickListener {
            activityBenchmark(
                BenchmarkType.MemoryType,
                BenchmarkClass.Faankuch,
                BenchmarkLanguage.Java
            )
        }
        /**********/
        /* N-Body */
        /**********/
        binding.nBodyKotlinSpeed.setOnClickListener {
            activityBenchmark(
                BenchmarkType.SpeedType,
                BenchmarkClass.NBody,
                BenchmarkLanguage.Kotlin
            )
        }
        binding.nBodyKotlinMemory.setOnClickListener {
            activityBenchmark(
                BenchmarkType.MemoryType,
                BenchmarkClass.NBody,
                BenchmarkLanguage.Kotlin
            )
        }
        binding.nBodyJavaSpeed.setOnClickListener {
            activityBenchmark(
                BenchmarkType.SpeedType,
                BenchmarkClass.NBody,
                BenchmarkLanguage.Java
            )
        }
        binding.nBodyJavaMemory.setOnClickListener {
            activityBenchmark(
                BenchmarkType.MemoryType,
                BenchmarkClass.NBody,
                BenchmarkLanguage.Java
            )
        }
    }

    private fun activityBenchmark(
        benchmarkType: BenchmarkType,
        benchmarkClass: BenchmarkClass,
        benchmarkLanguage: BenchmarkLanguage
    ) = launch(Dispatchers.Main) {
        benchmarking.value = true
        val results = runBenchmark(benchmarkType, benchmarkClass, benchmarkLanguage)
        Timber.d("$results")
        benchmarking.value = false
        saveResultToFile(
            benchmarkType,
            benchmarkClass,
            benchmarkLanguage,
            results,
            applicationContext
        )
    }

    private fun requestStoragePermission() {
        val permission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    }
}
