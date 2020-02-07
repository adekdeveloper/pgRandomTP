package net.pietreg.randomtp.utils;

import java.util.Random;

public final class RandomUtils {

    public static final Random random = new Random();

    private RandomUtils(){
    }

    public static Double getRandomDouble(final double min, final double max){
        return random.nextDouble() * (max - min) + min;
    }
}
