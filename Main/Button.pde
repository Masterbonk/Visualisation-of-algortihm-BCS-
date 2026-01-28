void button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
  int changeVal = 2;
  
  if (mouseOver(_x_pos, _y_pos, _x_size, _y_size)){
    fill(#000000);
    rect(_x_pos+changeVal,_y_pos+changeVal, _x_size-changeVal*2, _y_size-changeVal*2);
    fill(#FFFFFF); //Text color
    textSize((_x_size-changeVal)/5);
    textAlign(CENTER);
    text(_text, (_x_pos+changeVal)+(_x_size-changeVal*2)/2, (_y_pos+changeVal)+(_y_size-changeVal*2)/2+10);
  } else {
    fill(#505050);
    rect(_x_pos,_y_pos, _x_size, _y_size);
    fill(#FFFFFF);
    textSize(_x_size/5);
    textAlign(CENTER);
    text(_text, (_x_pos)+(_x_size)/2, (_y_pos)+(_y_size)/2+10);
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
