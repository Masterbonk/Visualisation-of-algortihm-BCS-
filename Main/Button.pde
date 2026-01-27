void button(int _x_pos, int _y_pos, int _x_size, int _y_size, String _text){
  int changeVal = 2;
  
  if (mouseOver(_x_pos, _y_pos, _x_size, _y_size)){
    fill(#585858);
    rect(_x_pos+changeVal,_y_pos+changeVal, _x_size-changeVal*2, _y_size-changeVal*2);
    fill(#000000);
    textSize((_x_size-changeVal)/7);
    textAlign(CENTER);
    text(_text, (_x_pos+changeVal)+(_x_size-changeVal*2)/2, (_y_pos+changeVal)+(_y_size-changeVal*2)/2+5);
  } else {
    fill(#898989);
    rect(_x_pos,_y_pos, _x_size, _y_size);
    fill(#000000);
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
