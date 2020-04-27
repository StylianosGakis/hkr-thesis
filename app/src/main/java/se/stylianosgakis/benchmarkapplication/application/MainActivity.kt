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
        benchmarking.observe(this) { isBenchmarking ->
            binding.progressBar.setVisible(isBenchmarking)
            binding.fannkuchJavaMemory.isEnabled = isBenchmarking.not()
            binding.fannkuchJavaSpeed.isEnabled = isBenchmarking.not()
            binding.fannkuchKotlinMemory.isEnabled = isBenchmarking.not()
            binding.fannkuchKotlinSpeed.isEnabled = isBenchmarking.not()
            binding.nBodyJavaMemory.isEnabled = isBenchmarking.not()
            binding.nBodyJavaSpeed.isEnabled = isBenchmarking.not()
            binding.nBodyKotlinMemory.isEnabled = isBenchmarking.not()
            binding.nBodyKotlinSpeed.isEnabled = isBenchmarking.not()
            binding.fastaJavaMemory.isEnabled = isBenchmarking.not()
            binding.fastaJavaSpeed.isEnabled = isBenchmarking.not()
            binding.fastaKotlinMemory.isEnabled = isBenchmarking.not()
            binding.fastaKotlinSpeed.isEnabled = isBenchmarking.not()
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
        /*********/
        /* Fasta */
        /*********/
        binding.fastaKotlinSpeed.setOnClickListener {
            activityBenchmark(
                BenchmarkType.SpeedType,
                BenchmarkClass.Fasta,
                BenchmarkLanguage.Kotlin
            )
        }
        binding.fastaKotlinMemory.setOnClickListener {
            activityBenchmark(
                BenchmarkType.MemoryType,
                BenchmarkClass.Fasta,
                BenchmarkLanguage.Kotlin
            )
        }
        binding.fastaJavaSpeed.setOnClickListener {
            activityBenchmark(
                BenchmarkType.SpeedType,
                BenchmarkClass.Fasta,
                BenchmarkLanguage.Java
            )
        }
        binding.fastaJavaMemory.setOnClickListener {
            activityBenchmark(
                BenchmarkType.MemoryType,
                BenchmarkClass.Fasta,
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
