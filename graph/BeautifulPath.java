package graph;

import java.util.*;
// you are given a graph with n nodes and m directed edges. One lowercase letter is assigned to each node.
// We define a path's value as the number of most frequently visited occurring letter. For example, if
// the letter on a path are "abaca", then the value of that path is 3.Your task is to find a path whose
// value is the largest.

import graph.Dijkstra.Pair;

// Output a single integer denoting largest value. If the value is arbitarily large print -1.
public class BeautifulPath {
	
//	//static int visited[];
//	static ArrayList<Integer> list[];
//	static int letter[];
//	static int dp[][];
	
	static class Pair implements Comparable<Pair>
	{
		int v;
		int time;
		
		Pair(int v, int time)
		{
			this.v = v;
			this.time = time;
		}

		@Override
		public int compareTo(Pair o) {
			
			return this.time - o.time;
		}
	}
	
	static int solve(int v, ArrayList<Integer> list[],boolean visited[], int C, int T)
	{
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		pq.add(new Pair(1, 0));

		
		while(!pq.isEmpty())
		{
			Pair curr = pq.poll();
			
			int currv = curr.v;
			int currt = curr.time;
			
			if(visited[currv])
				continue;
			visited[currv] = true;
			
			if(currv == v)
				return currt;
			for(int n:list[currv])
			{
				if(visited[n])
					continue;
				int nt = currt + C;
				
				if(n != v && ((nt / T)&1) == 1)  // if red light
				{
					// add waiting time
					nt += ((nt / T)+1) * T - nt;
				}
				pq.add(new Pair(n, nt));
				// System.out.println(n +" "+nt);
			}
		}
		return -1;
	}
	
//	static boolean dfs(int v)
//	{
//		visited[v] = 1;
//		
//		for(int i=0;i<list[v].size();i++)
//		{
//			int n = list[v].get(i);
//			
//			if(visited[n] == 1)
//				return true;
//			
//			if(dfs(n) == true)
//				return true;
//			
//			for(int j=0;j<26;j++)
//			{
//				dp[v][j] = Math.max(dp[v][j], dp[n][j]);
//			}
//		}
//		dp[v][letter[v]]++;
//		visited[v] = 2;
//		
//		return false;
//		
//	}
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int v = sc.nextInt();
		int e = sc.nextInt();
		int T = sc.nextInt();
		int C = sc.nextInt();
		
		ArrayList<Integer> list[] = new ArrayList[v+1];
		
		for(int i=0;i<=v;i++)
		{
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<e;i++)
		{
			int u = sc.nextInt();
			int n = sc.nextInt();
			
			list[u].add(n);
			list[n].add(u);
		}

		boolean visited[] = new boolean[v+1];
		
		System.out.println(solve(v, list, visited, C,  T));
		

		
		
//		6
//		8
//		1 3
//		1 4
//		1 5
//		1 6
//		2 3
//		2 5
//		3 6
//		4 6
//		4
//		6	
//	
		
		
//		5
//		5
//		3
//		5
//		1 2
//		1 3
//		2 4
//		1 4
//		2 5
//		
		
		
//		5
//		5
//		1 2
//		1 3
//		2 4
//		1 4
//		2 5
//		3
//		5	
		
		
		
		
		
		
		
		
//		letter = new int[v+ 1];
//		
//		dp = new int[v+1][26];
//		for(int i=1;i<=v;i++)
//		{
//			letter[i] = s.charAt(i-1) - 'a';
//		}
//		
//		visited = new int[v+1];
//		for(int i=1;i<=v;i++)
//		{
//			if(visited[i] == 0)
//			{
//				if(dfs(i ) == true)
//				{
//					System.out.println(-1);
//					return;
//				}
//			}
//		}
//		
//		int ans = 0;
//		for(int i=0;i<=v;i++)
//		{
//			for(int j=0;j<26;j++)
//			{
//				ans = Math.max(ans, dp[i][j]);
//			}
//		}
////		if(ans == 5)
////			System.out.println(ans-1);
////		else
//		System.out.println(ans);
		
	}

}

// Testcase 1::
//5 4
//abaca
//1 2
//1 3
//3 4
//4 5

//Tetcase 2:
//	6 6
//	xyzabc
//	1 2
//	3 1
//	2 3
//	5 4
//	4 3
//	6 4

//Testcase 3:
//10 14
//xzyzyzyzqx
//1 2
//2 4
//3 5
//4 5
//2 6
//6 8
//6 5
//2 10
//3 9
//10 9
//4 6
//1 10
//2 8
//3 7
	
