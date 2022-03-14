package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1, A, B, C 범위 조심
// 2. 곱셈 수가 Integer.MAX 번이라 시간초과 남 -> 제곱수를 2로 나눠가면서 곱해줌
public class BJ1629_곱셈 {
    static long A, B, C;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(multiple(A, B));
    }
    static long multiple(long x, long n) {
        if(n == 1) return x % C;
        long ans = multiple(x, n / 2);
        ans = (ans * ans) % C;
        if(n % 2 == 1) ans = (ans * x) % C;
        return ans;
    }
}
