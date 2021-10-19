package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210817
 */
public class BJ2941_CroatiaAlphabet {
	static String[] alphabets;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		alphabets = new String[]{"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};	// 크로아티아 알파벳 저장
		int ans = 0;	// 글자 수
		int i = 0;		// 입력받은 String의 i번째 글자를 검사
		while(i < s.length()) {		// i를 모두 검사할 때까지 반복
			boolean isCroatia = false;	
			for(int j = 0 ; j < alphabets.length ; j++) {	// 크로아티아 알파벳 수만큼 반복
				int aLen = alphabets[j].length();			// 해당 알파벳의 길이
				if(i + aLen > s.length()) continue;			// i와 길이를 합한 것이 배열을 넘어가면 아니므로 continue
				if(alphabets[j].equals(s.substring(i, i + aLen))) {	// i부터 길이만큼의 substring을 가져와 해당 알파벳과 비교해 같다면
					isCroatia = true;						// 크로아티아 알파벳임을 표시
					i += aLen;								// i는 해당 알파벳 길이만큼 증가
					break;
				}
			}
			if(!isCroatia) 			// 크로아티아 알파벳이 아니면
				i++;				// 다음 글자 검사
			ans++;				// 글자 수 1개 증가
		}
		System.out.println(ans);
	}
}
