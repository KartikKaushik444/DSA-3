package graph;

import java.util.LinkedList;
import java.util.Scanner;

// euler tour simply stores inTime and outTime of every node in a dfs
// Using this inTime and outTime we can check wheter x node lies in subtree of y node or not in O(1) time 
public class EulerTour {
	
	static int time = 1;
	static void eulerTour(Graph g, int inTime[], int outTime[])
	{
		int v = g.v;
		LinkedList<Integer> adj[] = g.adj;
		
		boolean vis[] = new boolean[v];
//		int inTime[] = new int[v];
//		int outTime[] = new int[v];
		for(int i=0;i<v;i++)
		{
			if(!vis[i])
			{
				dfs(i, adj, vis, inTime, outTime);
			}
		}
		
		
		
	}
	
	static void dfs(int v, LinkedList<Integer> adj[], boolean vis[], int inTime[], int outTime[])
	{
		time++;
		inTime[v] = time;
		vis[v] = true;
		for(int n:adj[v])
		{
			if(!vis[n])
				dfs(n, adj, vis, inTime, outTime);
		}
		
		outTime[v] = time;
	}
	
	// is x subtree of y
	static boolean isSubtree(int x, int y, int inTime[], int outTime[])
	{
		if(inTime[x] > inTime[y] && outTime[x] < outTime[y])
			return true;
		else
			return false;
	}

	// oliver and game
	
	static void solve(int val, int oliver, int bob, int inTime[], int outTime[] )
	{
		// bob has to find oliver
		if(val == 0)       // moving away from mansion
		{
			System.out.println(isSubtree(oliver, bob, inTime, outTime));
		}
		if(val == 1)       // moving towards the mansion
		{
			System.out.println(isSubtree(bob, oliver, inTime, outTime));
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		Graph g = new Graph(v);
		
		int e = sc.nextInt();
		for(int i=0;i<e;i++)
		{
			g.addEdge(sc.nextInt()-1, sc.nextInt()-1);
		}
		
		int inTime[] = new int[v];
		int outTime[]  = new int[v];
		
		eulerTour(g, inTime, outTime);
		
		int q = sc.nextInt();
		
		for(int i=0;i<q;i++)
		{
			int val = sc.nextInt();
			int oliver = sc.nextInt() - 1;
			int bob = sc.nextInt() - 1;
			
			solve(val, oliver, bob, inTime, outTime);
		}
		
		sc.close();
		

	}

}


//9
//8
//1 2
//2 6
//6 9
//2 7
//7 8
//1 3
//3 4
//3 5
//5
//0 2 8
//1 2 8
//1 6 5
//0 6 5
//1 9 1
