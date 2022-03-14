package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1157_단어공부 {
    static String s;
    static char ans;
    static int max;
    static int[] alphaNum;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        s = in.readLine().toUpperCase();
        N = 'Z' - 'A' + 1;
        alphaNum = new int[N];
        for(int i = 0 ; i < s.length() ; i++) {
            alphaNum[s.charAt(i) - 'A']++;
            if(max < alphaNum[s.charAt(i) - 'A']) {
                max = alphaNum[s.charAt(i) - 'A'];
                ans = s.charAt(i);
            }
        }
        for(int i = 0 ; i < N ; i++) {
            if(alphaNum[i] == max && ans != (i + 'A')) {
                ans = '?';
                break;
            }
        }
        System.out.println(ans);
    }
}
