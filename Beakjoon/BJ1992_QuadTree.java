package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * Date : 210818
 */
public class BJ1992_QuadTree {
	static int N;				// 영상의 길이
	static char[][] map;		// 영상
	static String result;		// 압축한 결과
	static int divideNum = 2;	// 길이 2씩 분할
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new char[N][];	
		result = "";						
		for(int i = 0 ; i < N ; i++) {				// map에 값들을 저장함
			map[i] = in.readLine().toCharArray();
		}
		compress(0, 0, N);							// 압축
		System.out.println(result);					// 결과 출력
	}
	static void compress(int si, int sj, int n) {	// 현재 영상을 압축하자
		int sum = arrTotal(si, sj, n);				// 총합을 구함
		if(sum == 0 || sum == n * n) result += map[si][sj];			// 총합이 0이면 0으로 압축되므로 결과에 0 추가, 총합이 1이면 1로 압축되므로 결과에 1 추가
		else {										// 0, 1이 섞여있다면
			result += "(";							// 괄호를 더하고
			int half = n / divideNum;
			for(int i = 0 ; i < divideNum ; i++) {	// 4개의 영역으로 나눠 압축
				for(int j = 0 ; j < divideNum ; j++) {
					compress(si + half * i, sj + half * j, half);
				}
			}
			result += ")";							// 닫는 괄호 추가해줌
		}
	}
	static int arrTotal(int si, int sj, int n) {		// 해당 영역의 총 합
		int sum = 0;									
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				sum += map[si + i][sj + j] - '0';		// 문자이므로 0의 아스키코드만큼 빼줌 -> 0 또는 1 나옴
			}
		}
		return sum;										// 총합 반환
	}
}
