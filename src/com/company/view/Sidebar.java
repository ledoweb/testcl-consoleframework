package com.company.view;

import com.company.datahandlers.InfoGroup;

import java.util.ArrayList;


public class Sidebar {
    private InfoGroup[] elements;
    private int sizeX;
    private int sizeY;

    public Sidebar(InfoGroup[] infosToShow) {
        this.elements = infosToShow;
        sizeX = autosizeX(true);
        sizeY = autosizeY(true);

    }

    public void editInfoGroup(int index, InfoGroup newGroup) {
        elements[index] = newGroup;
    }


    public String getEmpty(boolean includingFrame) {
        String output = "";
        for (int i = 0; i < getSizeX(includingFrame,true); i++)
            output += " ";

        return output;
    }

    public ArrayList<String> getView() {
        ArrayList<String> output = new ArrayList<>();

        output.add(Elements.getBorder(Elements.BORDER_TOP, Elements.BORDER_DOUBLE, getSizeX(false,true), false, 0, 0));
        int infoGroupHorCharCount = autosizeX(false);

        for (InfoGroup g : elements) {
            output.add(Elements.FRAME_DOUBLE_VERTICAL + Elements.getBorder(Elements.BORDER_TOP, Elements.BORDER_SINGLE, getSizeX(false,false), false, 0, 0) + Elements.FRAME_DOUBLE_VERTICAL);
            for (String s : g.toStringArray()) {
                String padding = "";
                if (s.length() < infoGroupHorCharCount) {

                    for (int i = 0; i < infoGroupHorCharCount - s.length(); i++)
                        padding += " ";
                }
                output.add(Elements.FRAME_DOUBLE_VERTICAL + Elements.FRAME_SINGLE_VERTICAL + s + padding + Elements.FRAME_SINGLE_VERTICAL + Elements.FRAME_DOUBLE_VERTICAL);
            }
            output.add(Elements.FRAME_DOUBLE_VERTICAL + Elements.getBorder(Elements.BORDER_BOTTOM, Elements.BORDER_SINGLE, getSizeX(false,false), false, 0, 0) + Elements.FRAME_DOUBLE_VERTICAL);
        }

        output.add(Elements.getBorder(Elements.BORDER_BOTTOM, Elements.BORDER_DOUBLE, getSizeX(false,true), false, 0, 0));
        return output;
    }

    public int getSizeX(boolean includingFrame, boolean includingChildFrames) {
        if (!includingFrame)
            return autosizeX(includingChildFrames);
        else
            return autosizeX(includingChildFrames) + 2;
    }

    public int getSizeY(boolean includingFrame,boolean includingChildFrames) {
        if (!includingFrame)
            return autosizeY(includingChildFrames);
        else
            return autosizeY(includingChildFrames) + 2;
    }


    private int autosizeX(boolean includingFrames) {
        int autosize = 0;
        for (InfoGroup g : elements) {
            if (autosize < maxSizeX(g, includingFrames))
                autosize = maxSizeX(g, includingFrames);
        }
        return autosize;
    }

    private int maxSizeX(InfoGroup group, boolean includingFrame) {
        if (includingFrame)
            return group.maxSizeX()+ 2;
        else
            return group.maxSizeX();
    }

    private int autosizeY(boolean includingFrames) {
        int autoSize = 0;
        for (InfoGroup g : elements)
            autoSize += maxSizeY(g, includingFrames);
        return autoSize;
    }

    private int maxSizeY(InfoGroup group, boolean includingFrame) {
        if (includingFrame)
            return group.getValueNames().size() + 3;
        else
            return group.getValueNames().size() + 1;
    }
}

