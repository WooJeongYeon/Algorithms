package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Date : 2022.06.17
 * Level : BaekJoon Gold 3
 * URL : https://www.acmicpc.net/problem/2623
 * Method : 위상정렬(시간복잡도 V + E)
 * Help : 위상정렬 차수 0인거 여러개일 때 어떻게 판단하는지 기억이 안나서 이전 위상정렬 풀었던거 다시 봄
 */

public class BJ2623_음악프로그램 {
    static int N, M;
    static int[] cntArr;
    static LinkedList<Integer>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new LinkedList[N + 1];
        cntArr = new int[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            edges[i] = new LinkedList<>();
        }

        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine());
            int size = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            for(int j = 1 ; j < size ; j++) {
                int next = Integer.parseInt(st.nextToken());
                edges[last].offer(next);
                cntArr[next]++;
                last = next;
            }
        }

        System.out.println(makeOrder());
    }

    static String makeOrder() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;

        for(int i = 1 ; i <= N ; i++) {     // 시간복잡도 V
            if(cntArr[i] == 0) {
                queue.offer(i);
                cnt++;
            }
        }

        while(!queue.isEmpty()) {           // 시간복잡도 E
            int now = queue.poll();
            sb.append(now).append("\n");
            for(int next : edges[now]) {
                if(--cntArr[next] == 0) {
                    queue.offer(next);
                    cnt++;
                }
            }
        }

        if(cnt != N)
            return "0";
        else
            return sb.toString();
    }
}
