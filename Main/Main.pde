float x = 10;

void setup() //This will ONLY run when the program start and never again
{
  fullScreen(); //Is the size of the canvas
  frameRate(30); //Decides how many times per second the draw function is called
}

void draw() { //This will run frameRate times per second and repeat continuesly
  background(204); //Draws over everything on screen clearing it for the next frame
  ellipse(mouseX, mouseY, x, x);
  x+=10;
} 
