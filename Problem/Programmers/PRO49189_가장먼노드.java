/*
 * Date : 2022.01.19
 * Level : Programmers level 3
 * URL : https://programmers.co.kr/learn/courses/30/lessons/49189
 * Thinking : ��������Ʈ�� ������ �����ؼ� BFS�� �ѹ� �湮�ϰ�
 * Method : BFS
 * Error1 : DFS�� �ߴٰ� �̰� ����Ž���̶� ª�� �Ÿ��� �� �Ÿ��� �����ϰ� �Ȱ� �� �־ BFS
 * Plus1 : �������� SIZE�� ������ ������ ���! �ٸ��в� ���� �˾Ҵ�
 */
import java.util.*;

public class PRO49189_����ճ�� {
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
	    // DFS�� �ϸ� ª�� �Ÿ��� �ִµ����� �� �Ÿ��� �Ǵ��� �� ����
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
