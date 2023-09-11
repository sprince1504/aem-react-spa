package com.adobe.aem.guides.aemspa.react.core.servlets;

public class ArrayTest {
    public static void main(String[] args) {
        int arr[] = {3,1,4,6,5};

        for (int i=0;i<=arr.length;i++) {
            int initial = arr[0] * arr [0];
            int middle = arr[2] * arr [2];
            int end = arr[4] * arr [4];
            int total = initial + middle;

            if (total == end) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }
}
