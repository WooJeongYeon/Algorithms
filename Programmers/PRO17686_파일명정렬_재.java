package baekjoon;


import java.util.Arrays;
/*
 * Date : 2022.05.10(재)
 * Level : Programmers level 3
 * Method : 정렬
 * Result : 정규표현식으로 푸니까 이전보다 더 빠르고 깔끔하네
 */
public class PRO17686_파일명정렬_재 {
    public String[] solution(String[] files) {
        int N = files.length;
        String[] answer = new String[N];
        String[][] splitFiles = new String[N][3];
        for(int i = 0 ; i < N ; i++) {
            splitFiles[i][0] = files[i].split("[0-9]+")[0];
            splitFiles[i][1] = files[i].substring(splitFiles[i][0].length(), files[i].length()).split("[^0-9]+")[0];
            splitFiles[i][2] = files[i].substring(splitFiles[i][0].length() + splitFiles[i][1].length(), files[i].length());
        }
        Arrays.sort(splitFiles, (o1, o2) -> {
            String s1 = o1[0].toLowerCase();
            String s2 = o2[0].toLowerCase();
            if(s1.equals(s2)) {
                int i1 = Integer.parseInt(o1[1]);
                int i2 = Integer.parseInt(o2[1]);
                return i1 - i2;
            }
            return s1.compareTo(s2);
        });
        for(int i = 0 ; i < N ; i++) {
            answer[i] = splitFiles[i][0] + splitFiles[i][1] + splitFiles[i][2];
        }

        return answer;
    }
}
