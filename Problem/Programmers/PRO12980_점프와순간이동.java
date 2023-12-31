import java.util.*;
/*
 * Date : 2022.01.29
 * Level : Programmers level 2
 * Difficulty : 중
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12980?language=java
 * Thinking : 처음엔 BFS, 다음엔 DP로 풀었다가 둘다 시간/메모리 초과남 
 * Method : 계산
 * Help : 몰라서 질문하기 참고함. +1이랑 *2라서 그런지 계산으로 되네ㅠㅠㅠ 거꾸로 계산할 생각을 못했음
 * Result : 다음에 다시 풀어보자
 */
public class PRO12980_점프와순간이동{
    public int solution(int n) {
        int answer = 0;
        while(n > 0) {
            answer += n % 2;
            n /= 2;
        }
        return answer;
    }
    public int solution2(int n) {
        int[] memo = new int[n + 1];
        for(int i = 1 ; i <= n ; i++) {
            if(i % 2 == 1) {
                memo[i] = memo[i - 1] + 1;
            } else {
                memo[i] = Math.min(memo[i - 1] + 1, memo[i / 2]);
            }
        }
        
        return memo[n];
    }
     public int solution3(int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> queue = new LinkedList<>();
        int answer = 0;
        queue.offer(new int[]{0, 0});
        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            if(arr[0] == n) {
                answer = arr[1];
                break;
            }
            if(arr[0] * 2 <= n && !visited[arr[0] * 2] && arr[0] * 2 != 0) {
                queue.offer(new int[]{arr[0] * 2, arr[1]});
                visited[arr[0] * 2] = true;
            }
            if(arr[0] + 1 <= n && !visited[arr[0] + 1]) {
                queue.offer(new int[]{arr[0] + 1, arr[1] + 1});
                visited[arr[0] + 1] = true;
            }
        }
        return answer;
    }
}