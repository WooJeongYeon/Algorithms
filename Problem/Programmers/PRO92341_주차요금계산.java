package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 누적 주차 시간이구나... 주차할때마다 계산하는줄
// map의 EntrySet이나 KeySet으로 list 만들 수 있구만
// TreeMap은 keySet()으로 가져올 때 정렬된 상태로 가져온대 -> ArrayList 안써도 됬넹

public class PRO92341_주차요금계산 {
    public int[] solution(int[] fees, String[] records) {
        Map<String, int[]> map = new HashMap<>();
        ArrayList<String[]> list = new ArrayList<>();
        for(String s : records) {
            String[] split = s.split(" ");
            int min = toMin(split[0]);
            String num = split[1];
            if(split[2].length() == 2) {
                if(!map.containsKey(num)) map.put(num, new int[]{min, 0});
                else map.put(num, new int[]{min, map.get(num)[1]});
            } else {
                map.put(num, new int[]{-1, map.get(num)[1] + min - map.get(num)[0]});
            }
        }

        for(Map.Entry<String, int[]> entry : map.entrySet()) {
            int[] values = entry.getValue();
            if(values[0] != -1) {
                values[1] += toMin("23:59") - values[0];
            }
            list.add(new String[]{entry.getKey(), getMoney(fees, values[1]) + ""});
        }
        Collections.sort(list, (o1, o2) -> o1[0].compareTo(o2[0]));

        int[] answer = new int[list.size()];
        int idx = 0;
        for(String[] sArr : list) {
            answer[idx++] = Integer.parseInt(sArr[1]);
        }
        return answer;
    }
    int toMin(String s) {
        String[] split = s.split(":");
        int min = Integer.parseInt(split[0]) * 60;
        min += Integer.parseInt(split[1]);
        return min;
    }
    int getMoney(int[] fees, int min) {
        int money = 0;
        money += fees[1];
        min -= fees[0];
        if(min > 0) money += ((min + fees[2] - 1) / fees[2]) * fees[3];
        return money;
    }
}
