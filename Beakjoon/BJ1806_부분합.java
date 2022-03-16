package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1806_부분합 {
    static int N, S, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0, end = 0, sum = arr[start];
        ans = Integer.MAX_VALUE;
        while(true) {
            if(sum < S) {
                if(++end >= N) break;   // 1. end가 범위에서 나갈 경우에 끝내면 됨. start > end되는 경우는 음수라 start <= end로 돌아옴
                sum += arr[end];
            } else {
                ans = Math.min(ans, (end - start) + 1); // 2. S보다 같거나 크기만 하면 무조건 길이 작은걸로 업데이트
                sum -= arr[start++];
            }
        }
        if(ans == Integer.MAX_VALUE) ans = 0;
        System.out.println(ans);
    }
}
