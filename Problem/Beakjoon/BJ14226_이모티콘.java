package baekjoon;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
// 
// 한 곳에 있을 때 클립보드에 저장되어 있는게 다를 수 있음
/*
 * Date : 2022.02.13
 * Level : BaekJoon Gold 5
 * URL : https://www.acmicpc.net/problem/14226
 * Thinking : PQ 사용 - 가장 시간 빠른 애들부터 처리하려고
 * Method : PQ(BFS)
 * Error1 : 복사한 거 여러번 쓸수있네
 * Error2 : 한 곳에 있을 때 클립보드에 저장되어 있는게 다를 수 있음 -> 2차원 배열
 * Result : 단순한 BFS로 알았어서 계속 틀려서 찾는데 좀 오래걸림
 */
public class BJ14226_이모티콘 {
    static int S, ans;
    static boolean[][] visited;
    static final int N = 2000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        visited = new boolean[N][N / 2];

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        visited[1][0] = true;
        pq.offer(new int[]{0, 1, 0});
        while(!pq.isEmpty()) {
            int[] arr = pq.poll();
            if(arr[1] == S) {
                ans = arr[0];
                break;
            }
            int n = arr[1] - 1;
            if(n > 0 && !visited[n][arr[2]]) {
                visited[n][arr[2]] = true;
                pq.offer(new int[]{arr[0] + 1, n, arr[2]});
            }
            n = arr[1] + arr[2];
            if(n < N && !visited[n][arr[2]]) {
                visited[n][arr[2]] = true;
                pq.offer(new int[]{arr[0] + 1, n, arr[2]});
            }
            n = arr[1] * 2;
            if(n < N && !visited[n][arr[1]]) {
                visited[n][arr[1]] = true;
                pq.offer(new int[]{arr[0] + 2, n, arr[1]});
            }
        }
        System.out.println(ans);
    }
}
