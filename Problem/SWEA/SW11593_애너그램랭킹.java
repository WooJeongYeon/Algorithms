import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class SW11593_애너그램랭킹 {
    static int TC;
    static long ans;
    static char[] s;
    static long[] factorialArr;
    static int SIZE = 20;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        factorialArr = new long[SIZE + 1];
        factorialArr[1] = 1;
        for(int i = 2 ; i <= 20 ; i++) {
            factorialArr[i] = factorialArr[i - 1] * i;
        }
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            s = in.readLine().toCharArray();
            for(int i = 0 ; i < s.length - 1 ; i++) {
                long[] arrCnt = new long['Z' - 'A' + 1];
                Set<Character> set = new HashSet<>();
                arrCnt[s[i] - 'A']++;
                for(int j = i + 1 ; j < s.length ; j++) {
                    if(s[j] < s[i]) {
                        set.add(s[j]);
                    }
                    arrCnt[s[j] - 'A']++;
                }
                for(Character c : set) {
                    long now = factorialArr[s.length - i - 1];
                    arrCnt[c - 'A']--;
                    for(int j = 0 ; j < arrCnt.length ; j++) {
                        if(arrCnt[j] != 0)
                            now /= factorialArr[(int)arrCnt[j]];
                    }
                    arrCnt[c - 'A']++;
                    ans += now;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }
}