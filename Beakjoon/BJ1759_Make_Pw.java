package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210809
 */
public class BJ1759_Make_Pw {
	static char input[];			// 주어진 문자들
	static char result[];			// 최종 암호
	static boolean isSelected[];	// 문자가 선택되었는지를 저장
	static int L, C;				// 선택할 수, 전체 주어지는 문자 수
	static String vowels = "aeiou";	// 모음 String
	static StringBuilder sb;		// 결과를 저장할 sb
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		L = Integer.parseInt(st.nextToken());		// 값 입력받음
		C = Integer.parseInt(st.nextToken());		
		input = new char[C];						// 값 초기화
		result = new char[L];
		isSelected = new boolean[C];
		sb = new StringBuilder();
		st = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < C ; i++) {				// 문자들 입력받음
			input[i] = st.nextToken().charAt(0);
		}	
		Arrays.sort(input);							// 문자배열 오름차순 정렬(사전순 정렬을 위해)
		subset(0, 0);								// 가능한 암호들을 구함
		System.out.println(sb);						// 암호들 출력
	}
	// input의 검사 자릿수, 현재 문자를 선택한 개수
	static void subset(int idx, int cnt) {
		if(cnt == L) {		// 문자가 L개 선택되었다면
			int pos = 0;
			for(int i = 0 ; i < C ; i++) {	// 모든 input에 대해
				if(isSelected[i]) {		// 선택된 문자를
					result[pos++] = input[i];	// result에 저장
				}
			}
			int vowelNum = 0;		// 모음 개수
			for(int i = 0 ; i < L ; i++) {	// 결과에서 해당 문자가 모음이라면 개수 증가
				if(vowels.contains(result[i] + ""))
					vowelNum++;
			}
			if(vowelNum >= 1 && vowelNum <= L - 2) {	// 모음 1개 이상, L-2개 이하라면(자음 2개 이상)
				sb.append(new String(result) + "\n");	// 결과를 더함
			}
			return;	// 반환
		}
		if(idx == C) return;	// 모든 input문자들을 검사했다면 반환
		isSelected[idx] = true;	// 현재 자리를 포함시킴
		subset(idx + 1, cnt + 1);	// 선택한 개수 증가
		isSelected[idx] = false;	// 현재 자리를 불포함시킴
		subset(idx + 1, cnt);
	}
}
