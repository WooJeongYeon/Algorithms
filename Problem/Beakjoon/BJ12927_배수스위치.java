package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210825
 */
public class BJ12927_배수스위치 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	
		String s = in.readLine();
		int[] sw = new int[s.length() + 1];
		int result = 0;
		for(int i = 1 ; i <= s.length() ; i++) {		// 새로운 int 배열에
			sw[i] = s.charAt(i - 1) == 'Y' ? 1 : 0;		// 1 또는 0으로 저장
		}
		for(int i = 1 ; i < sw.length ; i++) {
			if(sw[i] == 1) {							// 만약 켜져있으면
				result++;								// 조작 횟수 증가
				for(int j = i ; j < sw.length ; j += i )	// 모든 배수에 대해
					sw[j] = 1 - sw[j];						// 스위치 반전
			}
		}
		System.out.println(result);
	}
}
