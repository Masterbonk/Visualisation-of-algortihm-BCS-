
class Button{
  int x_pos, y_pos, x_size, y_size; 
  String text;

  Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    x_pos = _x_pos; y_pos = _y_pos; x_size = _x_size; y_size = _y_size; text = _text;
  }
  
  void draw(){
    int changeVal = 2;
    
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
  
  boolean mouse_Over(){
  if (mouseX >= x_pos && mouseX <= x_pos+x_size && 
      mouseY >= y_pos && mouseY <= y_pos+y_size){
        return true;
     } else {
        return false;
     }
  }
  
  void click(){
    println("Not implemented");
  }
}

class Back_Button extends Button{
  Back_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}

class Pause_Button extends Button{
  Pause_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}

class Forward_Button extends Button{
  Forward_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}

class Cut_Button extends Button{
  Cut_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}

class Circle_Button extends Button{
  Circle_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}

class Line_Button extends Button{
  Line_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}
class Flag_A_Button extends Button{
  Flag_A_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}
class Flag_B_Button extends Button{
  Flag_B_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}
class Weight_Button extends Button{
  Weight_Button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
    super(_x_pos, _y_pos, _x_size, _y_size, _text);
  }
  
  void click(){
    println("Not implemented");
  }
}
