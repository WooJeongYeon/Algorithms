package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1248_공통조상 {
    static int TC, V, E, v1, v2, ans, cnt;
    static int[][] tree;
    static int[] heights;
    static final int PARENT = 0;
    static final int LEFT = 1;
    static final int RIGHT = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            cnt = 0;
            st = new StringTokenizer(in.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            tree = new int[V + 1][3];
            heights = new int[V + 1];

            st = new StringTokenizer(in.readLine());
            for(int i = 0 ; i < E ; i++) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                if(tree[s][LEFT] == 0)
                    tree[s][LEFT] = e;
                else
                    tree[s][RIGHT] = e;
                tree[e][PARENT] = s;
            }
            setHeight(1);
            findCommonParent();
            findCnt(ans);

            sb.append("#").append(tc).append(" ").append(ans).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static void setHeight(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        int height = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0 ; i < size ; i++) {
                int now = queue.poll();
                heights[now] = height;
                if(tree[now][LEFT] != 0) {
                    queue.offer(tree[now][LEFT]);
                }
                if(tree[now][RIGHT] != 0)
                    queue.offer(tree[now][RIGHT]);
            }
            height++;
        }
    }

    private static void findCommonParent() {
        int h1 = heights[v1];
        int h2 = heights[v2];
        while(h1 < h2) {
            v2 = tree[v2][PARENT];
            h2--;
        }
        while(h1 > h2) {
            v1 = tree[v1][PARENT];
            h1--;
        }

        while(v1 != v2) {
            v1 = tree[v1][PARENT];
            v2 = tree[v2][PARENT];
        }

        ans = v1;
    }

    private static void findCnt(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        while(!queue.isEmpty()) {
            int now = queue.poll();
            cnt++;
            if(tree[now][LEFT] != 0)
                queue.offer(tree[now][LEFT]);
            if(tree[now][RIGHT] != 0)
                queue.offer(tree[now][RIGHT]);
        }
    }
}
