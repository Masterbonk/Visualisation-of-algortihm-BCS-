/**
* Button class is used to make all buttons in the program. They have their size, position and a text inside them all built in.
*  
*  
* @param x_pos The x_pos is the x position of the square that makes up the button. Effectively where on x axis the left side of the button is.
* @param y_pos The y_pos is the y position of the square that makes up the button. Effectively where on y axis the top side of the button is.
* @param x_size How wide the button is.
* @param x_pos How high the buttom is.
*/
class Button{
  int x_pos, y_pos, x_size, y_size; 
  String text;

  Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    x_pos = _x_pos; y_pos = _y_pos; x_size = _x_size; y_size = _y_size; text = _text;
  }
  
  /**
  * Draw is used to draw the button at the end of every draw loop. ALso makes it change size and color if the mosue button hovers over it.
  */
  
  void draw(){
    fill(#A2A2A2);
    rect(x_pos,y_pos, x_size, y_size);
    
    int changeVal = 5;
    
    if (mouse_Over()){
      fill(#000000);
      rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
      fill(#FFFFFF); //Text color
      textSize((x_size-changeVal)/5);
      textAlign(CENTER);
      text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2, (y_pos+changeVal)+(y_size-changeVal*2)/2+10);
    } else {
      fill(#505050);
      rect(x_pos,y_pos, x_size, y_size);
      fill(#FFFFFF);
      textSize(x_size/5);
      textAlign(CENTER);
      text(text, (x_pos)+(x_size)/2, (y_pos)+(y_size)/2+10);
    } 
  }
  
  /**
  * @return is true if the mouse is currently hovering over the button.
  */
  
  boolean mouse_Over(){
  if (mouseX >= x_pos && mouseX <= x_pos+x_size && 
      mouseY >= y_pos && mouseY <= y_pos+y_size){
        return true;
     } else {
        return false;
     }
  }
  
  /**
  * Click is extended to all subclass of buttons and is used to activate the functionality of the button once it's clicked.
  */
  void click(){
    println("Not implemented");
  }
}

class Back_Button extends Button{
  Back_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 1");
  }
}

class Pause_Button extends Button{
  boolean paused = false;
  
  Pause_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    
    if (!paused) {
      text = "⏸"; //start
    } else text = "⏯"; //Pause
    paused = !paused;
    println("Not fully implemented 2");
  }
}

class Forward_Button extends Button{
  Forward_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 3");
  }
}

class Cut_Button extends Button{
  Cut_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 4");
  }
}

class Circle_Button extends Button{
  Circle_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 5");
  }
}

class Line_Button extends Button{
  Line_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 6");
  }
}
class Flag_A_Button extends Button{
  Flag_A_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 7");
  }
}
class Flag_B_Button extends Button{
  Flag_B_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 8");
  }
}
class Weight_Button extends Button{
  Weight_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 9");
  }
}

class File_Button extends Button{
  File_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    file_clicked = !file_clicked;
  }
}

/**
* Unique type knap til knapper under file, som skal gemmes væk når man ikke har klikket på file.
*/

class File_Type_Buttons extends Button{
  
  File_Type_Buttons(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void draw(){
    if (file_clicked){
      fill(#A2A2A2);
      rect(x_pos,y_pos, x_size, y_size);
      
      int changeVal = 5;
      
      if (mouse_Over()){
        fill(#000000);
        rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
        fill(#FFFFFF); //Text color
        textSize((x_size-changeVal)/5);
        textAlign(CENTER);
        text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2, (y_pos+changeVal)+(y_size-changeVal*2)/2+10);
      } else {
        fill(#505050);
        rect(x_pos,y_pos, x_size, y_size);
        fill(#FFFFFF);
        textSize(x_size/5);
        textAlign(CENTER);
        text(text, (x_pos)+(x_size)/2, (y_pos)+(y_size)/2+10);
      } 
    }
  }

}

class Export_Button extends File_Type_Buttons{
  Export_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 9");
  }
}

class Import_Button extends File_Type_Buttons{
  Import_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented 9");
  }
}
