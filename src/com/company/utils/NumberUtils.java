package com.company.utils;


public class NumberUtils {



    public static void sortIntArray(int[] arrayToSort){
        for(int i=0;i<arrayToSort.length;i++) {
            if(arrayToSort[i]==0)
                continue;
            for (int j = i; j < arrayToSort.length; j++){
                if(arrayToSort[j]==0)
                    continue;
                if(arrayToSort[j]<arrayToSort[i]){
                    int temp=arrayToSort[i];
                    arrayToSort[i]=arrayToSort[j];
                    arrayToSort[j]=temp;
                }

            }
        }

    }
}
