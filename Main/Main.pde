


float x = 10;

void setup() //This will ONLY run when the program start and never again
{
  fullScreen(); //Is the size of the canvas
  frameRate(30); //Decides how many times per second the draw function is called
  PFont font;
  // The font must be located in the sketch's 
  // "data" directory to load successfully
  font = createFont("Arial-Black-48.vlw", 128);
  textFont(font);
}

void draw() { //This will run frameRate times per second and repeat continuesly
  background(204); //Draws over everything on screen clearing it for the next frame
  
} 

void mousePressed(){
  
}


void Make_UI(){
  
  //Make bottom part of UI
  button(width/9*0, height-50, width/9, 50,"⏴"); //Step back
  button(width/9*1, height-50, width/9, 50,"⏯"); //pause
  button(width/9*2, height-50, width/9, 50,"⏵"); //Step forward
  button(width/9*3, height-50, width/9, 50,"✂"); //Cut
  button(width/9*4, height-50, width/9, 50,"⏺"); //Create circle
  button(width/9*5, height-50, width/9, 50,"\\"); //Create line
  button(width/9*6, height-50, width/9, 50,"⚐"); //Set flag A
  button(width/9*7, height-50, width/9, 50,"⚑"); //Set flag B
  button(width/9*8, height-50, width/9, 50,"Weight"); //Weight
}
