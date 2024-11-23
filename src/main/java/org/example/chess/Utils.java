package org.example.chess;

public class Utils {
    public static void reverseArray(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }
}