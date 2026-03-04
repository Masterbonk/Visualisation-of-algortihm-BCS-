package org.algorithm;

import processing.core.PApplet;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import static org.algorithm.Main.Ui;
import static processing.core.PApplet.print;
import static processing.core.PApplet.println;


public class Util {
    PApplet sketch;
    int button_height;

    public static HashMap<String, Node> name_to_node;

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
        Ui.add_Button("Name_display", _sketch.displayWidth / 9f,0, _sketch.displayWidth/10f, _button_height,"Name", Name_Button.class, false);

        Ui.add_Button("Node_display", _sketch.displayWidth / 9f*2f,0, _sketch.displayWidth/10f, _button_height,"Display N", Node_Button.class, false);

        Ui.add_Button("Edge_display",_sketch.displayWidth/9f*3f, 0, _sketch.width/9f, _button_height,"Display E", Edge_Button.class, false);

        Ui.add_Button("heuristic",_sketch.displayWidth/9f*4f, 0, _sketch.width/9f, _button_height,"Display H", Heuristic_Button.class, false);

        Ui.add_Button("PQ_display",(_sketch.displayWidth)/9f*5f, 0, _sketch.displayWidth/9f, _button_height,"Display Q", Edge_Button.class, false);

        Ui.add_Button("clear",(_sketch.displayWidth)/9f*6f, 0, _sketch.displayWidth/9f, _button_height,"Clear", Clear_Button.class, false);

        Ui.add_Button("color_scheme",(_sketch.displayWidth)/9f*7f, 0, _sketch.displayWidth/9f, _button_height,"Pink", Color_Scheme_Button.class, false);

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
        Main.goal_node = null;
        Main.start_node = null;
        Main.node_1 = null;
        Main.initial_goal_node = null;
        Main.initial_start_node = null;
        if (Main.algorithm.get_U() != null){
            Main.algorithm.get_U().get_Heap().clear();
            Main.algorithm.get_U().get_Keys().clear();
        }


    }

    public static Edge find_Shared_Edge(Node _n1, Node _n2){

        for (Edge e : _n1.connected){
            if (e.to == _n2 || e.from == _n2 ){
                return e;
            }
        }

        return null;
    }

    public static void parseOSM(PApplet _sketch,String inp) throws Exception  {
        var input = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedInputStream(new FileInputStream(inp)));

        ArrayList<Util_Way> way_list = new ArrayList<>();
        ArrayList<String> nodes_in_current_way = new ArrayList<>();
        HashSet<String> all_nodes_in_use = new HashSet<>();

        name_to_node = new HashMap<>();

        boolean stop_first_loop = false;
        boolean way_has_begun = false;
        while (input.hasNext() && !stop_first_loop) { //Run number 1
            var tagKind = input.next();
            if (tagKind == XMLStreamConstants.START_ELEMENT) {
                var name = input.getLocalName();
                switch (name) {
                    case "way" -> {
                        nodes_in_current_way = new ArrayList<>();
                        way_has_begun = true;
                    }
                    case "tag" -> {
                        if (way_has_begun && Objects.equals(input.getAttributeValue(null, "k"), "highway")){
                            all_nodes_in_use.addAll(nodes_in_current_way);
                            way_list.add(new Util_Way(nodes_in_current_way));
                        }
                    }
                    case "nd" -> {
                        nodes_in_current_way.add(input.getAttributeValue(null, "ref")); //The key)
                    }
                    case "relation" -> {
                        stop_first_loop = true;
                    }
                }
            }
        }
        
        input.close();
        //input = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedInputStream(inputStream));
        input = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedInputStream(new FileInputStream(inp)));

        Node tmp = null;
        while (input.hasNext()) { //Run number 2
            var tagKind = input.next();
            if (tagKind == XMLStreamConstants.START_ELEMENT) {
                //println("1");
                var name = input.getLocalName();

                if (name.equals("node")) {
                    if (all_nodes_in_use.contains(input.getAttributeValue(null, "id"))){
                        //println("MADE A NODE");
                        tmp = new Node(_sketch, convertX(input.getAttributeValue(null, "lon")),convertY(input.getAttributeValue(null, "lat")));
                        name_to_node.put(input.getAttributeValue(null, "id"), tmp);
                    }
                }
            }
        }
        //new BiEdge(_sketch,tmp,Main.node_array.getFirst(),0);
        //println("Made it out of second loop");

        //When all nodes have been added, we will connect them with edges
        for (Util_Way w: way_list){
            w.connect(_sketch);
        }

        name_to_node.clear();
    }

    public static int convertX(String _x){
        float tmp = Float.parseFloat(_x);
        return (int) ((tmp - 12.588658)*1000000);
    }

    public static int convertY(String _y){
        float tmp = Float.parseFloat(_y);
        return (int) ((tmp - 55.658591)*1000000);
    }
}

class Util_Way{
    ArrayList<String> node_list;

    Util_Way(ArrayList<String> _node_list){
        node_list = _node_list;
        println(node_list.toString());
    }

    public void connect(PApplet _sketch){
        for (int i = 0; i< node_list.size()-1; i++){
            if (Util.name_to_node.get(node_list.get(i)) != null && Util.name_to_node.get(node_list.get(i + 1)) != null) {
                new BiEdge(_sketch, Util.name_to_node.get(node_list.get(i)), Util.name_to_node.get(node_list.get(i + 1)));
            } else {
                //println("New connect call \n size: "+node_list.size()+"\n i: "+i);
                println("One of them was null");
            }
        }
    }
}
