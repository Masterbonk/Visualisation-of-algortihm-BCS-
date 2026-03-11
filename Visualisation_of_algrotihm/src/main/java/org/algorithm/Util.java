package org.algorithm;

import org.algorithm.graph.edges.Edge;
import org.algorithm.graph.Node;
import org.algorithm.ui.buttons.*;
import processing.core.PApplet;

import java.util.ArrayList;

import static org.algorithm.Main.*;
import static processing.core.PApplet.print;
import static processing.core.PApplet.println;


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
        return (float) (Math.round(Math.sqrt((Math.pow(a.get_X() - b.get_X(),2)) + (Math.pow(a.get_Y() - b.get_Y(),2)))* 100.0) / 100.0);
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
        Ui.add_Button("Name_display", _sketch.displayWidth / 9f,0, _sketch.displayWidth/10f, _button_height,"Name", Name_Button.class, false);

        Ui.add_Button("Node_display", _sketch.displayWidth / 9f*2f,0, _sketch.displayWidth/10f, _button_height,"Display N", Node_Button.class, false);

        Ui.add_Button("Edge_display",_sketch.displayWidth/9f*3f, 0, _sketch.width/9f, _button_height,"Display E", Edge_Button.class, false);

        Ui.add_Button("heuristic",_sketch.displayWidth/9f*4f, 0, _sketch.width/9f, _button_height,"Display H", Heuristic_Button.class, false);

        Ui.add_Button("PQ_display",(_sketch.displayWidth)/9f*5f, 0, _sketch.displayWidth/9f, _button_height,"Display Q", PQueue_Button.class, false);

        Ui.add_Button("clear",(_sketch.displayWidth)/9f*6f, 0, _sketch.displayWidth/9f, _button_height,"Clear", Clear_Button.class, false);

        Ui.add_Button("algo_mode",(_sketch.displayWidth)/9f*7f, 0, _sketch.displayWidth/9f, _button_height,"D* Lite", Algo_Mode_Button.class, false);

        Ui.add_Button("color_scheme",(_sketch.displayWidth)/9f*8f, 0, _sketch.displayWidth/9f, _button_height,"Pink", Color_Scheme_Button.class, false);

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

    /**
     * Util function that will add spaces to the front of a digit,
     * if the _range is bigger than the amount of digits in _digit.
     * It will not remove digits if the range is smaller.
     * @param _digit The number we want to make int a new string
     * @param _range The amount of characters our resulting string will have,
     *               any amount above the characters in the _digit will become spaces in front.
     * @return The string version of the _digit, with spaces in front depending on the _range.
     */
    public static String make_digit_fit_range(String _digit, int _range){
        int difference = _range-_digit.length();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < difference; i++) {
            result.append(" ");
        }
        result.append(_digit);
        return result.toString();
    }

    public static void delete_Graph(){
        Main.edge_array = new ArrayList<>();
        Main.node_array = new ArrayList<>();
        algorithm.set_Goal(null);
        algorithm.set_Start(null);
        Main.node_1 = null;
        Main.initial_goal_node = null;
        Main.initial_start_node = null;
        if (Main.algorithm.get_U() != null){
            Main.algorithm.get_U().clear_Heap();
            Main.algorithm.get_U().clear_Keys();
        }
        count = 0;
        letter = 64;


    }

    public static Edge find_Shared_Edge(Node _n1, Node _n2){

        for (Edge e : _n1.get_Connected()){
            if (e.get_To() == _n2 || e.get_From() == _n2 ){
                return e;
            }
        }

        return null;
    }

    /**
     * @return the connected node with the lowest g value
     * */
    public static Node find_Min_G_Node(Node _n){
        int min = MAX_INT;
        Node tmp = null;
        for(Edge e: _n.get_Connected()){
            Node other_node = e.get_From();
            if (e.get_From() == _n) other_node = e.get_To();
            if (other_node.get_G_Val() != MAX_INT) {
                if (min > e.get_Weight()+other_node.get_G_Val() && e.get_Weight()+other_node.get_G_Val() > -1){
                    min = e.get_Weight()+other_node.get_G_Val();
                    tmp = other_node;
                }
            }

        }
        return tmp;
    }

    /**
     * Finds the smallest g value amongst all the nodes.
     * @param _n The node that is part of the graph that we wish to find the smallest g value of
     * @return The int value of the smallest g
     */
    public static int find_Min_G(Node _n){
        int min = MAX_INT;
        for(Edge e: _n.get_Connected()){

            Node other_node = e.get_From();
            if (e.get_From() == _n) other_node = e.get_To();

            if (other_node.get_G_Val() != MAX_INT) {
                if (min > e.get_Weight()+other_node.get_G_Val() && e.get_Weight()+other_node.get_G_Val() > -1){
                    min = e.get_Weight()+other_node.get_G_Val();
                }
            }
        }

        return min < -1 ? MAX_INT : min;
    }



    /**
     * @return a string composed of a letter based of an ascii code and a number depending on how many of that letter nodes there are
     * */
    public static String generate_Name(){
        if(letter == 90){ //if we are at the end of the alphabet via ascii, then go back to A (64 isn't A, its just so it doesn't skip)
            letter = 64;
            count++;//also increment our number label
        }
        letter++;//increment our ascii letter
        if (count == 0) { //if the count is 0 we don't append the number to the name
            return Character.toString ((char) letter);
        } else {//else we append the number to the name
            return Character.toString ((char) letter) + count;
        }
    }
}
