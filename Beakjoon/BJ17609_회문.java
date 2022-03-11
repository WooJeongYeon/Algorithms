package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 2022.03.11
 * Level : BaekJoon Sliver 5
 * Difficulty : 중상
 * Why : 계속 틀렸었다ㅠㅠㅠ
 * URL : https://www.acmicpc.net/problem/17609
 * Method : String
 * Error1 : 왜 틀리는지 모름. -> 틀린 문자 삭제할 때 왼쪽, 오른쪽 어떤걸 삭제할지는 둘다 해봐야 함
 * Help : 에러 왜나는지 테케 찾았었다ㅠ
 * Result : 한번 문자 삭제하면 뒤에는 그냥 검증만 하면 됨(하나하나 비교할 필요 없음) -> 재귀로 또 검증하게 하자
 */
public class BJ17609_회문 {
    static int N;
    static boolean isDelete;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(in.readLine());
        for(int i = 0 ; i < N ; i++)
        {
            String s = in.readLine();
            isDelete = false;
            sb.append(checkString(s) + "\n");
        }
        System.out.print(sb.toString());
    }

    private static int checkString(String s) {
        int left = 0, right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                if(isDelete) return -1;
                isDelete = true;
                int result1 = checkString(s.substring(left + 1, right + 1));
                int result2 = checkString(s.substring(left, right));
                if(result1 == 0 || result2 == 0) return 1;
                else return 2;
            }

            left++; right--;
        }
        return 0;
    }
}
