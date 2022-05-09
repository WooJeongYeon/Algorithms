package baekjoon;

import java.util.Arrays;
/*
 * Date : 2022.05.09
 * Level : Programmers level 2
 * Method : Greedy
 */
public class PRO42885_구명보트 {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int sIdx = 0, eIdx = people.length - 1;
        while(sIdx < eIdx) {
            if(people[sIdx] + people[eIdx] <= limit) {
                sIdx++;
            }
            eIdx--;
            answer++;
        }
        if(sIdx == eIdx) answer++;
        return answer;
    }
}
