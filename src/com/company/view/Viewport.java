package com.company.view;


import java.util.ArrayList;

public class Viewport {

    private int sizeX;
    private int sizeY;
    private boolean isFixedSize;
    private ArrayList<ArrayList<Frame>> content = new ArrayList<>();

    public Viewport(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        isFixedSize = true;

    }

    public Viewport() {
        isFixedSize = false;
    }

    public void addFrame(Frame frameToAdd, int column) {
        /*ArrayList<Frame> currentColumn;
        if (content.size() >= (column + 1)) {
            currentColumn = content.get(column);
            content.remove(column);
        } else {
            currentColumn = new ArrayList<>();
        }
        if (currentColumn.size() < row) {
            for (int i = 0; i < row; i++){
                if(currentColumn.)
            }
        }
        currentColumn.add(row, frameToAdd);

        content.add(column, currentColumn);
        */
        if (column + 1 <= content.size()) {
            content.get(column).add(frameToAdd);
        } else {
            int currentSize = content.size();
            for (int i = 0; i < (column + 1) - currentSize; i++)
                content.add(currentSize + i, new ArrayList<>());
            content.get(column).add(frameToAdd);
        }
    }

    public String getEmpty(boolean includingFrame, boolean frameVisible) {
        String output = "";
        if (includingFrame) {
            for (int i = 0; i < getSizeX(); i++)
                output += " ";
            if (frameVisible)
                output = Elements.FRAME_DOUBLE_VERTICAL + output + Elements.FRAME_DOUBLE_VERTICAL;
            else
                output += "  ";
        } else {
            for (int i = 0; i < getSizeX(); i++)
                output += " ";
        }

        return output;

    }

    public ArrayList<String> getView(boolean includingFrame) {
        ArrayList<String> output = new ArrayList<>();

        if (includingFrame)
            output.add(Elements.getBorder(Elements.BORDER_TOP, Elements.BORDER_DOUBLE, getSizeX(), false, 0, 0));
        for (int i = 0; i < getSizeY(); i++) {
            String temp = "";
            for (ArrayList<Frame> list : content) {
                String tempRow = getRowNumber(list, i);
                int delta = contentColumnWidth(list) - tempRow.length();
                if (delta > 0)
                    for (int j = 0; j < delta; j++)
                        tempRow += " ";
                temp += tempRow;
            }
            if (includingFrame)
                temp = Elements.FRAME_DOUBLE_VERTICAL + temp + Elements.FRAME_DOUBLE_VERTICAL;
            output.add(temp);
        }
        /*for (int i = 0; i < sizeY; i++) {
            String spacer = "";
            for (int j = 0; j < sizeX - 2; j++)
                spacer += " ";
            output.add(Elements.FRAME_DOUBLE_VERTICAL + spacer + Elements.FRAME_DOUBLE_VERTICAL);
        }*/

        if (includingFrame)
            output.add(Elements.getBorder(Elements.BORDER_BOTTOM, Elements.BORDER_DOUBLE, getSizeX(), false, 0, 0));

        return output;
    }

    public int getSizeX() {


        if (isFixedSize)
            return sizeX;
        else {
            int measure = 0;
            for (ArrayList<Frame> list : content)
                measure += maxSizeX(list);
            return measure;
        }
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
        isFixedSize = true;
    }

    public int getSizeY() {


        if (isFixedSize)
            return sizeY;
        else
            return maxSizeY();
    }

    private String getRowNumber(ArrayList<Frame> column, int number) {
        if (number + 1 > contentColumnHeight(column))
            return Elements.getEmpty(maxSizeX(column));
        else {
            int cursor = 0;
            int remaining = number + 1;
            while (remaining > column.get(cursor).getSizeY(true)) {
                remaining -= column.get(cursor).getSizeY(true);
                cursor++;
            }
            return column.get(cursor).getView().get(remaining - 1);
        }

    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
        isFixedSize = true;
    }

    private int maxSizeY() {
        int maxHeight = 0;
        for (ArrayList<Frame> list : content) {
            if (maxHeight < contentColumnHeight(list))
                maxHeight = contentColumnHeight(list);
        }

        return maxHeight;
    }

    private int contentColumnHeight(ArrayList<Frame> column) {
        int measure = 0;
        for (Frame f : column) {
            measure += f.getSizeY(true);
        }
        return measure;
    }

    private int contentColumnWidth(ArrayList<Frame> column) {
        int measure = 0;
        for (Frame f : column) {
            if (measure < f.getSizeX(true))
                measure = f.getSizeX(true);
        }
        return measure;
    }


    private int maxSizeX(ArrayList<Frame> input) {
        int measure = 0;
        for (Frame f : input) {
            if (measure < f.getSizeX(true))
                measure = f.getSizeX(true);
        }

        return measure;
    }

}
