package baekjoon;

import java.io.IOException;
import java.util.Scanner;

// 맨 뒤에 0을 붙여준다 -> 틀림
// 1. 계산 어떻게 하지..? 뒤에 N의 뒷자리수들 붙여줘서 비교 -> 응 일케하면 안대~~ 근데 계산을 어케함..
// 2. 100에서 +-로 가는 게 더 빠를 수 있음
// 3. 완탐은 생각도못했는데 미치겠네..ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
// 4. 그래도 똑같이 5퍼에서 틀림 -> 한자릿수 더 큰 수에서 내려가는게 더 빠를 수 있다
// 5. 맨 앞자리가 0인 경우도 생각해야 함 -> 맨 앞 자리는 만들어서 dfs를 돌리자.
// 살기싫다
// 고치고 바로 내지 말고 생각을 하고 완전할 때 내자
// 3시간 넘게? 푼듯.. ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ

public class BJ1107_리모컨 {
    static int N, size, len, ans;
    static boolean[] isBroken;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        size = in.nextInt();
        len = (N + "").length();
        isBroken = new boolean[10];
        for(int i = 0 ; i < size ; i++) {
            isBroken[in.nextInt()] = true;
        }
        ans = Math.abs(100 - N);
        dfs(0, 0, 0);
        for(int i = 1 ; i <= len ; i++) {
            for(int j = 1 ; j < 10 ; j++) {
                if (isBroken[j]) continue;
                dfs(i - 1, (int)Math.pow(10, i) * j, 1);
            }
        }
        System.out.println(ans);
    }
    static void dfs(int idx, int num, int cnt) {
        if(idx < 0) {
            ans = Math.min(ans, Math.abs(N - num) + cnt);
            return;
        }
        for(int i = 0 ; i < 10 ; i++) {
            if(isBroken[i]) continue;
            dfs(idx - 1, (int)Math.pow(10, idx) * i + num, cnt + 1);
        }
    }
}
