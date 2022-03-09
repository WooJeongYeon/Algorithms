package baekjoon;

import java.util.Arrays;
import java.util.Scanner;
// 에라토스테네스의 체 + 슬라이딩 윈도우
// decimalArr 배열 크기 만들어놓고 len 나중에 구할 수도 있네
// 소수 구하기 -> 시작 수는 sqrt(N)까지 진행해도 됨

public class BJ1644_소수의연속합 {
    static int N, len, sum, left, right, ans;
    static boolean[] isDecimalArr;
    static int[] decimalArr;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        isDecimalArr = new boolean[N + 1];
        Arrays.fill(isDecimalArr, true);
        for(int i = 2 ; i <= Math.sqrt(N) ; i++) {
            if(!isDecimalArr[i]) continue;  // 이미 방문했던 곳은 패스.. 왜 시간 증가했찌ㅋㅋㅋㅋㅋ
            for(int j = i * i ; j <= N ; j += i) {
                isDecimalArr[j] = false;
            }
        }

        for(int i = 2 ; i <= N ; i++) {
            if(isDecimalArr[i]) len++;
        }
        decimalArr = new int[len];
        int idx = 0;
        for(int i = 2 ; i <= N ; i++) {
            if(isDecimalArr[i])
                decimalArr[idx++] = i;
        }
        if(len > 0) sum = decimalArr[left];
        while(true) {
            if(sum <= N) {
                if(sum == N)
                    ans++;
                if(++right >= len) break;
                sum += decimalArr[right];
            }
            else sum -= decimalArr[left++];
        }
        System.out.println(ans);
    }
}
