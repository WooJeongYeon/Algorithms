package programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/*
 * Date : 2022.05.05(재)
 * Level : Programmers level 2
 * Thinking : 이차원 배열로 만들어서 길이 오름차순으로 정렬시키고 set에 하나씩 포함시키면서 만들자
 * Method : 정규표현식, Set
 * Help : 정규표현식 찾아봤다
 * Result : 두번째 푸는거라 괜찮은듯. 정규표현식 공부좀 해보자
 * Plus1 : int배열로 변환 안하고 string배열인 arr 그대로 써도 되네...
 * Plus2 : 이거 stream으로 풀고 싶었다가 찾아보고 포기했는데 그렇게 푼 사람 있네 두줄로...
 */
public class PRO64065_튜플_재 {
    public int[] solution(String s) {
        String[] arr = s.replace("{{", "").replace("}}", "").split("\\},\\{");
        int[][] list = new int[arr.length][];
        int[] answer = new int[arr.length];
        for(int i = 0 ; i < arr.length ; i++) {
            String[] split = arr[i].split(",");
            list[i] = new int[split.length];
            for(int j = 0 ; j < split.length ; j++) {
                list[i][j] = Integer.parseInt(split[j]);
            }
        }
        Arrays.sort(list, (o1, o2) -> o1.length - o2.length);

        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < list.length ; i++) {
            for(int j = 0 ; j < list[i].length ; j++) {
                if(!set.contains(list[i][j])) {
                    set.add(list[i][j]);
                    answer[i] = list[i][j];
                }
            }
        }

        return answer;
    }
}
