package com.company.view;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

public class Screen {

    Viewport viewport;
    Sidebar sidebar;
    Footer footer;

    public void refreshScreen(){
        refreshScreen(viewport,sidebar,null);
    }

    public void refreshScreen(Viewport viewport, Sidebar sidebar, @Nullable Footer footer) {
        clearScreen();
        if (viewport != null)
            this.viewport = viewport;
        if (sidebar != null)
            this.sidebar = sidebar;
        if (footer != null)
            this.footer = footer;

        ArrayList<String> outputToDraw = new ArrayList<>();

        if (viewport.getSizeY() > sidebar.getSizeY(false,true)) {
            int linesDrawn = 0;
            for (int i = 0; i < sidebar.getView().size(); i++) {
                outputToDraw.add(viewport.getView(true).get(i) + sidebar.getView().get(i));
                linesDrawn++;
            }
            for (int i = linesDrawn; i < viewport.getView(true).size(); i++)
                outputToDraw.add(viewport.getView(true).get(i) + sidebar.getEmpty(true));
        } else {
            int linesDrawn = 0;
            for (int i = 0; i < viewport.getView(true).size(); i++) {
                outputToDraw.add(viewport.getView(true).get(i) + sidebar.getView().get(i));
                linesDrawn++;
            }
            for (int i = linesDrawn; i < sidebar.getView().size(); i++)
                outputToDraw.add(viewport.getEmpty(true,false)+ sidebar.getView().get(i));
        }

        draw(outputToDraw);


    }

    public static void clearScreen() {
        System.out.flush();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void draw(ArrayList<String> output) {
        for (int i = 0; i < output.size(); i++)
            System.out.println(output.get(i));
    }
}
