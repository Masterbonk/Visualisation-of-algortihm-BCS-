package org.algorithm;

import processing.core.PApplet;

import static org.algorithm.Main.Ui;


public class Util {
    PApplet sketch;
    int button_height;
    Util(PApplet _sketch, int _button_height){
        sketch = _sketch;
        button_height = _button_height;
    }

    /** Calculates the heuristic between Node a & b
     *  Heuristic = distance between the two points
     * */
    public static float heuristic(Node a, Node b){
        return (float) (Math.round(Math.sqrt((Math.pow(a.x - b.x,2)) + (Math.pow(a.y - b.y,2)))* 100.0) / 100.0);
    }


    /**
     * Makes the Ui elements, ie. all buttons All are added to the button arrays so they are rendered.
     */
    public static void Make_UI(PApplet _sketch, int _button_height){

        //bottom ui
        Ui.add_Button("reset", 0, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"↺", Reset_Button.class, true);

        Ui.add_Button("pause", (_sketch.displayWidth)/9f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⏯", Pause_Button.class, true);

        Ui.add_Button("forward", _sketch.displayWidth/9f*2f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⏵", Forward_Button.class, true);

        Ui.add_Button("cut", _sketch.displayWidth/9f*3f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"✂", Cut_Button.class, true);

        Ui.add_Button("circle", _sketch.displayWidth/9f*4f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⏺", Circle_Button.class, true);

        Ui.add_Button("line", _sketch.displayWidth/9f*5f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"\\", Line_Button.class, true);

        Ui.add_Button("flag_a", _sketch.displayWidth/9f*6f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⚑", Flag_A_Button.class, true);

        Ui.add_Button("flag_b", _sketch.displayWidth/9f*7f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⚐", Flag_B_Button.class, true);

        Ui.add_Button("weight", _sketch.displayWidth/9f*8f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"Weight", Weight_Button.class, true);

        //top ui
        Ui.add_Button("file", 0, 0, _sketch.displayWidth/9f, _button_height,"File", File_Button.class, false);

        Ui.add_Button("export", 0, _button_height+_button_height/10f, _sketch.displayWidth/10f ,_button_height-_button_height/10f,"Export", Export_Button.class, false);

        Ui.add_Button("import", 0, _button_height*2+_button_height/10f, _sketch.displayWidth/10f, _button_height-_button_height/10f,"Import", Import_Button.class, false);

        //CORRECT X & Y COORDINATES DO NOT CHNAGE
        Ui.add_Button("Node_display", _sketch.displayWidth / 9f,0, _sketch.displayWidth/10f, _button_height,"Display N", Node_Botton.class, false);

        Ui.add_Button("Edge_display",_sketch.displayWidth/9f*2f, 0, _sketch.width/9f, _button_height,"Display E", Edge_Button.class, false);

        Ui.add_Button("heuristic",_sketch.displayWidth/9f*3f, 0, _sketch.width/9f, _button_height,"Display H", Heuristic_Button.class, false);

        Ui.add_Button("PQ_display",(_sketch.displayWidth)/9f*4f, 0, _sketch.displayWidth/9f, _button_height,"Display Q", Edge_Button.class, false);

        //debugging slash testing
        //System.out.println("display: " + _sketch.displayWidth + ", " + _sketch.displayHeight);
        //System.out.println("normal: " + _sketch.width + ", " + _sketch.height);
    }

    /**
     * Util function that will add spaces to the front of a digit,
     * if the _range is bigger than the amount of digits in _digit.
     * It will not remove digits if the range is smaller.
     * @param _digit The number we want to make int oa string
     * @param _range The amount of characters our resulting string will have,
     *               any amount above the characters in the _digit will become spaces in front.
     * @return The string version of the _digit, with spaces in front depending on the _range.
     */
    public static String make_digit_fit_range(int _digit, int _range){
        int difference = _range-(""+_digit).length();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < difference; i++) {
                    result.append(" ");
        }
        result.append(""+_digit);
        return result.toString();
    }

    public static Edge find_Shared_Edge(Node _n1, Node _n2){

        for (Edge e : _n1.connected){
            if (e.to == _n2 || e.from == _n2 ){
                return e;
            }
        }

        return null;
    }


}
