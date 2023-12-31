package day0809;

import java.util.LinkedList;
import java.util.Scanner;
/*
 * Date : 210809
 */
public class SW1228_Password {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int TC = 10;			// 테스트케이스 10번
		for(int tc = 1 ; tc <= TC ; tc++) {	
			int pwLen = in.nextInt();	// 암호 길이를 저장
			LinkedList<Integer> list = new LinkedList<Integer>();	// 암호 전체를 저장할 링크드리스트
			for(int i = 0 ; i < pwLen ; i++) {		// 링크드리스트에 암호를 저장(각 암호는 숫자로 이루어짐)
				list.add(in.nextInt());
			}
			int orderNum = in.nextInt();			// 명령어 개수
			for(int i = 0 ; i < orderNum ; i++) {	// 명령어 개수만큼 반복
				in.next();							// 맨 앞의 |를 받아서 없앰
				int pos = in.nextInt();				// 해당 위치 다음에 삽입
				int num = in.nextInt();				// num개의 숫자 삽입
				for(int j = 0 ; j < num ; j++) {	// 각 암호 부분을 링크드리스트에 삽입
					list.add(pos++, in.nextInt());
				}
			}	
			System.out.print("#" + tc + " ");		// 테스트케이스 형식 출력
			for(int i = 0 ; i < 10 ; i++)			// 링크드 리스트에서 10개까지 꺼내서 출력
				System.out.print(list.poll() + " ");
			System.out.println();					// 다음 줄로
		}
	}
}
