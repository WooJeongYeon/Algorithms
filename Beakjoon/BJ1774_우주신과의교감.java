package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1-2, 3-4 이런 경우에 다 연결 된게 아닌데 안됨 -> 정점을 포함시키는게 아니라(Prim) 간선을 하나씩 포함시켜야 할듯(Kruskal)
// plus - kruskal로 풀어야 할듯.. 시간초과날거같은데... 하지만 시간초과 안났다!
// plus - 아냐 Prim으로도 풀 수 있어! 이미 설정되어있는 애들의 간선 길이를 0으로(최소) 놓고 prim돌리면 됨! 나는 이미 연결되어있는 애들을 다 visited 처리 해주는 걸로 해서 안됬네ㅠㅠㅠㅠ 생각을 좀 더 하자..
// -> 둘 다 풀 수 있는 문제

public class BJ1774_우주신과의교감 {
    static int N, M, cnt;
    static double ans;
    static int[] parents;
    static double[][] vertexArr;
    static PriorityQueue<int[]> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vertexArr = new double[N + 1][2];
        make();
        pq = new PriorityQueue<>((o1, o2) -> {
            double a = getLen(o1[0], o1[1]);
            double b = getLen(o2[0], o2[1]);
            if(a < b) return -1;
            else if(a == b) return 0;
            else return 1;
        });
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            vertexArr[i] = new double[]{x, y};
        }
        for(int i = 1 ; i <= N ; i++) {
            for(int j = 1 ; j <= N ; j++) {
                if(i == j) continue;
                pq.offer(new int[]{i, j});
            }
        }
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(union(start, end)) cnt++;
        }
        kruskal();
        System.out.printf("%.2f", Math.round(ans * 100) / 100.0);
    }

    private static void kruskal() {
        while(true) {
            int[] edge = pq.poll();
            if(union(edge[0], edge[1])) {
                ans += getLen(edge[0], edge[1]);
                if(++cnt == N - 1) {
                    break;
                }
            }
        }
    }

    static void make() {
        parents = new int[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            parents[i] = i;
        }
    }

    static int find(int n) {
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        else {
            parents[rootA] = rootB;
            return true;
        }
    }

    static double getLen(int aIdx, int bIdx) {
        return Math.sqrt(Math.pow(vertexArr[aIdx][0] - vertexArr[bIdx][0], 2) + Math.pow(vertexArr[aIdx][1] - vertexArr[bIdx][1], 2));
    }
}
