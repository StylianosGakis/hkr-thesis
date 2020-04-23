package se.stylianosgakis.benchmarkapplication.benchmark.fannkuchredux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static se.stylianosgakis.benchmarkapplication.benchmark.utils.BenchmarkUtilsKt.FANNKUCH_SIZE;

public class FannkuchReduxJava {
    public ResultPair benchmark() {
        return benchmark(FANNKUCH_SIZE);
    }

    public ResultPair benchmark(int size) {
        List<int[]> listOfPermutations = getAllPermutations(size);
        int totalChecksum = 0;
        int maxRotations = 0;
        for (int index = 0; index < listOfPermutations.size(); index++) {
            int rotations = calculateRotations(listOfPermutations.get(index));
            maxRotations = Math.max(maxRotations, rotations);
            if (index % 2 == 0) {
                totalChecksum += rotations;
            } else {
                totalChecksum += -rotations;
            }
        }
        return new ResultPair(totalChecksum, maxRotations);
    }

    public int calculateRotations(int[] array) {
        int rotations = 0;
        int firstNumber = array[0];
        while (firstNumber != 1) {
            int[] firstHalf = Arrays.copyOfRange(array, 0, firstNumber);
            reverseIntArray(firstHalf);
            int[] secondHalf = Arrays.copyOfRange(array, firstNumber, array.length);
            array = Arrays.copyOf(firstHalf, firstHalf.length + secondHalf.length);
            System.arraycopy(secondHalf, 0, array, firstHalf.length, secondHalf.length);
            rotations++;
            firstNumber = array[0];
        }
        return rotations;
    }

    private void reverseIntArray(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int temp = array[start];
            array[start++] = array[end];
            array[end--] = temp;
        }
    }

    public List<int[]> getAllPermutations(int size) {
        int[] list = new int[size];
        for (int i = 1; i < size + 1; i++) {
            list[i - 1] = i;
        }
        List<int[]> permutations = new ArrayList<>();
        permutations.add(list.clone());
        int[] indexes = new int[size];
        int i = 0;
        while (i < size) {
            if (indexes[i] < i) {
                int a = i % 2 == 0 ? 0 : indexes[i];
                swap(list, a, i);
                permutations.add(list.clone());
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }
        return permutations;
    }

    private void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
}