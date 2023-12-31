package programmers;

/*
 * Date : 2033.05.05(재)
 * Level : Programmers level 1
 * Method : 정규표현식
 * Help : 정규표현식 찾아봤다
 * Result : 정규표현식 어려우. 공부하자
 */

public class PRO72410_신규아이디추천_재 {
    public String solution(String new_id) {
        String s = new_id.toLowerCase().replaceAll("[^0-9a-z-._]?", "").replaceAll("[.]+",".").replaceAll("(^\\.)?(\\.$)?", "");
        if(s.length() == 0) s = "a";
        if(s.length() >= 16) s = s.substring(0, 15).replaceAll("(\\.$)?", "");
        while(s.length() < 3) {
            s += s.charAt(s.length() - 1);
        }

        return s;
    }
}
