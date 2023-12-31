package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210824
 */
public class BJ1759_MakePassword_2 {
	static int L, C;					// 암호길이, 알파벳개수
	static char[] alpha;				// 입력받은 알파벳
	static boolean[] select;			// 해당 알파벳이 선택되었는지를 저장
	static String vowel = "aeiou";		// 모음의 모음
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpha = new char[C];
		st = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < C ; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alpha);			// 암호가 알파벳 순이므로 오름차순 정렬
		select = new boolean[C];
		
		comb(0, 0);
		
		System.out.println(sb);
	}
	
	static void comb(int idx, int cnt) {		// 알파벳 C개 중 L개 선택
		if(cnt == L) {							// L개의 알파벳을 선택했으면
			String result = "";						
			int vowelNum = 0;
			for(int i = 0 ; i < C ; i++) {
				if(select[i]) {					// 선택된 알파벳이고
					if(vowel.contains(alpha[i] + "")) vowelNum++;		// 해당 알파벳이 모음이면 모음수 증가
					result += alpha[i];			// 선택된 알파벳으로 암호를 만듬
				}
			}
			if(vowelNum <= L - 2 && vowelNum >= 1) sb.append(result + "\n");	// 모음의 개수가 1개 ~ L - 2개이면 암호 결과에 추가
			return;
		}
		if(idx == C) return;				// 끝까지 갔으면 리턴
		select[idx] = true;					// 해당 알파벳 포함
		comb(idx + 1, cnt + 1);
		select[idx] = false;				// 해당 알파벳 포함X
		comb(idx + 1, cnt);
		
	}
}
