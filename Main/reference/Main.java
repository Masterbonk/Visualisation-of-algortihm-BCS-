
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/** Processing Sketch Main */
public class Main {
float x = 10;

void setup() //This will ONLY run when the program start and never again
{
  fullScreen(); //Is the size of the canvas
  frameRate(30); //Decides how many times per second the draw function is called
}

void draw() { //This will run frameRate times per second and repeat continuesly
  background(204); //Draws over everything on screen clearing it for the next frame
  button(width/9*0, height-50, width/9, 50,"Back");
  button(width/9*1, height-50, width/9, 50,"Start/stop");
  button(width/9*2, height-50, width/9, 50,"Step");
  button(width/9*3, height-50, width/9, 50,"Cut");
  button(width/9*4, height-50, width/9, 50,"Circle");
  button(width/9*5, height-50, width/9, 50,"Line");
  button(width/9*6, height-50, width/9, 50,"Set flag A");
  button(width/9*7, height-50, width/9, 50,"Set flag B");
  button(width/9*8, height-50, width/9, 50,"Change weight");
} 







void mousePressed(){
  
}void button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
  int changeVal = 2;
  
  if (mouseOver(_x_pos, _y_pos, _x_size, _y_size)){
    fill(0x585858);
    rect(_x_pos+changeVal,_y_pos+changeVal, _x_size-changeVal*2, _y_size-changeVal*2);
    fill(0x000000);
    textSize((_x_size-changeVal)/7);
    textAlign(CENTER);
    text(_text, (_x_pos+changeVal)+(_x_size-changeVal*2)/2, (_y_pos+changeVal)+(_y_size-changeVal*2)/2+5);
  } else {
    fill(0x898989);
    rect(_x_pos,_y_pos, _x_size, _y_size);
    fill(0x000000);
    textSize(_x_size/7);
    textAlign(CENTER);
    text(_text, (_x_pos)+(_x_size)/2, (_y_pos)+(_y_size)/2+5);
  } 
}


boolean mouseOver(int _x_pos, int _y_pos, int _width, int _height){
  if (mouseX >= _x_pos && mouseX <= _x_pos+_width && 
      mouseY >= _y_pos && mouseY <= _y_pos+_height){
        return true;
     } else {
        return false;
     }
}
}

