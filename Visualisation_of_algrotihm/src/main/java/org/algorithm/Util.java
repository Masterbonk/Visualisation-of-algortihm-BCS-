package org.algorithm;

import processing.core.PApplet;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import static org.algorithm.Main.*;
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
            Main.algorithm.get_U().clear_Heap();
            Main.algorithm.get_U().clear_Keys();
        }
        count = 0;
        letter = 64;


    }

    public static Edge find_Shared_Edge(Node _n1, Node _n2){

        for (Edge e : _n1.connected){
            if (e.to == _n2 || e.from == _n2 ){
                return e;
            }
        }

        return null;
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

    /**
     * The parseOSM file. It is given the path to a .osm file, which it then parses over 2 scans of the file.
     * While scanning it creates util_way objects to represent the ways and later nodes and edges once it's
     * sure of what to create. it only consideres ways with the key tag of highway, since a lot of other ways
     * are simply there to draw the map, which we ignore.
     * @param _sketch The sketch file so it can do stuff like create new edges or nodes.
     * @param inp The string file path to the file.
     * @throws Exception Nessecary to use the file reader as the file might be missing or smt.
     */
    public static void parseOSM(PApplet _sketch,String inp) throws Exception  {
        //Starts a new stream reader of the file, which sits at the first character of the file.
        var input = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedInputStream(new FileInputStream(inp)));

        //Creates the arrays that keep track of our information
        ArrayList<Util_Way> way_list = new ArrayList<>(); //Keeps track of the way objects we have made

        ArrayList<String> nodes_in_current_way = new ArrayList<>(); //A shorter array that is often reset to
        // make room for others. Keeps track of all the nodes the current way is refrencing. We won't kow if
        // we have to keep them until we get to the tag of the way.

        HashSet<String> all_nodes_in_use = new HashSet<>(); //The set of all nodes in ways we want to keep.
        // Each node is saved as it's string refrence in the OSM file. We only add nodes if they are in ways
        // with the highway tag.

        name_to_node = new HashMap<>(); //Has a nodes id connected to the node object we create. This way we can refrence the node object without having to add a id to the node object.

        double minlonX = 0; //according to the osm bounding box, this value will be the smallest x value of
        // the nodes. used to help convert the x and y cordinates to our own cordinates.
        double minlatY = 0; //Same as above.


        boolean stop_first_loop = false; //False until we need to stop the loop. We need to stop the while
        // loop when we meet a relation, or we get to the end of the file, if it has no relation.
        boolean way_has_begun = false; //Is false until we find the first way. When we do, it becomes true
        // and we begin to scan the document. This prevents nodes with the tag "highway" from creating
        // Util_Way

        // This while loop is made to extract all the ways with the highway tag and to find out which
        // nodes the highway ways use. When we do our second loop we can then specifically extract those.
        while (input.hasNext() && !stop_first_loop) { //Run number 1

            //gets the next line in the input
            var tagKind = input.next();

            //if we are sure that the element is a start element, ie. <xxxx, and not something like a
            // blank line
            if (tagKind == XMLStreamConstants.START_ELEMENT) {

                //What kind of tag does this line have. Example it's node when we have <node id="1234...
                // ...>
                var name = input.getLocalName();

                //A switch case that accounts for each different tag we have available.
                switch (name) {
                    case "bounds" -> {
                        //bounds only exists at the top of a typical OSM file and specifically gets us the
                        // smallest x and y value in our graph. We record these for later use.
                        minlonX = Float.parseFloat(input.getAttributeValue(null, "minlon"));
                        minlatY = Float.parseFloat(input.getAttributeValue(null, "minlat"));
                    }
                    case "way" -> {
                        //Way resets our nodes_in_current_way list so that we don't have any nodes being added to the wrong way.
                        nodes_in_current_way = new ArrayList<>();
                        //When we get to the first way, we begin checking the following tags for highway.
                        way_has_begun = true;
                    }
                    case "tag" -> {
                        //Tags tells us stuff about the object. We only care about the highway key and
                        // whether we have encountered a way yet.
                        if (way_has_begun && Objects.equals(input.getAttributeValue(null, "k"), "highway")){
                            //If we have encountered a way, we add all the node refrences we have found to our legal nodes and create a util_way object to record this one. It is also added to our list of ways.
                            all_nodes_in_use.addAll(nodes_in_current_way);
                            way_list.add(new Util_Way(nodes_in_current_way));
                        }
                    }
                    case "nd" -> {
                        //nd appears inside ways and directly refrence a node that the osm file has made
                        // earlier. When we find these, we add it to our local list, since we won't know
                        // if we should keep it until we get to the tags under the way, after all the nd's
                        // have been called.
                        nodes_in_current_way.add(input.getAttributeValue(null, "ref")); //The key)
                    }
                    case "relation" -> {
                        //If we meet a relation, we know that the rest of the file does not matter, as
                        // relations only appear after all the ways have been described.
                        stop_first_loop = true;
                    }
                }
            }
        }

        //We close our input
        input.close();

        //We create a completly new file reader so we can start from the start position of the file again.
        input = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedInputStream(new FileInputStream(inp)));

        //Create a tmp node for future use.
        Node tmp = null;

        //False until we need to stop the loop. We need to stop the while
        // loop when we meet a way, or we get to the end of the file, if it has no way.
        boolean stop_second_loop = false;

        //This run through the file only looks for nodes and saves them if they exist in a way we have kept. If they have not, we will ignore them and continue.
        while (input.hasNext() && !stop_second_loop) { //Run number 2

            //get the next line in the input.
            var tagKind = input.next();

            //If we have a start element, ie. not a blank line or a line that is not <xxx
            if (tagKind == XMLStreamConstants.START_ELEMENT) {

                //Get the name of the type of object in the osm file.
                var name = input.getLocalName();

                //Since we are looking for nodes, we only look if the name is node, ie. <node...
                if (name.equals("node")) {
                    //If the node id is saved in the all_nodes_in_use set we know we want to keep it.
                    if (all_nodes_in_use.contains(input.getAttributeValue(null, "id"))){

                        //Creates a new node with the right x and y, which we get by using the convertX
                        // and convert Y functions.
                        tmp = new Node(_sketch, convertX(input.getAttributeValue(null, "lon"), minlonX),convertY(input.getAttributeValue(null, "lat"), minlatY),generate_Name());
                        name_to_node.put(input.getAttributeValue(null, "id"), tmp);
                    }
                } else if (name.equals("way")) {
                    //We stop the loop if we get to the way objects in the osm.
                    stop_second_loop = true;
                }
            }
        }

        //When all nodes have been added, we will connect them with edges
        for (Util_Way w: way_list){
            w.connect(_sketch);
        }

        name_to_node = new HashMap<>();
    }

    public static void parseOSMIntersection(PApplet _sketch,String inp) throws Exception  {
        var input = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedInputStream(new FileInputStream(inp)));

        ArrayList<Util_Way> way_list = new ArrayList<>();
        ArrayList<String> nodes_in_current_way = new ArrayList<>();
        HashMap<String, ArrayList<Util_Way>> all_nodes_in_use = new HashMap<>();

        name_to_node = new HashMap<>();

        double minlonX = 0;
        double minlatY = 0;


        boolean stop_first_loop = false;
        boolean way_has_begun = false;
        while (input.hasNext() && !stop_first_loop) { //Run number 1
            var tagKind = input.next();
            if (tagKind == XMLStreamConstants.START_ELEMENT) {
                var name = input.getLocalName();
                switch (name) {
                    case "bounds" -> {
                        minlonX = Float.parseFloat(input.getAttributeValue(null, "minlon"));
                        minlatY = Float.parseFloat(input.getAttributeValue(null, "minlat"));
                    }
                    case "way" -> {
                        nodes_in_current_way = new ArrayList<String>();
                        way_has_begun = true;
                    }
                    case "tag" -> {
                        if (way_has_begun && Objects.equals(input.getAttributeValue(null, "k"), "highway")){
                            Util_Way new_way = new Util_Way(nodes_in_current_way);
                            for (String s: nodes_in_current_way){
                                if (nodes_in_current_way.contains(s)){
                                    ArrayList<Util_Way> tmp = new ArrayList<>();
                                    all_nodes_in_use.putIfAbsent(s, tmp);
                                    all_nodes_in_use.get(s).add(new_way);
                                } else {
                                    ArrayList<Util_Way> tmp = new ArrayList<>();
                                    tmp.add(new_way);
                                    all_nodes_in_use.putIfAbsent(s, tmp);
                                }
                            }
                            way_list.add(new_way);
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
                    if (all_nodes_in_use.containsKey(input.getAttributeValue(null, "id"))){
                        if (all_nodes_in_use.get(input.getAttributeValue(null, "id")).size() > 1){
                            tmp = new Node(_sketch, convertX(input.getAttributeValue(null, "lon"), minlonX),convertY(input.getAttributeValue(null, "lat"), minlatY));
                            name_to_node.put(input.getAttributeValue(null, "id"), tmp);
                        } else {
                            if(all_nodes_in_use.get(input.getAttributeValue(null, "id")).getFirst().remove(input.getAttributeValue(null, "id"))){
                                way_list.remove(all_nodes_in_use.get(input.getAttributeValue(null, "id")).getFirst());
                            } else if (all_nodes_in_use.get(input.getAttributeValue(null, "id")).getFirst().node_Should_Exist(input.getAttributeValue(null, "id"))){
                                tmp = new Node(_sketch, convertX(input.getAttributeValue(null, "lon"), minlonX),convertY(input.getAttributeValue(null, "lat"), minlatY));
                                name_to_node.put(input.getAttributeValue(null, "id"), tmp);
                            }
                        }
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

    public static int convertX(String _x, double _bounds){
        float tmp = Float.parseFloat(_x);
        return (int) ((tmp - _bounds)*1000000);
    }

    public static int convertY(String _y, double _bounds){
        float tmp = Float.parseFloat(_y);
        double flipY = 0- ((tmp - _bounds)*1000000);
        return (int) flipY;
    }
}

/**
 * Util_Way is ways described in OSM files. This class is made to support the parseOSM functions and keeps
 * some specific info related to the ways. It specifically keeps a list of nodes in our node_list, using a string
 * ArrayList, where each string corresponds to a node ID in the OSM file.
 */
class Util_Way{
    ArrayList<String> node_list;

    Util_Way(ArrayList<String> _node_list){
        node_list = _node_list;
        println(node_list.toString());
    }

    /**
     * Connect is a function that creates BiEdges between all the nodes in the node_list. They are made in order they are in the list, so first element will connect to second element and so on.
     *
     * If it prints "One of them was null" it means that either node does not exist.
     * @param _sketch The sketch for the program, necessary to use certain processing functions.
     */
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

    /**
     * Remove removes an element from our node_list.
     * It will also return true if the size of the way is less than 2, which means that hte way should be removed from the way list up in ParseOSM
     * It will NOT remove the first and last element of the list, as they shall be made no matter what.
     * @param _to_be_removed The string refrence to the node that is to be removed.
     * @return True if the way should be removed from the way_list up in parseOSM.
     */
    public boolean remove(String _to_be_removed){
        if (node_list.get(0).equals(_to_be_removed) || node_list.get(node_list.size()-1).equals(_to_be_removed)){
            return false;
        } else {
            node_list.remove(_to_be_removed);
            if (node_list.size() < 2) {
                return true;
            } else return false;
        }
    }

    /**
     * A special function that checks whether a node is at the end or the start of this ways list.
     * @param _node_ref The node id/ref in a string that we wish to check.
     * @return true if it's at the start or end of the list, false otherwise.
     */
    public boolean node_Should_Exist(String _node_ref){
        if (node_list.get(0).equals(_node_ref) || node_list.get(node_list.size()-1).equals(_node_ref)){
            return true;
        } else return false;
    }
}
