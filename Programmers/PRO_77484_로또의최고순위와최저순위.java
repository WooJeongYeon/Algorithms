/*
 * Date : 2021.11.01
 * Level : Programmers Level 1
 * Why : 하루 한개씩 Level1 풀면 좋겠다
 * Time : 15분
 * URL : https://programmers.co.kr/learn/courses/30/lessons/77484
 * Thinking : map쓸까 하다가 괜히 시간 더 걸릴까 해서 배열 인덱스를 로또번호로 써서 사용
 */

public class PRO_77484_로또의최고순위와최저순위 {
	public static void main(String[] args) {
		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		System.out.println(solution(lottos, win_nums));
	}
	
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        boolean[] number = new boolean[46];
        int zeroCnt = 0;
        int sameNum = 0;
        for(int i = 0 ; i < lottos.length ; i++) {
            if(lottos[i] == 0) {
                zeroCnt++;
            }
            else {
                number[lottos[i]] = true;
            }
        }
        for(int i = 0 ; i < win_nums.length ; i++) {
            if(number[win_nums[i]]) sameNum++;
        }
        answer[1] = sameNum <= 1 ? 6 : 7 - sameNum;
        answer[0] = answer[1] - zeroCnt <= 0 ? 1 : answer[1] - zeroCnt ;
        return answer;
    }
}
