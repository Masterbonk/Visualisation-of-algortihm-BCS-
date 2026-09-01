package org.algorithm.graph.IO;

import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;
import processing.core.PApplet;
import org.algorithm.Main;

import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.nio.file.Path;

public class Export_Handler {


    public static void Export(){

        try {


            File dir = new File("Export");
            dir.mkdir();

            File file = new File("Export","graph.xml");
            FileWriter myWriter = null;
            int count = 1;
            while (file.exists()) {
                file = new File("Export", "graph (" + count + ").xml");
                count++;
            }
            myWriter = new FileWriter(file);

            myWriter.write(convert_To_XML());
            myWriter.close();// must close manually
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    
    
    
    private static String convert_To_XML(){
        StringBuilder output = new StringBuilder();
        output.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Graph>");
        for (Node n : Main.node_array ){
            output.append("\n\t<Node>").append("\n\t\t<ref>").append(n.get_Id()).append("</ref>").append("\n\t\t<x>").append(n.get_X()).append("</x>").append("\n\t\t<y>").append(n.get_Y()).append("</y>");

            /* dont actually need to know what edges the nodes have when making the nodes
            .append("\n\t\t<connected>");
            for(Edge e : n.get_Connected()){
                output.append("\n\t\t\t<edge>").append("\n\t\t\t\t<ref>").append(e.get_Id()).append("</ref>").append("\n\t\t\t</edge>");

            }
            \n\t\t</connected>
            */

            output.append("\n\t</Node>");

        }

        for (Edge e: Main.edge_array){
            output.append("\n\t<Edge>").
                    append("\n\t\t<ref>").append(e.get_Id()).append("</ref>").
                    append("\n\t\t<To>").append(e.get_To().get_Id()).append("</To>").
                    append("\n\t\t<From>").append(e.get_From().get_Id()).append("</From>").
                    append("\n\t\t<Weight>").append(e.get_Weight()).append("</Weight>\n\t</Edge>");
        }

        output.append("\n</Graph>");
        return output.toString();
    }
}


