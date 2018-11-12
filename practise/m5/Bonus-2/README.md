Bridge: A bridge (or cut-edge) is an edge whose deletion increases the number of connected components. Equivalently, an edge is a bridge if and only if it is not contained in any cycle. Bridge uses depth-first search to find time the bridges in a graph. It takes time proportional to V + E in the worst case. 

Input Format :
First line of input contains number of Vertices.
Second line of input contains number of Edges.
From third line onwards, each line contains two vertices separated by space, which are to be connected in Undirected Graph.

Output Format:
Print the number of bridges, if graph has bridges else No Bridge.


Note : Use Bag.java to store adjacent vertices, to print them in output order.