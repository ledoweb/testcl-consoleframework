package com.company.view;


import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

public class Elements {
    public static final String CARD_NUMBERS_ROW_START = "│";
    public static final String CARD_NUMBERS_ROW_END = "│";
    public static final String CARD_NUMBERS_SEPARATOR = "│";
    public static final String CARD_ROW_SEPARATOR = "├────┼────┼────┼────┼────┼────┼────┼────┼────┤";
    public static final String CARD_FIRST_ROW = "┌────┬────┬────┬────┬────┬────┬────┬────┬────┐";
    public static final String CARD_LAST_ROW = "└────┴────┴────┴────┴────┴────┴────┴────┴────┘";

    public static final String TEXT_FORMAT_BOLD_START = "\033[1m";
    public static final String TEXT_FORMAT_BOLD_END = "\033[0m";

    public static final int VIEWPORT_HORIZONTAL_SIZE = 50;
    public static final int VIEWPORT_VERTICAL_SIZE = 5;

    public static final int BORDER_TOP = 0;
    public static final int BORDER_BOTTOM = 1;
    public static final int BORDER_CROSS = 2;
    public static final int BORDER_SINGLE = 10;
    public static final int BORDER_DOUBLE = 11;

    public static final String FRAME_SINGLE_CORNER_LT = "┌";
    public static final String FRAME_SINGLE_CORNER_RT = "┐";
    public static final String FRAME_SINGLE_CORNER_LB = "└";
    public static final String FRAME_SINGLE_CORNER_RB = "┘";
    public static final String FRAME_SINGLE_VERTICAL = "│";
    public static final String FRAME_SINGLE_HORIZONTAL = "─";
    public static final String FRAME_SINGLE_T_TOP = "┬";
    public static final String FRAME_SINGLE_T_BOTTOM = "┴";
    public static final String FRAME_SINGLE_T_LEFT = "├";
    public static final String FRAME_SINGLE_T_RIGHT = "┤";
    public static final String FRAME_SINGLE_PLUS = "┼";

    public static final String FRAME_DOUBLE_CORNER_LT = "╔";
    public static final String FRAME_DOUBLE_CORNER_RT = "╗";
    public static final String FRAME_DOUBLE_CORNER_LB = "╚";
    public static final String FRAME_DOUBLE_CORNER_RB = "╝";
    public static final String FRAME_DOUBLE_VERTICAL = "║";
    public static final String FRAME_DOUBLE_HORIZONTAL = "═";
    public static final String FRAME_DOUBLE_T_TOP = "╦";
    public static final String FRAME_DOUBLE_T_BOTTOM = "╩";
    public static final String FRAME_DOUBLE_T_LEFT = "╠";
    public static final String FRAME_DOUBLE_T_RIGHT = "╣";
    public static final String FRAME_DOUBLE_PLUS = "╬";

    public static final String FRAME_HD_VS_CORNER_LT = "╒";
    public static final String FRAME_HD_VS_CORNER_RT = "╕";
    public static final String FRAME_HD_VS_CORNER_LB = "╘";
    public static final String FRAME_HD_VS_CORNER_RB = "╛";
    public static final String FRAME_HD_VS_VERTICAL = "│";
    public static final String FRAME_HD_VS_HORIZONTAL = "═";
    public static final String FRAME_HD_VS_T_TOP = "╤";
    public static final String FRAME_HD_VS_T_BOTTOM = "╧";
    public static final String FRAME_HD_VS_T_LEFT = "╞";
    public static final String FRAME_HD_VS_T_RIGHT = "╡";
    public static final String FRAME_HD_VS_PLUS = "╪";

    public static final int FRAME_TABLE_HORIZONTAL_PADDING = 1;

    public static String getBorder(int which, int weight, int characterLenght, @Nullable boolean divideElements, @Nullable int elementLenght, @Nullable int elementPadding) {

        switch (weight) {
            default:
            case BORDER_SINGLE:

                switch (which) {
                    default:
                    case BORDER_TOP:
                        if (!divideElements) {
                            String output = FRAME_SINGLE_CORNER_LT;
                            for (int i = 0; i < characterLenght; i++) {
                                output += FRAME_SINGLE_HORIZONTAL;
                            }
                            output += FRAME_SINGLE_CORNER_RT;
                            return output;
                        } else {
                            String output = Elements.FRAME_SINGLE_CORNER_LT;
                            int singleColumnTotalSize = elementLenght + (2 * elementPadding);
                            int singleElementDrawn = 0;
                            for (int i = 0; i < singleColumnTotalSize * characterLenght; i++) {
                                output += Elements.FRAME_SINGLE_HORIZONTAL;
                                singleElementDrawn++;
                                if (singleElementDrawn == singleColumnTotalSize && i != singleColumnTotalSize * characterLenght - 1) {
                                    singleElementDrawn = 0;
                                    output += Elements.FRAME_SINGLE_T_TOP;
                                }
                            }
                            output += Elements.FRAME_SINGLE_CORNER_RT;
                            return output;
                        }

                    case BORDER_BOTTOM:
                        if (!divideElements) {
                            String output = FRAME_SINGLE_CORNER_LB;
                            for (int i = 0; i < characterLenght; i++) {
                                output += FRAME_SINGLE_HORIZONTAL;
                            }
                            output += FRAME_SINGLE_CORNER_RB;
                            return output;
                        } else {
                            String output = Elements.FRAME_SINGLE_CORNER_LB;
                            int singleColumnTotalSize = elementLenght + 2 * elementPadding;
                            int singleElementDrawn = 0;
                            for (int i = 0; i < singleColumnTotalSize * characterLenght; i++) {
                                output += Elements.FRAME_SINGLE_HORIZONTAL;
                                singleElementDrawn++;
                                if (singleElementDrawn == singleColumnTotalSize && i != singleColumnTotalSize * characterLenght - 1) {
                                    singleElementDrawn = 0;
                                    output += Elements.FRAME_SINGLE_T_BOTTOM;
                                }
                            }
                            output += Elements.FRAME_SINGLE_CORNER_RB;
                            return output;

                        }

                }
            case BORDER_DOUBLE:

                switch (which) {
                    default:
                    case BORDER_TOP:
                        String output = FRAME_DOUBLE_CORNER_LT;
                        for (int i = 0; i < characterLenght; i++)
                            output += FRAME_DOUBLE_HORIZONTAL;
                        output += FRAME_DOUBLE_CORNER_RT;
                        return output;

                    case BORDER_BOTTOM:
                        output = FRAME_DOUBLE_CORNER_LB;
                        for (int i = 0; i < characterLenght; i++)
                            output += FRAME_DOUBLE_HORIZONTAL;
                        output += FRAME_DOUBLE_CORNER_RB;
                        return output;

                }


        }

    }

    public static String getCustomBorder(int horWeight, int vertWeight, int[] elementsLenght, int[] dividerSetup) {
        String startChar = "";
        String endChar = "";
        String horChar = "";
        String crossDivider = "";
        String topDivider = "";
        String bottomDivider = "";

        switch (horWeight) {
            case BORDER_SINGLE:
                if (vertWeight == BORDER_SINGLE) {
                    crossDivider = FRAME_SINGLE_PLUS;
                    horChar = FRAME_SINGLE_HORIZONTAL;
                    topDivider = FRAME_SINGLE_T_TOP;
                    bottomDivider = FRAME_SINGLE_T_BOTTOM;
                    switch (dividerSetup[0]) {
                        case BORDER_TOP:
                            startChar = FRAME_SINGLE_CORNER_LT;
                            break;
                        case BORDER_BOTTOM:
                            startChar = FRAME_SINGLE_CORNER_LB;
                            break;
                        case BORDER_CROSS:
                            startChar = FRAME_SINGLE_T_LEFT;
                            break;
                    }
                    switch (dividerSetup[dividerSetup.length - 1]) {
                        case BORDER_TOP:
                            endChar = FRAME_SINGLE_CORNER_RT;
                            break;
                        case BORDER_BOTTOM:
                            endChar = FRAME_SINGLE_CORNER_RB;
                            break;
                        case BORDER_CROSS:
                            endChar = FRAME_SINGLE_T_RIGHT;
                            break;
                    }
                } else {

                }
                break;
            case BORDER_DOUBLE:
                if (vertWeight == BORDER_SINGLE) {
                    crossDivider = FRAME_HD_VS_PLUS;
                    horChar = FRAME_HD_VS_HORIZONTAL;
                    topDivider = FRAME_HD_VS_T_TOP;
                    bottomDivider = FRAME_HD_VS_T_BOTTOM;
                    switch (dividerSetup[0]) {
                        case BORDER_TOP:
                            startChar = FRAME_HD_VS_CORNER_LT;
                            break;
                        case BORDER_BOTTOM:
                            startChar = FRAME_HD_VS_CORNER_LB;
                            break;
                        case BORDER_CROSS:
                            startChar = FRAME_HD_VS_T_LEFT;
                            break;
                    }
                    switch (dividerSetup[dividerSetup.length - 1]) {
                        case BORDER_TOP:
                            endChar = FRAME_HD_VS_CORNER_RT;
                            break;
                        case BORDER_BOTTOM:
                            endChar = FRAME_HD_VS_CORNER_RB;
                            break;
                        case BORDER_CROSS:
                            endChar = FRAME_HD_VS_T_RIGHT;
                            break;
                    }
                } else {

                }
                break;
        }


        String finalOutput = startChar;
        for (int i = 0; i < elementsLenght.length; i++) {
            for (int j = 0; j < elementsLenght[i]; j++)
                finalOutput += horChar;
            if (i != elementsLenght.length - 1)
                switch (dividerSetup[i + 1]) {
                    case BORDER_TOP:
                        finalOutput += topDivider;
                        break;
                    case BORDER_BOTTOM:
                        finalOutput += bottomDivider;
                        break;
                    case BORDER_CROSS:
                        finalOutput += crossDivider;
                        break;
                }

        }
        finalOutput+=endChar;

        return finalOutput;


    }

    public static String getInsideBorder(int weight, int characterLenght, int padding, @Nullable boolean divideElements, @Nullable int elementLenght) {
        switch (weight) {
            default:
            case BORDER_SINGLE:
                String output = Elements.FRAME_SINGLE_T_LEFT;
                int singleColumnTotalSize = elementLenght + (2 * padding);
                int singleElementDrawn = 0;
                for (int i = 0; i < singleColumnTotalSize * characterLenght; i++) {
                    output += Elements.FRAME_SINGLE_HORIZONTAL;
                    singleElementDrawn++;
                    if (singleElementDrawn == singleColumnTotalSize && i != singleColumnTotalSize * characterLenght - 1) {
                        singleElementDrawn = 0;
                        output += Elements.FRAME_SINGLE_PLUS;
                    }
                }
                output += Elements.FRAME_SINGLE_T_RIGHT;
                return output;

        }
    }

    public static String getEmpty(int numOfChars) {
        String output = "";
        for (int i = 0; i < numOfChars; i++)
            output += " ";

        return output;
    }
}
