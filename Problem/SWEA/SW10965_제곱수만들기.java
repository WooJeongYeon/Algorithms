package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW10965_제곱수만들기 {
    static int TC, N, ans;
    static boolean[] isNotPrime;
    static final int SIZE = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());

        isNotPrime = new boolean[SIZE + 1];
        setPrime();
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 1;
            N = Integer.parseInt(in.readLine());

            int max = (int)Math.sqrt(N);
            for(int i = 2 ; i <= max ; i++) {   // 루트(N)까지 나눠보고
                int cnt;
                if(!isNotPrime[i]) {
                    cnt = 0;
                    while(N % i == 0) {
                        N /= i;
                        cnt++;
                    }
                    if((cnt & 1) == 1)
                        ans *= i;
                }
                if(N == 1)
                    break;
            }
            if(N > 1) // 남아있는건 소수
                ans *= N;


            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static void setPrime() {
        int max = (int)Math.sqrt(SIZE);
        for(int i = 2 ; i <= max ; i++) {
            if(!isNotPrime[i]) {
                for(int j = i * i ; j <= SIZE ; j += i)
                    isNotPrime[j] = true;
            }
        }
    }
}
