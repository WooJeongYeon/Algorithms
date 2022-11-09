package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SW1266_소수완제품확률 {
    static int TC, allCase;
    static double sucA, sucB, failA, failB, ansA, ansB, ans;
    static final int N = 18;
    static int[] notPrime = {1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
    static int[] cntArr;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        cntArr = new int[N + 1];
        set = new HashSet<>();
        allCase = (1 << N);
        for(int i = 0 ; i < notPrime.length ; i++) {
            set.add(notPrime[i]);
        }

        subset(0, 0);

        System.out.println(allCase);
        System.out.println(Arrays.toString(cntArr));

        for(int tc = 1 ; tc <= TC ; tc++) {
            System.out.println(tc + " -----------------------");
            ans = 0.0;
            ansA = 0.0;
            ansB = 0.0;
            st = new StringTokenizer(in.readLine());
            sucA = Integer.parseInt(st.nextToken());
            failA = 100 - sucA;
            sucB = Integer.parseInt(st.nextToken());
            failB = 100 - sucB;
            sucA /= 100;
            failA /= 100;
            sucB /= 100;
            failB /= 100;
            System.out.println(sucA + " " + failA);
            System.out.println(sucB + " " + failB);

            for(int i = 0 ; i < notPrime.length ; i++) {
                int successCnt = notPrime[i];
                int failCnt = N - notPrime[i];
                double resultA = 1.0, resultB = 1.0;
                for(int j = 0 ; j < successCnt ; j++) {
                    resultA *= sucA;
                    resultB *= sucB;
                }
                for(int j = 0 ; j < failCnt ; j++) {
                    resultA *= failA;
                    resultB *= failB;
                }
                System.out.println(i + " : " + resultA + " " + resultB);

                ansA += (resultA * cntArr[notPrime[i]]) / allCase;
                ansB += (resultB * cntArr[notPrime[i]]) / allCase;
            }

            ans = ansA * ansB;

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static void subset(int cnt, int idx) {
        if(idx == N) {
            if(set.contains(cnt)) {
                cntArr[cnt]++;
            }
            return;
        }
        subset(cnt, idx + 1);
        subset(cnt + 1, idx + 1);
    }


}
