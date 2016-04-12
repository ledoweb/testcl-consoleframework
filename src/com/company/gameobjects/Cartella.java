package com.company.gameobjects;


import com.company.utils.NumberUtils;
import com.company.view.Elements;
import com.company.view.Screen;

import java.util.Random;
import java.util.Scanner;

public class Cartella {
    public static final int CARD_ROWS = 3;
    public static final int CARD_COLUMNS = 3;
    private int[][] numeri = new int[CARD_ROWS][CARD_COLUMNS];

    public int[][] getRandomNumbersTable(){
        setRandomNumbers();
        return numeri;
    }

    public int getNumber(int row, int column) {
        return numeri[row][column];
    }

    public void setNumber(int row, int column, int value) {
        numeri[row][column] = value;
    }

    public void setNumbers() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numeri.length; i++) {
            for (int j = 0; j < numeri[i].length; j++) {
                numeri[i][j] = scanner.nextInt();
            }
        }
    }

    public void setRandomNumbers() {
        for (int i = 0; i < CARD_COLUMNS; i++) {
            int[] randomColumn = randomColumn(i);
            for (int j = 0; j < CARD_ROWS; j++) {
                numeri[j][i] = randomColumn[j];
            }
        }
    }


    public void print() {
        System.out.println(Elements.CARD_FIRST_ROW);
        for (int i = 0; i < numeri.length; i++) {
            printRow(i);
            if (i != numeri.length - 1)
                System.out.println(Elements.CARD_ROW_SEPARATOR);

        }
        System.out.println(Elements.CARD_LAST_ROW);

    }

    private void printRow(int row) {

        System.out.print(Elements.CARD_NUMBERS_ROW_START);
        for (int i = 0; i < numeri[row].length; i++) {
            if (numeri[row][i] == 0) {
                System.out.print("    ");
                System.out.print(Elements.CARD_NUMBERS_SEPARATOR);
                continue;
            }
            if (numeri[row][i] / 10 >= 1)
                System.out.print(" " + numeri[row][i] + " ");
            else
                System.out.print("  " + numeri[row][i] + " ");

            System.out.print(Elements.CARD_NUMBERS_SEPARATOR);
        }
        System.out.print("\n");


    }

    private int[] randomColumn(int index) {
        Random generator = new Random();

        int[] tempValues = new int[CARD_ROWS];
        int[] columnValues = new int[CARD_ROWS];

        int howManyNumbers = generator.nextInt(CARD_ROWS) + 1;

        for (int i = 0; i < howManyNumbers; i++) {
            tempValues[i] = index * 10 + generator.nextInt(9) + 1;

            int position = generator.nextInt(CARD_ROWS);
            while (columnValues[position] != 0)
                position = generator.nextInt(CARD_ROWS);
            columnValues[position] = tempValues[i];
        }

        NumberUtils.sortIntArray(columnValues);
        return columnValues;
    }


}
