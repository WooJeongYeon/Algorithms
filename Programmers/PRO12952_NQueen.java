package programmers;

/*
 * Date : 2022.06.05
 * Level : Programmers level 2
 * Difficulty : 중
 * Why : 시간초과나서 찾아봄
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12952
 * Select1 : 가로세로대각선 모두 비교 VS 이전 선택한 애들 비교(선택) -> 이게 비교횟수가 더 적음!
 * Thinking : 백트레킹인데... 이차원배열 인덱스 하나씩 증가시키면서 행, 열로 바꿔서 체크하자
 *          - 유효성 체크 - 같은 열인 게 없는지, 기울기가 1이 아닌지(대각선)
 * Method : 백트레킹
 * Error1 : 시간초과남ㅠㅠㅠ 뭐가 문제지ㅠㅠㅠ
 * Help : 찾아봤다! 어짜피 한 행에 하나씩만 들어갈 수 있으니까 depth를 행으로 지정하고 열만 배열에 저장하면 됨!
 * Result : 같은 행은 비교할 필요 없음! 패스하면 된다
 */

public class PRO12952_NQueen {
    int ans, N;
    int[] cols;
    public int solution(int n) {
        ans = 0;
        N = n;
        cols = new int[N];

        dfs(0);

        return ans;
    }

    void dfs(int depth) {
        if(depth == N) {
            ans++;
            return;
        }
        for(int j = 0 ; j < N ; j++) {
            if(check(j, depth)) {
                cols[depth] = j;
                dfs(depth + 1);
            }
        }
    }

    boolean check(int j, int depth) {
        for(int n = 0 ; n < depth ; n++) {
            if(cols[n] == j
                    || Math.abs(cols[n] - j) == Math.abs(n - depth)) {
                return false;
            }
        }
        return true;
    }
}
