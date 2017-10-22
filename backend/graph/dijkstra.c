/**
 * Thank you to Aditya Goel
 * Article from:
 * http://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
 *
 * C program for Dijkstra's single source shortest
 * path algorithm. The program is for adjacency matrix
 * representation of the graph.
 */

#include <stdio.h>
#include <limits.h>

#define G_SIZE 8000

typedef enum { false, true } bool;

int V;
int graph[G_SIZE][G_SIZE];
int target, source;
unsigned int weights[G_SIZE];

// A utility function to find the vertex with minimum distance
// value, from the set of vertices not yet included in shortest
// path tree
int minDistance(int dist[], bool sptSet[])
{
    // Initialize min value
    int min = INT_MAX, min_index = 0;
    
    for (int v = 0; v < V; v++)
        if (sptSet[v] == false && dist[v] <= min)
            min = dist[v], min_index = v;
    
    return min_index;
}

// Function to print shortest path from source to j
// using parent array
void printPath(int parent[], int j)
{
    // Base Case : If j is source
    if (parent[j]==-1)
        return;
    
    printPath(parent, parent[j]);
    
    if (j == source) {
        printf("%d ", 0);
    }
    else if (j == 0) {
        printf("%d ", source);
    }
    else printf("%d ", j);
}

// A utility function to print the constructed distance
// array
void printSolution(int dist[], int n, int parent[], int source, int target)
{
    printf("%d\n%d ", dist[target] + weights[0], source); // TODO respawn value pls
    printPath(parent, target);
}

// Funtion that implements Dijkstra's single source shortest path
// algorithm for a graph represented using adjacency matrix
// representation
void dijkstra(int graph[G_SIZE][G_SIZE], int src)
{
    int dist[V];  // The output array. dist[i] will hold
    // the shortest distance from src to i
    
    // sptSet[i] will true if vertex i is included / in shortest
    // path tree or shortest distance from src to i is finalized
    bool sptSet[V];
    
    // Parent array to store shortest path tree
    int parent[V];
    
    // Initialize all distances as INFINITE and stpSet[] as false
    for (int i = 0; i < V; i++)
    {
        parent[0] = -1;
        dist[i] = INT_MAX;
        sptSet[i] = false;
    }
    
    // Distance of source vertex from itself is always 0
    dist[src] = 0;
    
    // Find shortest path for all vertices
    for (int count = 0; count < V-1; count++)
    {
        // Pick the minimum distance vertex from the set of
        // vertices not yet processed. u is always equal to src
        // in first iteration.
        int u = minDistance(dist, sptSet);
        
        // Mark the picked vertex as processed
        sptSet[u] = true;
        
        // Update dist value of the adjacent vertices of the
        // picked vertex.
        for (int v = 0; v < V; v++)
            
            // Update dist[v] only if is not in sptSet, there is
            // an edge from u to v, and total weight of path from
            // src to v through u is smaller than current value of
            // dist[v]
            if (!sptSet[v] && graph[u][v] &&
                // dist[u] + graph[u][v] < dist[v]) // TODO CHANGES
                dist[u] + graph[u][v] + weights[v] < dist[v])
            {
                parent[v]  = u;
                dist[v] = dist[u] + graph[u][v] + weights[v];
            }
    }
    
    // print the constructed distance array
    printSolution(dist, V, parent, source, target);
}

int main()
{
    // read vertices
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d", &V);
    
    // Node weights
    for (int i = 0; i < V; i++) {
        scanf("%d", &weights[i]);
    }
    
    // Adjacency matrix
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            scanf("%d", &graph[i][j]);
        }
    }
    
    scanf("%d %d", &source, &target);
    
    if (source != 0)
        for (int i = 0; i < V; i++) {
            if (!(i == 0 || i == source)) {
                int temp = graph[i][0];
                graph[i][0] = graph[i][source];
                graph[i][source] = temp;
                temp = graph[0][i];
                graph[0][i] = graph[source][i];
                graph[source][i] = temp;
            }
        }
    
    if (target == 0) {
        target = source;
    }
    
    dijkstra(graph, 0);
    
    return 0;
}
