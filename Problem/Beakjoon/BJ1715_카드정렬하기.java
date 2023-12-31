package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * Date : 2022.03.18
 * Level : BaekJoon Gold 4
 * Difficulty : 중
 * Why : 저번에 못풀어서 다시 보니까 풀린다
 * Time : 다시 볼 때는 금방 풀었음
 * URL : https://www.acmicpc.net/problem/1715
 * Select1 :
 * Thinking : 현재 있는 애들 중 가장 작은 두 묶음을 더하는 게 가장 최선의 방법
 * Method : Priority Queue
 * Result : 처음 풀 때 제대로 생각도 안하고 풀어서 틀렸는데 생각하는 시간을 가져보자
 */

public class BJ1715_카드정렬하기 {
    static int N;
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Long> pq = new PriorityQueue<>();
        N = Integer.parseInt(in.readLine());
        for(int i = 0 ; i < N ; i++) {
            pq.offer(Long.parseLong(in.readLine()));
        }

        while(pq.size() > 1) {                  // 한개 될 때까지 반복
            long x = pq.poll() + pq.poll();     // 가장 작은 거 2개 -> 뽑아서 합치고 pq에 넣고
            ans += x;
            pq.offer(x);

        }

        System.out.println(ans);
    }
}
