package com.company.datahandlers;

import com.company.view.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoGroup {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getValueNames() {
        return valueNames;
    }

    public void setValueNames(ArrayList<String> valueNames) {
        this.valueNames = valueNames;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public void setValue(String name, String value) {
        values.put(name, value);
        if (!valueNames.contains(name))
            valueNames.add(name);
    }

    public String getValue(String name) {
        return values.get(name);
    }

    public ArrayList<String> toStringArray() {
        ArrayList<String> output = new ArrayList<>();
        output.add(/*Elements.TEXT_FORMAT_BOLD_START +*/ title/* + Elements.TEXT_FORMAT_BOLD_END*/);
        for (String s : valueNames)
            output.add(s + ": " + /*Elements.TEXT_FORMAT_BOLD_START + */values.get(s)/* + Elements.TEXT_FORMAT_BOLD_END*/);
        return output;

    }

    public int maxSizeX() {
        int titleSize = getTitle().length();
        int maxValueSizeX = 0;
        for (int i = 0; i < getValueNames().size(); i++) {
            int currentElementLenght = (getValueNames().get(i) + ": " + getValue(getValueNames().get(i))).length();
            if (maxValueSizeX < currentElementLenght)
                maxValueSizeX = currentElementLenght;
        }
        return titleSize > maxValueSizeX ? titleSize : maxValueSizeX;
    }

    public int maxSizeY() {
        return title == null ? getValueNames().size() : getValueNames().size() + 1;
    }

    private String title;
    private ArrayList<String> valueNames = new ArrayList<>();
    private Map<String, String> values = new HashMap<String, String>();
}