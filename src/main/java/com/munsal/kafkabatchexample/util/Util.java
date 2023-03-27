package com.munsal.kafkabatchexample.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Util {

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static String getGeneratedString() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
