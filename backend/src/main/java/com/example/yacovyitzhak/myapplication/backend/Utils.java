package com.example.yacovyitzhak.myapplication.backend;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public class Utils {

    public static int randInt(int min, int max) {

        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
