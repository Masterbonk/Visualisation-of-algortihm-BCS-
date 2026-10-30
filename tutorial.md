This is a tutorial of using our software.
Once the program is runnning, follow this guide.

## Start
This is the first thing that is shown. A graph & buttons. By default the program is set to run the Dijkstra algorithm. The algorithm run can be changed to A*, LPA* or D* Lite, by clicking the 'Algorithms' button.
You can hover over all the buttons to see what they do, or read through the entire tutorial.
Right click the screen to pan, use our mousepad, mouse-wheel or '+' & '-' to zoom in and out.

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

<img width="1439" height="899" alt="image" src="https://github.com/user-attachments/assets/bd07d79c-4801-4e35-8d8c-c80cd4494936" />
<img width="1439" height="899" alt="image" src="https://github.com/user-attachments/assets/72c030bc-6b8f-41df-8bef-70ed35564bf3" />

## Priority Queue
It is possible to see how the chosen algorithm finds the shortest path. The priority queue(PQ), is that priority queue the given algorithms uses. Depending on the algorithms different calculations is used to update the PQ.

_explanation for the different algorithms PQ to come_

## Export
A specific graph can be saved for later. By clicking save under the file button, the current graph will be saved as a new file in the export folder of the program.
<img width="1439" height="899" alt="image" src="https://github.com/user-attachments/assets/d75eea6d-1255-4efa-b178-962913cde22d" />

## Import
We support two input types of import: 
- osm files
- the graph files that you can save to the export folder (aka our own xml type)

It might take a short moment for the program to load an imported file. It is recommended using smaller osm files, as there currently is a limit of zooming out.

<br/><br/>
To import a prevoiusly saved graph simply click the import button, and find your file in the export folder.
<br/><br/>

To import osm files, simply find the osm you wish to import. 
Second image is from https://www.openstreetmap.org/export#map=17/55.658727/12.592885 where the osm file was downloaded.
<br/><br/>
<img width="960" height="600" alt="image" src="https://github.com/user-attachments/assets/29fc9e7d-86d8-4771-82b1-4bacb778e99d" />
<img width="960" height="600" alt="image" src="https://github.com/user-attachments/assets/f19f3626-6766-4a66-835f-3aaff8745124" />

## Algorithms
_potentially some more information about the algorithms here_













