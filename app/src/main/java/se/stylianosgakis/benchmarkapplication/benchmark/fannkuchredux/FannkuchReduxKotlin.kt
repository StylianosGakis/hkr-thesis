package se.stylianosgakis.benchmarkapplication.benchmark.fannkuchredux

import se.stylianosgakis.benchmarkapplication.benchmark.utils.FANNKUCH_SIZE
import kotlin.math.max

class FannkuchReduxKotlin {
    fun benchmark(): ResultPair {
        return benchmark(FANNKUCH_SIZE)
    }

    fun benchmark(size: Int): ResultPair {
        val listOfPermutations = getAllPermutations(size)
        var totalChecksum = 0
        var maxRotations = 0
        listOfPermutations.forEachIndexed { index, permutation ->
            val rotations = calculateRotations(permutation)
            maxRotations = max(maxRotations, rotations)
            totalChecksum += if (index % 2 == 0) rotations else -rotations
        }
        return ResultPair(totalChecksum, maxRotations)
    }

    fun calculateRotations(array: IntArray): Int {
        var mutableArray = array
        var rotations = 0
        var firstNumber = array.first()
        while (firstNumber != 1) {
            var firstHalf = mutableArray.copyOfRange(0, firstNumber)
            firstHalf.reverse()
            firstHalf = firstHalf.toList().toIntArray()
            val secondHalf = mutableArray.copyOfRange(firstNumber, mutableArray.size)
            mutableArray = firstHalf + secondHalf
            rotations++
            firstNumber = mutableArray.first()
        }
        return rotations
    }

    fun getAllPermutations(size: Int): List<IntArray> {
        val list: IntArray = (1..size).toList().toIntArray()
        val permutations = mutableListOf(list.clone())
        val indexes = IntArray(size)
        var i = 0
        while (i < size) {
            if (indexes[i] < i) {
                swap(list, if (i % 2 == 0) 0 else indexes[i], i)
                permutations.add(list.clone())
                indexes[i]++
                i = 0
            } else {
                indexes[i] = 0
                i++
            }
        }
        return permutations
    }

    private fun swap(input: IntArray, a: Int, b: Int) {
        val tmp: Int = input[a]
        input[a] = input[b]
        input[b] = tmp
    }
}