package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210810
 */
public class BJ8958_OX_Quiz {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {			
			String s = in.readLine();			// 해당 문자열 저장
			int num = 0;						// 연속된 0의 개수(현재 저장할 수 있는 점수)
			int score = 0;						// 총 점수
			for(int i = 0 ; i < s.length() ; i++) {	// 각 문자열의 문자에 대해
				if(s.charAt(i) == 'O') {			// O이면
					num++;							// 연속된 0의 개수 증가
					score += num;					// 점수에 더함
				}
				else num = 0;						// X이면 0으로 저장
			}
			System.out.println(score);			
		}
	}
}
