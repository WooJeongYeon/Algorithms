package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1267_작업순서 {
    static int V, E;
    static int[] cntArr;
    static Queue<Integer> queue;
    static LinkedList<Integer>[] edgeList;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1 ; tc <= 10 ; tc++) {
            sb.append("#").append(tc).append(" ");

            st = new StringTokenizer(in.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            queue = new LinkedList<>();
            cntArr = new int[V + 1];
            edgeList = new LinkedList[V + 1];
            for(int i = 1 ; i <= V ; i++) {
                edgeList[i] = new LinkedList<>();
            }

            st = new StringTokenizer(in.readLine());
            for(int i = 0 ; i < E ; i++) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                edgeList[s].offer(e);
                cntArr[e]++;
            }

            for(int i = 1 ; i <= V ; i++) {
                if(cntArr[i] == 0)
                    queue.offer(i);
            }

            while(!queue.isEmpty()) {
                int now = queue.poll();
                sb.append(now).append(" ");

                for(int next : edgeList[now]) {
                    cntArr[next]--;
                    if(cntArr[next] == 0)
                        queue.offer(next);
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}