package com.company.view;

import com.company.gameobjects.City;

import java.util.ArrayList;


public class CityView extends Frame {

    City data;

    public CityView(City cityData) {
        data = cityData;
    }

    public CityView(City cityData, int width, int height) {
        super(width, height);
        data = cityData;
    }

    @Override
    public ArrayList<String> getView(){
        ArrayList<String> output=new ArrayList<>();
        output.add(Elements.getCustomBorder(Elements.BORDER_SINGLE,Elements.BORDER_SINGLE,new int[]{5,10},new int[]{
                Elements.BORDER_TOP,Elements.BORDER_TOP,Elements.BORDER_TOP
        }));
        output.add(Elements.FRAME_SINGLE_VERTICAL+"     "+Elements.FRAME_SINGLE_VERTICAL+"          "+Elements.FRAME_SINGLE_VERTICAL);
        output.add(Elements.getCustomBorder(Elements.BORDER_SINGLE,Elements.BORDER_SINGLE,new int[]{5,10},new int[]{
                Elements.BORDER_CROSS, Elements.BORDER_BOTTOM, Elements.BORDER_CROSS
        }));
        output.add(Elements.FRAME_SINGLE_VERTICAL+"                "+Elements.FRAME_SINGLE_VERTICAL);
        output.add(Elements.FRAME_SINGLE_VERTICAL+"                "+Elements.FRAME_SINGLE_VERTICAL);
        output.add(Elements.FRAME_SINGLE_VERTICAL+"                "+Elements.FRAME_SINGLE_VERTICAL);
        output.add(Elements.FRAME_SINGLE_VERTICAL+"                "+Elements.FRAME_SINGLE_VERTICAL);

        output.add(Elements.getBorder(Elements.BORDER_BOTTOM,Elements.BORDER_SINGLE,16,false,0,0));

        return output;

    }
}

