
/*
 * Date : 2021.10.26
 * Level : Programmers Level 1
 * Difficulty : 생각보다 어려운데
 * Why : 문자열 연습하려고
 * Time : 1시간
 * URL : https://programmers.co.kr/learn/courses/30/lessons/72410
 * Method : 문자열 처리
 * Result : 문자열싫어... 콤마 여러개일 때 1개로 바꾸는 부분에서 좀 생각했다
 * Plus : 정규표현식이랑 replaceAll 쓰면 쉽대는디..!! ex) temp.replaceAll("[^-_.a-z0-9]","")
 */

public class PRO_72410_신규아이디추천 {
	static String possibleChar = "abcdefghijklmnopqrstuvwxyz1234567890-_.";
	public static void main(String[] args) {
		String s = "...!@BaT#*..y.abcdefghijklm";
		System.out.println(solution(s));
	}
	static String solution(String new_id) {
        String start = new_id.toString();
        String tmp1, answer;
        tmp1 = answer = "";
        start = start.toLowerCase();
        for(int i = 0 ; i < start.length() ; i++) {
        	if(possibleChar.contains(start.charAt(i) + "")) {
        		tmp1 += start.charAt(i);
        	}
        }
        int num = 0;
        for(int i = 0 ; i < tmp1.length() ; i++) {
        	if(tmp1.charAt(i) == '.') {
        		num++;
        	}
        	else {
        		if(num > 0) {
        			answer += ".";
        			num = 0;
        		}
        		answer += tmp1.charAt(i);
        	}
        }
//        if(num > 0) answer += ".";
        if(answer.indexOf('.') == 0) {
        	answer = answer.substring(1);
        }
//        if(answer.lastIndexOf('.') == answer.length() - 1) {
//        	answer = answer.substring(0, answer.length() - 1);
//        }
        if(answer == "") {
        	answer = "a";
        }
        if(answer.length() >= 16) {
        	answer = answer.substring(0, 15);
        	if(answer.lastIndexOf('.') == answer.length() - 1) {
            	answer = answer.substring(0, answer.length() - 1);
            }
        }
        if(answer.length() <= 2) {
        	char c = answer.charAt(answer.length() - 1);
        	while(answer.length() < 3) {
        		answer += c;
        	}
        }
        return answer;
    }
}
