/*
 * Date : 2022.01.19
 * Level : Programmers level 3
 * URL : https://programmers.co.kr/learn/courses/30/lessons/49189
 * Thinking : 엣지리스트에 엣지들 저장해서 BFS로 한번 방문하게
 * Method : BFS
 * Error1 : DFS로 했다가 이건 깊이탐색이라 짧은 거리를 긴 거리로 저장하고 안갈 수 있어서 BFS
 * Plus1 : 마지막의 SIZE을 답으로 했으면 됬네! 다른분꺼 보고 알았다
 */
import java.util.*;

public class PRO49189_가장먼노드 {
	class Solution {
	    boolean[] visited;
	    int[] cntArr;
	    LinkedList<Integer>[] edgeList;
	    public int solution(int n, int[][] edge) {
	        int ans = 0;
	        visited = new boolean[n];
	        cntArr = new int[n];
	        edgeList = new LinkedList[n];
	        for(int i = 0 ; i < edgeList.length ; i++) {
	            edgeList[i] = new LinkedList<Integer>();
	        }
	        for(int i = 0 ; i < edge.length ; i++) {
	            int a = edge[i][0] - 1;
	            int b = edge[i][1] - 1;
	            edgeList[a].add(b);
	            edgeList[b].add(a);
	        }
	        
	        bfs();
	            
	        for(int i = n - 1 ; i >= 0 ; i--) {
	            if(cntArr[i] != 0) {
	                ans = cntArr[i];
	                break;
	            }
	        }
	        return ans;
	    }
	    // DFS로 하면 짧은 거리가 있는데도더 긴 거리로 판단할 수 있음
	    void bfs() {
	        Queue<Integer> queue = new LinkedList<>();
	        queue.offer(0);
	        int depth = 0;
	        visited[0] = true;
	        while(!queue.isEmpty()) {
	            int size = queue.size();
	            for(int i = 0 ; i < size ; i++) {
	                int now = queue.poll();
	                boolean isPossible = false;
	                for(Integer end : edgeList[now]) {
	                    if(visited[end]) continue;
	                    isPossible = true;
	                    visited[end] = true;
	                    queue.offer(end);
	                }
	                if(!isPossible) {
	                    cntArr[depth]++;
	                }
	            } 
	            depth++;
	        }
	    }
	}
}
