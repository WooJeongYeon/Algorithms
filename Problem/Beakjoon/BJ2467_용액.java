package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 투포인터
public class BJ2467_용액 {
    static int N, a, b, le, ri, sum;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sum = Integer.MAX_VALUE;
        le = 0; ri = N - 1;
        while(le < ri) {
            int now = arr[le] + arr[ri];
            if(Math.abs(sum) > Math.abs(now)) {         // 0에 더 가까우면 답 갱신
                sum = now;
                a = arr[le];
                b = arr[ri];
            }
            if(now > 0) ri--;       // 합이 0보다 크면 0에 가까워지도록 right 포인터를 왼쪽으로 한칸
            else le++;              // 합이 0보다 작으면 0에 가까워지도록 left 포인터를 오른쪽으로 한칸
        }
        if(a > b) {     // 최댓값이 오른쪽에 오도록
            a ^= b;
            b ^= a;
            a ^= b;
        }
        System.out.println(a + " " + b);
    }
}
