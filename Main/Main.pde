float x = 10;

Button[] button_array;

void setup() //This will ONLY run when the program start and never again
{
  fullScreen(); //Is the size of the canvas
  frameRate(30); //Decides how many times per second the draw function is called
  PFont font;
  // The font must be located in the sketch's 
  // "data" directory to load successfully
  font = createFont("Arial-Black-48.vlw", 128);
  textFont(font);
  button_array = new Button[9];
  Make_UI();
}

void draw() { //This will run frameRate times per second and repeat continuesly
  background(204); //Draws over everything on screen clearing it for the next frame
  
  for(int i = 0; i < button_array.length; i++){
    button_array[i].draw();
  }
} 

void mousePressed(){
  
}


void Make_UI(){
  
  //Make bottom part of UI
  Button back = new Back_Button(width/9*0, height-50, width/9, 50,"⏴"); //Step back
  button_array[0] = back;
  Button pause = new Pause_Button(width/9*1, height-50, width/9, 50,"⏯"); //pause
  button_array[1] = pause;
  Button forward = new Forward_Button(width/9*2, height-50, width/9, 50,"⏵"); //Step forward
  button_array[2] = forward;
  Button cut = new Cut_Button(width/9*3, height-50, width/9, 50,"✂"); //Cut
  button_array[3] = cut;
  Button circle = new Circle_Button(width/9*4, height-50, width/9, 50,"⏺"); //Create circle
  button_array[4] = circle;
  Button line = new Line_Button(width/9*5, height-50, width/9, 50,"\\"); //Create line
  button_array[5] = line;
  Button flag_a = new Flag_A_Button(width/9*6, height-50, width/9, 50,"⚐"); //Set flag A
  button_array[6] = flag_a;
  Button flag_b = new Flag_B_Button(width/9*7, height-50, width/9, 50,"⚑"); //Set flag B
  button_array[7] = flag_b;
  Button weight = new Weight_Button(width/9*8, height-50, width/9, 50,"Weight"); //Weight
  button_array[8] = weight;
}
