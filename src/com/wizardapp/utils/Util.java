package com.wizardapp.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Util {

	static Random rnd = new Random();
	static Collection<Integer> alreadyChosen = new HashSet<Integer>();

    public static int getNextUniqueRandom(int maxSize){
        if (alreadyChosen.size()==maxSize){ //hardcoded 5 figure numbers, consider making a variable
             throw new RuntimeException("All values used");
        }

        boolean unique=false;
        int value=0;
        while(unique==false){
            value=rnd.nextInt(maxSize)+10000;
            unique=!alreadyChosen.contains(value);
        }
        alreadyChosen.add(value);
        return value;
    }
}
