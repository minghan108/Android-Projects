package com.Ming.MathApp;

/**
 * Created by MingHan on 9/2/2015.
 */
public class RandomNum {

  public int random(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
