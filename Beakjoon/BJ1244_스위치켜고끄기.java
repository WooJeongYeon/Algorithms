package day0802;

import java.util.Scanner;
/*
 * Date : 2021.08.02
 * Level : BaekJoon 실버 4
 * Method : 반복문
 */
public class BJ1244_스위치켜고끄기 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int switchNum = in.nextInt();
		int[] status = new int[switchNum + 1];
		for(int i = 1 ; i <= switchNum ; i++) {
			status[i] = in.nextInt();
		}
		int studentNum = in.nextInt();
		for(int i = 0 ; i < studentNum ; i++) {
			int sex = in.nextInt();
			int pos = in.nextInt();
			if(sex == 1) {
				int index = pos;
				while(index <= switchNum) {
					status[index] = 1 - status[index];
					index += pos;
				}
			}
			else if(sex == 2) {
				int shortLen = (pos - 1) < (switchNum - pos) ? (pos - 1) : (switchNum - pos);
				int start = pos;
				int len = 1;
				for(int j = 1 ; j <= shortLen ; j++) {
					if(status[pos - j] == status[pos + j]) {
						start = pos - j;
						len += 2;
					}
					else break;
				}
				for(int j = 0 ; j < len ; j++) {
					status[start + j] = 1 - status[start + j];
				}
			}
		}
		for(int i = 1 ; i <= switchNum  ; i++) {
			System.out.print(status[i] + " ");
			if(i % 20 == 0) System.out.println();
		}
	}

}
