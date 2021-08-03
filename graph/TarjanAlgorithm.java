package graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
// Tarjan Algoritm to find SCC (directed graph)
// TC: O(V+E), does dfs only one time
// Efficient than kosaraju

// Only the backedge contribute to the connected components and not the cross edges
//u -> v is a backedge if v already exists in the dfs stack
// once we find a backedge, we found a SCC as well. To mark all vertices of this SCC we use low
// value which will be same for all the vertices of a SCC
// The head of this SCC is the node which has same discovery time and low value


public class TarjanAlgorithm {
	static int time = 0;
	
	static void SCC(Graph g)
	{
		int v = g.v;
		LinkedList<Integer> adj[] = g.adj;
		int disc[] = new int[v];
		// discovery time tells at which time is this vertex first discovered or visited
		int low[] = new int[v];
		// low indicates what is the minimum discovery time vertex which can be reached using this vertex
		Arrays.fill(disc, -1);
		Arrays.fill(low, -1);
		
		// we can use them as visited array also, so need to create separate visited array
		
		boolean stackMember[] = new boolean[v];
		Stack<Integer> st = new Stack<>();
		
		for(int i=0;i<v;i++)
		{
			if(disc[i] == -1)
			{
				dfs(i, adj, low, disc, stackMember, st);
			}
		}

	}
	
	static void dfs(int v,LinkedList<Integer> adj[], int low[], int disc[], boolean stMem[], Stack<Integer> st)
	{
		disc[v] = time;
		low[v] = time;
		time++;;
		stMem[v] = true;
		st.push(v);
		
		// traverse all neighbor vertices
		Iterator<Integer> i = adj[v].iterator();
		
		while(i.hasNext())
		{
			int n = i.next();
			if(disc[n] == -1)  // not visited yet
			{
				dfs(n, adj, low, disc, stMem, st);
				// on returning back update the low value
				low[v] = Math.min(low[v], low[n]);
			}
			else if(stMem[n] == true) // if back-edge(a cycle)
			{
				// update the value
				low[v] = Math.min(low[v], disc[n]);   // IMPORTANT
			}
			// nothing to do in case of cross edge
		}
		
		int w = -1;
		if(low[v] == disc[v])    // if head node than print SCC using stack
		{
			// print until head node encounters itself
			while(w != v)
			{
				w = st.pop();
				System.out.print(w + " ");
				stMem[w] = false;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(5);
		g.addEdge(1, 0);
		g.addEdge(0, 2);
		g.addEdge(2, 1);
		g.addEdge(0, 3);
		g.addEdge(3, 4);
		SCC(g);


	}

}
