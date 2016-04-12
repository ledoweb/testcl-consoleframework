package com.company.view;


import com.company.datahandlers.InfoGroup;

import javax.sound.midi.MidiDevice;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Frame {
    private static final int TABLE = 00;
    private static final int INT_TABLE = 01;
    private static final int LIST = 10;
    private static final int ARRAY = 20;
    private static final int INFOGROUP = 30;
    private static final int VIEWPORT = 40;

    public boolean isFixedSize() {
        return isFixedSize;
    }

    private boolean isFixedSize = false;
    private int width;
    private int height;

    private int dataType;
    private String[][] table;
    private int[][] intTable;
    private String[] array;
    private InfoGroup infoGroup;
    private ArrayList<String> list = new ArrayList<>();
    private Viewport viewport;

    public Frame() {
    }

    public Frame(Object data, int width, int height) {
        this(data);
        isFixedSize = true;
        this.width = width;
        this.height = height;

    }

    public Frame(int width, int height) {
        isFixedSize = true;
        this.width = width;
        this.height = height;

    }

    public Frame(Object data) {
        if (data instanceof String[][]) {
            dataType = TABLE;
            table = (String[][]) data;
        } else if (data instanceof String[]) {
            dataType = ARRAY;
            array = (String[]) data;
        } else if (data instanceof ArrayList) {
            dataType = LIST;
            list = (ArrayList<String>) data;
        } else if (data instanceof int[][]) {
            dataType = INT_TABLE;
            intTable = (int[][]) data;
        } else if (data instanceof InfoGroup) {
            dataType = INFOGROUP;
            infoGroup = (InfoGroup) data;
        } else if (data instanceof Viewport) {
            dataType = VIEWPORT;
            viewport = (Viewport) data;
        }

    }

    public int getSizeX(boolean includingOutsideFrame) {
        if (!isFixedSize) {
            switch (dataType) {
                default:
                case INT_TABLE:
                    if (includingOutsideFrame)
                        return ((getIntTableMaxIntLenght() + (2 * Elements.FRAME_TABLE_HORIZONTAL_PADDING) + 1) * intTable[0].length) + 1;
                    else
                        return ((getIntTableMaxIntLenght() + (2 * Elements.FRAME_TABLE_HORIZONTAL_PADDING) + 1) * intTable[0].length) - 1;
                case INFOGROUP:
                    int measure = infoGroup.maxSizeX();
                    return includingOutsideFrame ? measure + 2 : measure;
                case VIEWPORT:
                    measure = viewport.getSizeX();
                    return includingOutsideFrame ? measure + 2 : measure;
            }
        } else return includingOutsideFrame ? width + 2 : width;
    }

    public int getSizeY(boolean includingOutsideFrame) {
        if (!isFixedSize) {
            switch (dataType) {
                default:
                case INT_TABLE:
                    if (includingOutsideFrame)
                        return (intTable.length * 2) + 1;
                    else
                        return (intTable.length * 2) - 1;
                case INFOGROUP:
                    return includingOutsideFrame ? infoGroup.maxSizeY() + 2 : infoGroup.maxSizeY();
                case VIEWPORT:
                    return includingOutsideFrame ? viewport.getSizeY() + 2 : viewport.getSizeY();
            }
        } else return includingOutsideFrame ? height + 2 : height;
    }

    public ArrayList<String> getView() {
        switch (dataType) {
            default:
            case INT_TABLE:
                return drawIntTable(intTable);
            case INFOGROUP:
                return drawInfoGroup(infoGroup);
            case VIEWPORT:
                ArrayList<String> output = new ArrayList<>();
                output.add(Elements.getBorder(Elements.BORDER_TOP, Elements.BORDER_SINGLE, viewport.getSizeX(), false, 0, 0));
                output.addAll(viewport.getView(false).stream().map(s -> Elements.FRAME_SINGLE_VERTICAL + s + Elements.FRAME_SINGLE_VERTICAL).collect(Collectors.toList()));
                output.add(Elements.getBorder(Elements.BORDER_BOTTOM, Elements.BORDER_SINGLE, viewport.getSizeX(), false, 0, 0));
                return output;

        }
    }


    private int getIntTableMaxIntLenght() {
        int intLenght = 0;
        for (int[] i : intTable)
            for (int j = 0; j < i.length; j++)
                if (intLenght < i[j] / 10)
                    intLenght = i[j] / 10;
        return intLenght;
    }

    private ArrayList<String> drawIntTable(int[][] table) {
        ArrayList<String> output = new ArrayList<>();
        output.add(Elements.getBorder(Elements.BORDER_TOP, Elements.BORDER_SINGLE, intTable[0].length, true, getIntTableMaxIntLenght(), Elements.FRAME_TABLE_HORIZONTAL_PADDING));
        for (int i = 0; i < table.length; i++) {
            String line = "";
            line += Elements.FRAME_SINGLE_VERTICAL;
            for (int j = 0; j < table[i].length; j++) {
                int value = table[i][j];
                int valueLenght = String.valueOf(value).length();
                int extraPadding = getIntTableMaxIntLenght() - valueLenght;
                for (int k = 0; k < Elements.FRAME_TABLE_HORIZONTAL_PADDING + extraPadding; k++)
                    line += " ";
                line += String.valueOf(value);
                for (int k = 0; k < Elements.FRAME_TABLE_HORIZONTAL_PADDING; k++)
                    line += " ";
                line += Elements.FRAME_SINGLE_VERTICAL;

            }
            output.add(line);
            if (i != table.length - 1)
                output.add(Elements.getInsideBorder(Elements.BORDER_SINGLE, intTable[0].length, Elements.FRAME_TABLE_HORIZONTAL_PADDING, true, getIntTableMaxIntLenght()));

        }
        output.add(Elements.getBorder(Elements.BORDER_BOTTOM, Elements.BORDER_SINGLE, intTable[0].length, true, getIntTableMaxIntLenght(), Elements.FRAME_TABLE_HORIZONTAL_PADDING));

        return output;
    }


    private ArrayList<String> drawInfoGroup(InfoGroup group) {
        ArrayList<String> output = new ArrayList<>();
        int infoGroupHorCharCount = group.maxSizeX();
        output.add(Elements.getBorder(Elements.BORDER_TOP, Elements.BORDER_SINGLE, getSizeX(false), false, 0, 0));

        for (String s : group.toStringArray()) {
            String padding = "";
            if (s.length() < infoGroupHorCharCount) {

                for (int i = 0; i < infoGroupHorCharCount - s.length(); i++)
                    padding += " ";
            }
            output.add(Elements.FRAME_SINGLE_VERTICAL + s + padding + Elements.FRAME_SINGLE_VERTICAL);
        }

        output.add(Elements.getBorder(Elements.BORDER_BOTTOM, Elements.BORDER_SINGLE, getSizeX(false), false, 0, 0));

        return output;
    }

}
