/*
 * Date : 2022.04.08
 * Level : Programmers level 2
 * URL : https://programmers.co.kr/learn/courses/30/lessons/70129
 * Thinking : 매번 변환할 필요 없이 비트로 풀면 되겠다
 * Method : Bitmasking
 * Result : String보다 비트로 푸는 게 훨씬 빠름. 오랜만에 문제 풀었다ㅠㅠㅠ 매일 풀자...!!! 아침 저녁으로!
 * Plus1 : 다른 사람들은 String으로 많이 풀었더라. s = Integer.toBinaryString(temp); 이렇게 비트를 string으로 바꿔주는 게 있네.
 */
public class PRO70129_이진변환반복하기 {
	public int[] solution(String s) {
		int[] answer = new int[2];
		int changeCnt = 0, zeroCnt = 0, num = 0, digit = 0, nowCnt = 0;
		for(int i = 0 ; i < s.length() ; i++) {
			if(s.charAt(i) == '1') num++;
		}
		if(!s.equals("1")) changeCnt++;
		zeroCnt = s.length() - num;
		while(num != 1) {
			nowCnt = 0;
			digit = 0;
			while(num >= (1 << digit)) {
				if(((num >> digit) & 1) == 1) nowCnt++;
				digit++;
			}
			zeroCnt += digit - nowCnt;
			num = nowCnt;
			changeCnt++;
		}
		answer[0] = changeCnt;
		answer[1] = zeroCnt;
		return answer;
	}
}
