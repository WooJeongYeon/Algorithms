package baekjoon;

/*
 * Date : 2022.06.21
 * Level : BaekJoon Gold 3
 * Difficulty : 중하
 * URL : https://www.acmicpc.net/problem/11054
 * Thinking : dp에 해당 인덱스까지의 최장길이 저장하도록
 *          - 왼쪽 -> 오른쪽 증가하는 부분수열, 오른쪽 -> 왼쪽 증가하는 부분수열 구해서 합이 가장 큰 인덱스의 경우를 답으로 선정
 * Method : LIS(Longest Increasing Subsequence : 최장 증가 부분 수열)
 * Result : 생각해내기 어려운 문제같음
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11054_가장긴바이토닉부분수열 {
    static int N, ans;
    static int[] arr, increDP, decreDP;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        arr = new int[N];
        increDP = new int[N];
        decreDP = new int[N];
        st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            int max = 0;
            for(int j = 0 ; j < i ; j++) {
                if(arr[j] < arr[i])
                    max = Math.max(max, increDP[j]);
            }
            increDP[i] = max + 1;
        }

        for(int i = N - 1 ; i >= 0 ; i--) {
            int max = 0;
            for(int j = i + 1 ; j < N ; j++) {
                if(arr[j] < arr[i])
                    max = Math.max(max, decreDP[j]);
            }
            decreDP[i] = max + 1;
        }


        for(int i = 0 ; i < N ; i++) {
            ans = Math.max(ans, increDP[i] + decreDP[i] - 1);
        }

        System.out.println(ans);
    }
}
