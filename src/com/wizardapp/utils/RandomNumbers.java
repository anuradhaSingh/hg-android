package com.wizardapp.utils;

import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomNumbers {

    private final Random random = new Random();
    private final BitSet used = new BitSet();
    private final int min = 0;
    private  static int max = 1000; // This is required for numbers available
    private  int numbersAvailable = max - min ;
    
    public static int generateRandomNumber(int max,RandomNumbers randomNumbers) {
    	RandomNumbers.max = max;
    	return randomNumbers.nextRandom();
     }

    private int nextRandom () throws NoSuchElementException {
        while (numbersAvailable > 0) {
            int rnd = min + random.nextInt(max - min);
            if (!used.get(rnd)) {
                used.set(rnd);
                numbersAvailable--;
                return rnd;
            }
        }
        throw new NoSuchElementException();
    }
}