package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 답 봄. 완탐으로 할꺼면 백트래킹 진짜 잘해야될듯
// 아이디어 문제넴...

public class BJ1339_단어수학 {
     static int N, ans;
     static Integer[] alpha;
     static int SIZE = 10;
     public static void main(String[] args) throws IOException {
          BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
          N = Integer.parseInt(in.readLine());
          alpha = new Integer['Z' - 'A' + 1];
          Arrays.fill(alpha, 0);
          for(int i = 0 ; i < N ; i++) {
               String s = in.readLine();
               int n = 1;
               for(int j = s.length() - 1 ; j >= 0 ; j--, n *= 10) {  // 알파벳 배열에 해당하는 숫자만큼 더해줌 Ex) AAA -> 111A로 나타낼 수 있음
                    alpha[s.charAt(j) - 'A'] += n;
               }
          }

          Arrays.sort(alpha, Collections.reverseOrder());
          for(int i = 1 ; i <= SIZE ; i++) { // 큰 수 순서대로 9~0숫자를 할당해 더해줌
               if(alpha[i - 1] == 0) break;
               ans += alpha[i - 1] * (SIZE - i);
          }
          System.out.println(ans);
     }
}
