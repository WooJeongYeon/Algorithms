package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Date : 2022.06.20
 * Level : BaekJoon Sliver 2
 * URL : https://www.acmicpc.net/problem/16953
 * Thinking : Queue에 넣어서 최솟값 구하도록 하자
 * Method : BFS
 * Error1 : int 사용했다가 2 곱하면서 범위 넘어가서 틀렸었다.
 * Plus1 : B에서 A로 가는걸 하면 long말고 int를 쓸 수 있잖아
 */

public class BJ16953_AB {
    static long A, B, ans;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Queue<long[]> queue = new LinkedList<>();
        A = in.nextLong();
        B = in.nextLong();
        ans = -1;

        queue.offer(new long[]{A, 1});
        while(!queue.isEmpty()) {
            long[] now = queue.poll();
            if(now[0] == B) {
                ans = now[1];
                break;
            }
            if(now[0] * 2 <= B)
                queue.offer(new long[]{now[0] * 2, now[1] + 1});
            if(now[0] * 10 + 1 <= B)
                queue.offer(new long[]{now[0] * 10 + 1, now[1] + 1});
        }

        System.out.println(ans);
    }
}
