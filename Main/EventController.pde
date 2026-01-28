

void mousePressed(){
  
  for(int i = 0; i < button_array.length; i++){
    if(button_array[i].mouse_Over()){
        button_array[i].click();
    }
  }
  if (!button_array[9].mouse_Over() && !button_array[10].mouse_Over() && !button_array[11].mouse_Over() && file_clicked){ //Lukker file menuen hvis man klikker uden for den mens den er åben.
          file_clicked = false;
  }
}
