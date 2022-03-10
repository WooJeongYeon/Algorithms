package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 임의의 노드 1개에서 가장 먼 노드는 node1이나 node2이다. 여기서 가장 긴 지름에 참여하는 노드 1개를 구하고 나머지 하나의 노드를 구하면 node1과 node2를 둘 다 구할 수 있다!
// 알듯 말듯
// DP 쓸 수도 있을듯?

public class BJ1967_트리의지름 {
    static int N, ans, max;
    static int[] twoMax;
    static boolean[] visited;
    static LinkedList<int[]>[] edgeList;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        edgeList = new LinkedList[N];
        for(int i = 0 ; i < N ; i++) {
            edgeList[i] = new LinkedList<>();
        }
        for(int i = 0 ; i < N - 1 ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            edgeList[s].offer(new int[]{e, w});
            edgeList[e].offer(new int[]{s, w});
        }

        for(int i = 0 ; i < N ; i++) {
            twoMax = new int[2];
            visited = new boolean[N];
            visited[i] = true;
            for(int[] arr : edgeList[i]) {
                max = 0;
                dfs(arr[0], arr[1]);
                if(max >= twoMax[0]) {
                    twoMax[1] = twoMax[0];
                    twoMax[0] = max;
                } else if(max >= twoMax[1]) {
                    twoMax[1] = max;
                }
            }
            ans = Math.max(ans, twoMax[0] + twoMax[1]);
        }

        System.out.println(ans);
    }
    static void dfs(int idx, int len) {
        visited[idx] = true;
        boolean isEnd = true;
        for(int[] arr : edgeList[idx]) {
            if(visited[arr[0]]) continue;
            isEnd = false;
            dfs(arr[0], len + arr[1]);
        }
        if(isEnd) {
            max = Math.max(max, len);
        }
    }
}
