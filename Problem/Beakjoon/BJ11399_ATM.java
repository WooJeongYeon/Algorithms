package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * Date : 2022.06.07
 * Level : BaekJoon Silver 4
 * Method : Greedy
 */
public class BJ11399_ATM {
    static long ans;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < N ; i++) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        int idx = 0;
        while(!pq.isEmpty()) {
            ans += (long) pq.poll() * (N - idx++);
        }

        System.out.println(ans);
    }
}
