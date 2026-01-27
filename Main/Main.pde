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
  
}
