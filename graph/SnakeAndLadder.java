package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// You are at block 1 and have to reach block 100. Given ladders and snakes, what is the minimum steps 
// required to reach 100 if you can choose any number between 1 to 6.

public class SnakeAndLadder {
	// Idea is to bfs
	static void solve(int ladders[][], int snakes[][])
	{
		HashMap<Integer, Integer> ladder = new HashMap<>();
		HashMap<Integer, Integer> snake = new HashMap<>();
		// adding ladders
		for(int i=0;i<ladders.length;i++)
		{
			ladder.put(ladders[i][0], ladders[i][1]);
		}
		
		// adding snakes
		for(int i=0;i<snakes.length;i++)
		{
			ladder.put(snakes[i][0], snakes[i][1]);
		}
		
		int moves = 0;
		
		Queue<Integer> q = new LinkedList<>();
		boolean visited[] = new boolean[1001];         // IMP:
		
		q.add(1);
		visited[1] = true;
		boolean end = false;
		while(!q.isEmpty() && end == false)
		{
			int sz = q.size();
			while(sz > 0)
			{
				int t = q.poll();
				for(int die = 1;die<=6;die++)
				{
					
					if(t+die == 100)
						end = true;
					// if ladder
					if(t + die <= 100 && ladder.containsKey(t+die) && visited[ladder.get(t+die)] == false)
					{
						visited[ladder.get(t+die)] = true;
						if(ladder.get(t+die) == 100)
						{
							end = true;
						}
						q.add(ladder.get(t+die));
					}
					
					// if snake
					if(t + die <= 100 && snake.containsKey(t+die) && visited[snake.get(t+die)] == false)
					{
						visited[snake.get(t+die)] = true;
						if(snake.get(t+die) == 100)
						{
							end = true;
						}
						q.add(snake.get(t+die));
					}
					// if an empty sqaure
					if(t+die <=100 && !visited[t+die] && !snake.containsKey(t+die) && !ladder.containsKey(t+die))
					{
						q.add(t+die);
						visited[t+die] = true;
					}
				}
				
				sz--;           /// IMP:
			}
			
			moves++;
		}
		if(end)
			System.out.println(moves);
		else
			System.out.println(-1);
	}

	public static void main(String[] args) {
		int lad[][] = {
				{3, 90},
				{56, 76},
				
		};

		int snake[][] = {
				{99, 10},
				{30, 20},
				{20, 5}
		};
		
		solve(lad, snake);
	}

}
