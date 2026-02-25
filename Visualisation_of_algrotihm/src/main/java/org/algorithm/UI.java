package org.algorithm;

import processing.core.PApplet;

import java.util.*;

import static org.algorithm.Main.*;

public class UI {

    private HashMap<String, Button> button_map = new HashMap<>();;
    ArrayList<String> bottom_ui = new ArrayList<>();
    ArrayList<String> top_ui = new ArrayList<>();
    PApplet sketch;

    /**
     * UI class is used to make all UI elements in the program.
     * @param _sketch The _sketch is the sketch from main
     */
    UI(PApplet _sketch){
        sketch = _sketch;

    }

    /**
     * render, calls render on all UI elements
     * */
    public void render(){
        for(String s: button_map.keySet()){
            button_map.get(s).render();
        }
        render_Edge_Weight_UI();
        display_PQ();
    }

    /**
     * add_Button takes a name, button and booolean describing if the button is on the top or bottom
     * @param _name the name of the  button
     * @param _button the button itself
     * @param _bottom if true, then apart bottom UI, else apart of top UI
     * */
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

    /**
     * add_Button takes a name, button and booolean describing if the button is on the top or bottom
     * @param _name the name of the  button
     * @param _x the x pos of the button
     * @param _y the y pos of the button
     * @param _width the width of the button
     * @param _height the height of the button
     * @param _text the text the button displays
     * @param buttonClass the class of the button
     * @param _bottom if true, then apart bottom UI, else apart of top UI
     * */
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
     * returns the button with the given name from the map
     * @param _name name of the button we fetch
     * @return Button
     * */
    public Button get_Button(String _name){
        return button_map.get(_name);
    }

    /**
     * @return Map<String, Button>
     * */
    public Map<String, Button> get_Map(){
        return button_map;
    }

    /**
     *  The function to make sure no buttons can be clicked simultaneously
     * */
    public void turn_Off_All_Buttons(Button _button){
        if (!bottom_ui.contains(_button.name)) return;
        for (String s : bottom_ui) {
            if (button_map.get(s) != _button) {
                if (s.equals("pause")) continue;
                button_map.get(s).clicked = false;
            }
        }
    }




    private void render_Edge_Weight_UI() {

        //make this not ugly
        sketch.push();
        sketch.textSize(30);
        StringBuilder inputStr = new StringBuilder();
        if (display_edge_weight_ui) {

            sketch.push();
            sketch.noStroke();
            sketch.fill(255);
            sketch.rectMode(CENTER);
            String tmp = ""+1000000;
            sketch.push();
            sketch.textSize(16);
            float fontSize = sketch.getGraphics().textSize;
            sketch.pop();
            float boxheight = (sketch.getGraphics().textSize) *1.7f;
            sketch.rect(
                    (sketch.displayWidth/2f),
                    (sketch.displayHeight/2f) - (sketch.getGraphics().textSize/2) - fontSize,
                    sketch.textWidth(tmp) *2,
                    boxheight + (fontSize*2)

            );
            sketch.push();
            sketch.fill(100);
            sketch.stroke(100);
            sketch.rect(
                    (sketch.displayWidth/2f),
                    (sketch.displayHeight/2f) - (sketch.getGraphics().textSize/2),
                    sketch.textWidth(tmp) * 1.5f ,
                    (sketch.getGraphics().textSize)

            );
            sketch.pop();

            sketch.pop();

            // Draw input
            sketch.fill(0);


            for (char c : currentInput) {
                inputStr.append(c);
            }
            sketch.push();
            sketch.textSize(16);
            sketch.text("Input Weight:",
                    sketch.displayWidth/2f - sketch.textWidth(tmp) - sketch.textWidth("Input Weight:")/2,
                    sketch.displayHeight/2f - boxheight);
            sketch.pop();


            sketch.textAlign(LEFT);
            sketch.text(inputStr.toString(),
                    sketch.displayWidth/2f-sketch.textWidth(tmp)/2, sketch.displayHeight/2f);
        }
        sketch.pop();

    }


    private void display_PQ(){

        if(Ui.get_Button("PQ_display").clicked){
            sketch.rect(sketch.displayWidth-400,0,400,750);
            if (algorithm != null && algorithm.get_U() != null){
                sketch.push();
                sketch.fill(0,0,0);
                sketch.textSize(100);
                for (int i = 0; i < algorithm.get_U().get_Heap().size(); i++){
                    String tmp = algorithm.get_U().get_Heap().get(i).toString();

                    sketch.text(tmp, sketch.displayWidth-200, 200+100*i);
                    println("traversed this element " + tmp);

                }
                sketch.pop();
                }
          }
    }
}
