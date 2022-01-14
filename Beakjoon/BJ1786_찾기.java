import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1786_찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
		int[] pArr = new int[pattern.length];
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		
		// 실패함수 만들기 : KMP의 아이디어를 똑같이 적용, W에서 W를 찾는 듯한 행위를 해서...
		// i : 접미사 포인터(i=1부터 시작: 우리는 실패함수를 만드는게 목적이므로 첫글자 틀리면 0위치로 가야하므로.)
		// j : 접두사 포인터
		for(int i = 1, j = 0 ; i < pattern.length ; i++) {
			while(j != 0 && pattern[i] != pattern[j]) j = pArr[j - 1];
			if(pattern[i] == pattern[j]) pArr[i] = ++j;
		}
		// i : 텍스트 포인터
		// j : 패턴 포인터 
		for(int i = 0, j = 0 ; i < text.length ; i++) {
			while(j != 0 && text[i] != pattern[j]) j = pArr[j - 1];
			if(text[i] == pattern[j]) { //두 글자 일치
				if(j == pattern.length - 1) {  // j가 패턴의 마지막 인덱스라면 패턴 글자가 모두 일치한 상황
					cnt++;
					sb.append((i + 2 - pattern.length) + " ");
					j = pArr[j];		// 그 다음 패턴검사 하기 위해 ininin에서 inin을 찾는 경우 첫번째 inin을 찾고 그 다음 유효한 비교를 하기 위함
				} else {				// 패턴 일치 중간 과정(패턴 앞쪽 일치하며 진행중인 상황)
					j++;				
				}
			}
		}
		
		System.out.println(cnt);
		if(cnt > 0)
			System.out.println(sb);
	}
}
