package org.algorithm;

import processing.core.PApplet;

import java.util.*;

public class UI {

    HashMap<String, Button> button_map = new HashMap<>();;
    ArrayList<String> bottom_ui = new ArrayList<>();
    ArrayList<String> top_ui = new ArrayList<>();
    PApplet sketch;

    UI(PApplet _sketch){
        sketch = _sketch;

    }

    public void render(){
        for(String s: button_map.keySet()){
            button_map.get(s).render();
        }
    }

    public void add_Button(String _name, Button _button, boolean _bottom){
        button_map.put(_name,_button);
        _button.name = _name;
        _button.bottom = _bottom;
        if (_bottom) {
            bottom_ui.add(_name);
        }
        else {
            top_ui.add(_name);
        }

    }

    public <T extends Button> void add_Button(String _name, float _x, float _y, float _width, float _height, String _text, Class<T> buttonClass, boolean _bottom) {
        Button tmp_button;
        try {
            tmp_button = buttonClass.getConstructor(PApplet.class, float.class, float.class, float.class, float.class, String.class)
                    .newInstance(sketch, _x, _y, _width, _height, _text);
            tmp_button.name = _name;
            tmp_button.bottom = _bottom;
            button_map.put(_name,tmp_button);
            if (_bottom) {
                bottom_ui.add(_name);
            }
            else {
                top_ui.add(_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  The function to make sure no buttons can be clicked simultaneously
     * */
    public void turn_Off_All_Buttons(Button _button){
        for (String s : bottom_ui) {
            if (button_map.get(s) != _button) {
                button_map.get(s).clicked = false;
            }
        }


    }
}
