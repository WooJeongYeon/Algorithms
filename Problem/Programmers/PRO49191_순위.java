package programmers;

import java.util.LinkedList;

public class PRO49191_순위 {
    int ans, N, num;
    int[][] cntArr;
    LinkedList<Integer>[] edges;
    boolean[] visited;
    public int solution(int n, int[][] results) {
        ans = 0;
        N = n;
        edges = new LinkedList[N];
        cntArr = new int[N][2];
        for(int i = 0 ; i < N ; i++) {
            edges[i] = new LinkedList<>();
        }
        for(int i = 0 ; i < results.length ; i++) {
            edges[results[i][0] - 1].addFirst(results[i][1] - 1);
        }
        for(int i = 0 ; i < N ; i++) {
            visited = new boolean[N];
            num = 0;
            dfs(i);
            cntArr[i][1] = num;
        }

        for(int i = 0 ; i < N ; i++) {
            if(cntArr[i][0] + cntArr[i][1] == N - 1) ans++;
        }

        return ans;
    }
    void dfs(int idx) {
        visited[idx] = true;
        for(Integer node : edges[idx]) {
            if(!visited[node]) {
                dfs(node);
                cntArr[node][0]++;
                num++;
            }
        }
    }
}
