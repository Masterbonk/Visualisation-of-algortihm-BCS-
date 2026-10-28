This is a tutorial of using our software.
Once the program is runnning, follow this guide.

## Start
This is the first thing that is shown. A graph & buttons. By default the program is set to run the Dijkstra algorithm. The algorithm run can be changed to A*, LPA* or D* Lite, by clicking the 'Algorithms' button.
You can hover over all the buttons to see what they do, or read through the entire tutorial.

<img width="1440" height="900" alt="image" src="https://github.com/user-attachments/assets/452b553f-1ce3-4309-ad92-071db320be82" />


## Run
In order to run the algorithm, a start and goal flag must have been placed. Left-click a node to place the start flag, right-click a node to set the goal flag.
<br/><br/>
<img width="207" height="82" alt="image" src="https://github.com/user-attachments/assets/cf74e05a-2ccc-4707-b8e1-e0b1d635f356" />
<img width="272" height="160" alt="image" src="https://github.com/user-attachments/assets/568032e1-55ac-4817-93ab-ff15c50648cf" />
<br/><br/>

Now either the play button or forward button can be pressed. The forward button steps through the algorithm step by step. The darkest color is the final path, while the lighter color is the edges considered during the algorithm's run. The big triangle is the run button while the small triangle is the step through button.
<br/><br/>
<img width="338" height="81" alt="image" src="https://github.com/user-attachments/assets/a22e32bb-64e8-43b2-acdf-aaf5f3ef43b9" />
<img width="397" height="242" alt="image" src="https://github.com/user-attachments/assets/323807c1-8824-45bb-9d65-fdbb430ec268" />


## Restart
There are several ways to restart the program.
- restart button
- clear button (which deletes the entire graph)
- graph button (which makes a new graph)
- changing algorithm mode

## Nodes
It's possible to delete or add nodes to the graph. The algorithm must be pause in order to do so. (However not if you are running LPA* in which case the algorithm is running indefinitely)
The cut (scissor) button will delete a node that's clicked on by the mouse.
The node (circle) button will add a node on the screen where the mouse has been clicked.
<br/><br/>
<img width="1439" height="899" alt="image" src="https://github.com/user-attachments/assets/343aaa9b-3145-47e2-a4b9-abe81ed7e10f" />


## Edges
Edges can be added by clicking the line button: 
- between two existing nodes 
- between one existing node and clicking somewhere on the screen
- clicking two different places on the screen
<img width="1439" height="899" alt="image" src="https://github.com/user-attachments/assets/0051a33b-adf4-4c6a-88a5-2d60ad628392" />

<br/><br/>
Furthermore an edge's weight can be altered, which by default is the actual pixel distance. Click display, then weight, to see the edge's weight. Click weight button in the buttom UI, to change an edge's weight. The weight cannot be smaller than the actual pixel distance.
<br/><br/>
<img width="1439" height="899" alt="image" src="https://github.com/user-attachments/assets/ecfee348-5d21-4d11-97ec-5160c58de89f" />

<br/><br/>
Deletion:
Edges can be deleted. If a nodes has edges and is deleted, the edges are deleted as well. The deletion (scissor) button works for edges as well as nodes

## Display
It's possible to change the program to pink mode. It's also possible to see different elements of the graph and/or algorithm, while it is running.
Clicking the display button allows you to toggle on:
- Name of the nodes
- Dist() or F() or g & rhs values of the nodes, depending on which algorithm is run
- Weight of the edges
- Heuristic between the start and goal flag
- Priority Queue
- Pink mode (visuals become pink)
- Priority Queue node (highligts the top node in priority queue)

<img width="255" height="170" alt="image" src="https://github.com/user-attachments/assets/f454bc92-c19c-4a8d-8eab-faa5e5c11498" />
<img width="255" height="170" alt="image" src="https://github.com/user-attachments/assets/7ec06976-ff2c-4333-a2c7-0dc0c6e58f38" />
<img width="255" height="170" alt="image" src="https://github.com/user-attachments/assets/c1ae7315-3c60-4ead-8c96-1fb0baa60abc" />
<img width="255" height="170" alt="image" src="https://github.com/user-attachments/assets/55923775-d22a-4913-a885-fb224bf6ecb7" />
<img width="255" height="170" alt="image" src="https://github.com/user-attachments/assets/f37ded36-63e2-46b0-a57f-5e608d41b129" />
<img width="255" height="170" alt="image" src="https://github.com/user-attachments/assets/0708c5a2-2fe5-4adc-a453-70c274116e95" />
<img width="255" height="170" alt="image" src="https://github.com/user-attachments/assets/62d73815-ba28-441c-b5b1-528d5f22f55c" />
<img width="278" height="170" alt="image" src="https://github.com/user-attachments/assets/1bd4265c-552b-4584-8fd5-fb9d27186a6b" />

## Priority Queue
As prevoiusly mentioned, one can see how the algorihtm finds the shortest path. This is done with a priority queue. The yellow colored node is the top node in the priority queue, meaning the one closest to start (d* lite does backwards search), and therfore the next node that will be evaluated. 

The nodes are inserted in the priority queue with the key consisting of **rhs value + the heurtstic between current node and start**  and the **rhs value**

<img width="1440" height="900" alt="image" src="https://github.com/user-attachments/assets/109c8d0e-324f-4fcc-aae0-8879d2a8d065" />

Specifically Node B is in the priority queue with the key: k1 = 880 and k2 = 247

## Export
A specfic graph can be saved for later. By clicking export the file graph.xml will be updated.
<img width="565" height="355" alt="image" src="https://github.com/user-attachments/assets/73e0e605-da0e-4726-97ff-ed777a33f5fc" />

## Import
We support two input types: osm and specific xml formats (meaning only our own). It might take a short while for the graph to load.
<br/><br/>
To import a prevoiusly saved graph simply click the import button, and find the file graph.xml
<br/><br/>
<img width="490" height="275" alt="image" src="https://github.com/user-attachments/assets/31213c0c-015a-47e4-b5a7-03e04592eeca" />
<img width="490" height="275" alt="image" src="https://github.com/user-attachments/assets/80a919cf-0ae8-44b4-bee6-3453e5c1f185" />

To import osm files, simply find the osm you wish to import. It's is recommened to import smaller osm files.
Second image is from https://www.openstreetmap.org/#map=16/55.65957/12.59312 from which the osm file was downloaded.
<br/><br/>
<img width="480" height="300" alt="image" src="https://github.com/user-attachments/assets/afbcab3f-3a39-4811-b2c5-2f37705a75ca" />
<img width="480" height="300" alt="image" src="https://github.com/user-attachments/assets/caa8bb68-7d2b-4373-9a2c-334e3863d752" />

## LPA*
LPA* listens for changes in the graph, but does not move the start flag as shown. 
<br/><br/>
<img width="287" height="190" alt="image" src="https://github.com/user-attachments/assets/fd821c16-66e3-4b2c-91af-0f1fe20d2206" />
<img width="287" height="190" alt="image" src="https://github.com/user-attachments/assets/cdb8f4f9-5430-4452-bebf-48e37ccc94eb" />
<img width="287" height="190" alt="image" src="https://github.com/user-attachments/assets/c9f1d2a8-11d9-4ef5-872b-b153007af73c" />














