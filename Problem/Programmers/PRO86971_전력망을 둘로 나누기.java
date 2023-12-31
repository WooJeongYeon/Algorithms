import java.util.*;
class Solution {
    boolean[] visited;
    int aSum;
    LinkedList<Integer>[] edges;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        edges = new LinkedList[n + 1];
        for(int i = 1 ; i <= n ; i++) {
            edges[i] = new LinkedList<Integer>();
        }
        for(int i = 0 ; i < wires.length ; i++) {
            edges[wires[i][0]].add(wires[i][1]);
            edges[wires[i][1]].add(wires[i][0]);
        }
        for(int i = 0 ; i < wires.length ; i++) {
            aSum = 0;
            visited = new boolean[n + 1];
            visited[wires[i][1]] = true;
            dfs(wires[i][0]);
            answer = Math.min(answer, Math.abs(n - 2 * aSum));
        }
        return answer;
    }
    void dfs(int idx) {
        aSum++;
        visited[idx] = true;
        for(Integer n : edges[idx]) {
            if(!visited[n]) dfs(n);
        }
    }
}