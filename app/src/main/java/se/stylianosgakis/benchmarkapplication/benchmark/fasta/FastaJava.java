/*
 * The Computer Language Benchmarks Game
 * http://benchmarksgame.alioth.debian.org/
 *
 * modified by Mehmet D. AKIN
 * further modified for the purpose of this paper by Stylianos Gakis
 */

package se.stylianosgakis.benchmarkapplication.benchmark.fasta;

public class FastaJava {
    // Weighted selection from alphabet
    private final String ALU = "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGG"
            + "GAGGCCGAGGCGGGCGGATCACCTGAGGTCAGGAGTTCGAGA"
            + "CCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACTAAAAAT"
            + "ACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCA"
            + "GCTACTCGGGAGGCTGAGGCAGGAGAATCGCTTGAACCCGGG"
            + "AGGCGGAGGTTGCAGTGAGCCGAGATCGCGCCACTGCACTCC"
            + "AGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAA";

    private final FrequencyJava[] IUB = new FrequencyJava[]{
            new FrequencyJava('a', 0.27),
            new FrequencyJava('c', 0.12),
            new FrequencyJava('g', 0.12),
            new FrequencyJava('t', 0.27),

            new FrequencyJava('B', 0.02),
            new FrequencyJava('D', 0.02),
            new FrequencyJava('H', 0.02),
            new FrequencyJava('K', 0.02),
            new FrequencyJava('M', 0.02),
            new FrequencyJava('N', 0.02),
            new FrequencyJava('R', 0.02),
            new FrequencyJava('S', 0.02),
            new FrequencyJava('V', 0.02),
            new FrequencyJava('W', 0.02),
            new FrequencyJava('Y', 0.02)
    };

    private final FrequencyJava[] HomoSapiens = new FrequencyJava[]{
            new FrequencyJava('a', 0.3029549426680d),
            new FrequencyJava('c', 0.1979883004921d),
            new FrequencyJava('g', 0.1975473066391d),
            new FrequencyJava('t', 0.3015094502008d)
    };

    public String benchmark() {
        return benchmark(100000);
    }

    public String benchmark(int n) {
        makeCumulative(HomoSapiens);
        makeCumulative(IUB);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">ONE Homo sapiens alu\n");
        makeRepeatFasta(ALU, n * 2, stringBuilder);
        stringBuilder.append(">TWO IUB ambiguity codes\n");
        makeRandomFasta(IUB, n * 3, stringBuilder);
        stringBuilder.append(">THREE Homo sapiens frequency\n");
        makeRandomFasta(HomoSapiens, n * 5, stringBuilder);
        return stringBuilder.toString();
    }

    private static final int IM = 139968;
    private static final int IA = 3877;
    private static final int IC = 29573;
    private static int seed = 42;

    private static final int LINE_LENGTH = 60;

    // pseudo-random number generator
    private double pseudoRandom(double max) {
        seed = (seed * IA + IC) % IM;
        return max * seed / IM;
    }

    private void makeCumulative(FrequencyJava[] frequencies) {
        double cumulativePercentage = 0.0;
        for (FrequencyJava frequency : frequencies) {
            cumulativePercentage += frequency.chance;
            frequency.chance = cumulativePercentage;
        }
    }

    // naive
    private char selectRandom(FrequencyJava[] frequencies) {
        int length = frequencies.length;
        double random = pseudoRandom(1.0);
        for (FrequencyJava frequency : frequencies) {
            if (random < frequency.chance) {
                return frequency.character;
            }
        }
        return frequencies[length - 1].character;
    }

    private int BUFFER_SIZE = 1024;
    private int charBufferIndex = 0;
    private char[] charBuffer = new char[BUFFER_SIZE];

    private void makeRandomFasta(FrequencyJava[] frequencies, int charsLeftToWrite, StringBuilder stringBuilder) {
        charBufferIndex = 0;
        while (charsLeftToWrite > 0) {
            int currentCharsToWrite = Math.min(charsLeftToWrite, LINE_LENGTH);
            if (BUFFER_SIZE - charBufferIndex < currentCharsToWrite) {
                stringBuilder.append(new String(charBuffer));
                charBufferIndex = 0;
            }
            for (int i = 0; i < currentCharsToWrite; i++) {
                charBuffer[charBufferIndex++] = selectRandom(frequencies);
            }
            charBuffer[charBufferIndex++] = '\n';
            charsLeftToWrite -= LINE_LENGTH;
        }
        if (charBufferIndex != 0) {
            stringBuilder.append(new String(charBuffer));
        }
    }

    private void makeRepeatFasta(String alu, int charsLeftToWrite, StringBuilder stringBuilder) {
        charBufferIndex = 0;
        int aluIndex = 0;
        int aluLength = alu.length();
        while (charsLeftToWrite > 0) {
            int currentCharsToWrite = Math.min(charsLeftToWrite, LINE_LENGTH);
            if (BUFFER_SIZE - charBufferIndex < currentCharsToWrite) {
                stringBuilder.append(new String(charBuffer));
                charBufferIndex = 0;
            }
            for (int i = 0; i < currentCharsToWrite; i++) {
                if (aluIndex == aluLength) {
                    aluIndex = 0;
                }
                charBuffer[charBufferIndex++] = alu.charAt(aluIndex++);
            }
            charBuffer[charBufferIndex++] = '\n';
            charsLeftToWrite -= LINE_LENGTH;
        }
        if (charBufferIndex != 0) {
            stringBuilder.append(new String(charBuffer));
        }
    }
}

final class FrequencyJava {
    public char character;
    public double chance;

    public FrequencyJava(char character, double chance) {
        this.character = character;
        this.chance = chance;
    }
}
