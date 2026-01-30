package org.algorithm;
import processing.core.PApplet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends PApplet{

    public static void main(String[] args){
        String[] processingArgs = {"Main"};
        Main main = new Main();
        PApplet.runSketch(processingArgs, main);
    }

    public void settings(){
        size(600, 600);
    }

    public void draw(){
        ellipse(200,200,200,200);
    }
}
