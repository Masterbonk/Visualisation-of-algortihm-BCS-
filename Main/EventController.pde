

void mouseClicked(){
  for(int i = 0; i < button_array.length; i++){
    if(button_array[i].mouse_Over()){
        button_array[i].click();
    }
  }
}
