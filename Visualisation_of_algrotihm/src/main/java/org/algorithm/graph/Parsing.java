package org.algorithm.graph;

import org.algorithm.Main;
import org.algorithm.graph.edges.BiEdge;
import org.algorithm.graph.edges.Edge;
import processing.core.PApplet;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.*;

import static org.algorithm.Util.generate_Name;
import static processing.core.PApplet.println;

public class Parsing {
    public static HashMap<String, Node> name_to_node;


    /**
     * The parseOSM file. It is given the path to a .osm file, which it then parses over 2 scans of the file.
     * While scanning it creates util_way objects to represent the ways and later nodes and edges once it's
     * sure of what to create. it only consideres ways with the key tag of highway, since a lot of other ways
     * are simply there to draw the map, which we ignore.
     * @param _sketch The sketch file so it can do stuff like create new edges or nodes.
     * @param inp The string file path to the file.
     * @param _remove_non_intersecting If true, it will simplify the resulting array by removing non
     *                                 intersecting nodes. The path it take should be the same.
     * @throws Exception Nessecary to use the file reader as the file might be missing or smt.
     */
    public static void parseOSM(PApplet _sketch, String inp, boolean _remove_non_intersecting) throws Exception  {
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

        if(_remove_non_intersecting) {
            //When we have made the initial nodes and edges, we will completly remove the intersections
            ArrayList<Node> array = (ArrayList<Node>) Main.node_array.clone();
            for (int i = 0; i < array.size(); i++) {
                array.get(i).strait_Edge_Connecting_Node();
            }
        }
        //Clears the name to node map, so we don't save a lot of objects we might not need.
        name_to_node = new HashMap<>();
        Main.importing = false;
    }


    public static void parseXML(PApplet _sketch, String inp) throws Exception  {

        println("parse XML runs");
        var input = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedInputStream(new FileInputStream(inp)));

        ArrayList<Edge> edges = new ArrayList<>();

        Map<Integer, Node> nodes = new HashMap<>();

        String currentElement = null;

        int ref = 0, x = 0, y = 0;
        int from = 0, to = 0;

        while (input.hasNext()) {
            int event = input.next();

            switch (event) {

                case XMLStreamConstants.START_ELEMENT:
                    currentElement = input.getLocalName();
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = input.getText().trim();
                    if (text.isEmpty()) break;

                    switch (currentElement) {
                        case "ref":
                            ref = Integer.parseInt(text);
                            break;
                        case "x":
                            x = Integer.parseInt(text);
                            break;
                        case "y":
                            y = Integer.parseInt(text);
                            break;
                        case "From":
                            from = Integer.parseInt(text);
                            break;
                        case "To":
                            to = Integer.parseInt(text);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String end = input.getLocalName();

                    if (end.equals("Node")) {
                        Node node = new Node(_sketch, x, y);
                        nodes.put(ref, node);
                    }

                    if (end.equals("Edge")) {
                        Node from_node = nodes.get(from);
                        Node to_node = nodes.get(to);
                        edges.add(new Edge(_sketch,from_node, to_node,0));
                    }
                    break;
            }
        }
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
class Util_Way {
    ArrayList<String> node_list;

    Util_Way(ArrayList<String> _node_list) {
        node_list = _node_list;
    }

    /**
     * Connect is a function that creates BiEdges between all the nodes in the node_list. They are made in order they are in the list, so first element will connect to second element and so on.
     * <p>
     * If it prints "One of them was null" it means that either node does not exist.
     *
     * @param _sketch The sketch for the program, necessary to use certain processing functions.
     */
    public void connect(PApplet _sketch) {
        for (int i = 0; i < node_list.size() - 1; i++) {
            if (Parsing.name_to_node.get(node_list.get(i)) != null && Parsing.name_to_node.get(node_list.get(i + 1)) != null) {
                new BiEdge(_sketch, Parsing.name_to_node.get(node_list.get(i)), Parsing.name_to_node.get(node_list.get(i + 1)));
            } else {
                println("One of them was null");
            }
        }
    }

    /**
     * Remove removes an element from our node_list.
     * It will also return true if the size of the way is less than 2, which means that hte way should be removed from the way list up in ParseOSM
     * It will NOT remove the first and last element of the list, as they shall be made no matter what.
     *
     * @param _to_be_removed The string refrence to the node that is to be removed.
     * @return True if the way should be removed from the way_list up in parseOSM.
     */
    public boolean remove(String _to_be_removed) {
        if (node_list.get(0).equals(_to_be_removed) || node_list.get(node_list.size() - 1).equals(_to_be_removed)) {
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
     *
     * @param _node_ref The node id/ref in a string that we wish to check.
     * @return true if it's at the start or end of the list, false otherwise.
     */
    public boolean node_Should_Exist(String _node_ref) {
        if (node_list.get(0).equals(_node_ref) || node_list.get(node_list.size() - 1).equals(_node_ref)) {
            return true;
        } else return false;
    }

}
