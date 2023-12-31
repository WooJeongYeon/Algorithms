/*
 * Date : 2021.11.15
 * Level : Programmers Level 1
 * URL : https://programmers.co.kr/learn/courses/30/lessons/68935
 * Method : 진법 변환
 * Result : 10진법 -> 3진법 -> string -> 뒤집음 -> 3진법 -> 10진법인데 string이 들어갈 필요가 없네 
 * Plus1 : String에는 reverse가 없음 -> new StringBuilder(s).reverse().toString()
 * Plus2 : reverse 안쓰고 뒤집을 수 있음! 10진법 -> 3진법 바꿀 때 s에 반대로 붙여주면 됨
 * Plus3 : 진법변환하기 Integer.parseInt(문자열, 진법) -> 주어진 진법의 숫자를 10진수 int형으로 변환해줌
 * Plus4 : 10진법을 3진법으로 만들면서 바로 뒤집어서 만든 10진법 수(답) 구하기
 * 		1. 저장된 수에 3을 곱함(3진법에서 왼쪽으로 이동하는 느낌)
 * 		2. 3으로 나머지한 수를 저장된 수에 더함
 */

public class PRO68935_3진법뒤집기 {
//	방법 1
	public int solution1(int n) { 
        int answer = 0;
        String s = "";
        
        while(n != 0) {
            s = (n % 3) + s;
            n /= 3;
        }
        
        for(int i = 0 ; i < s.length() ; i++) {
            answer += (s.charAt(i) - '0') * (int)Math.pow(3, i);
        }
        
        return answer;
    }
	
//	방법 2
	public int solution2(int n) {	// 얘가 훨씬 더 빠름
        int answer = 0;
        
        while(n != 0) {				// 3진법 수의 길이가 얼만지 모르니까 저장된 수에 계속 3 곱해주면서 10진수로 만들기
            answer *= 3;
            answer += (n % 3);
            n /= 3;
        }
        
        return answer;
    }
}
