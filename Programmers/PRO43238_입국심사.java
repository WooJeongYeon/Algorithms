package programmers;
// 이분탐색은 right값 설정 잘 하자!(1e19부터는 long 범위 넘어가네)
// sort 할 필요가 없구마..(어짜피 가능한지를 보는거라. 최솟값은 이분탐색 하면서 찾는거고!)
public class PRO43238_입국심사 {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 1, right = (long)1e18, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            long sum = 0;
            for(int time : times) {
                sum += mid / (long)time;
            }
            if(sum >= n) {
                right = mid - 1;
                answer = mid;
            }
            else left = mid + 1;
        }

        return answer;
    }
}
