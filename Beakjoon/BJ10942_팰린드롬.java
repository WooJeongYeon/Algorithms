package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.05.05
 * Level : BaekJoon Gold 3
 * Difficulty : 하
 * Time : 20m
 * Thinking : DP 생각했다가 별 차이 없을 거 같아서 던졌었다
 * Method : 팰린드롬...
 * Result : 1000 * 백만이 10억이라 최악의 경우 시간초과 나겠다 생각했는데 안나넴...
 * Plus : 사람들 DP로 풀기도 했당
 */
public class BJ10942_팰린드롬 {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(in.readLine());
        arr = new int[N];
        st = new StringTokenizer(in.readLine());

        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(in.readLine());

        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int cnt = (e - s + 1) / 2;
            boolean isPossible = true;
            for(int j = 0 ; j < cnt ; j++) {
                if(arr[s + j] != arr[e - j]) {
                    isPossible = false;
                    break;
                }
            }
            if(isPossible) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb.toString());
    }
}
