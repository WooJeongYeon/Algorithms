import java.util.*;
import java.util.regex.Pattern;

public class PRO17677_뉴스클러스터링 {
	class Solution {
		public int solution(String str1, String str2) {
			double ans = 0;
			int cnt1 = 0, cnt2 = 0;
			int size1 = 0, size2 = 0;
			str1 = str1.toUpperCase();
			str2 = str2.toUpperCase();
			Map<String, Integer> map1 = new HashMap<>();
			Map<String, Integer> map2 = new HashMap<>();
			for (int i = 0; i < str1.length() - 1; i++) {
				String sub = str1.substring(i, i + 2);
				if (Pattern.matches("[A-Z]*", sub)) {
					map1.put(sub, 1 + map1.getOrDefault(sub, 0));
					size1++;
				}
			}
			for (int i = 0; i < str2.length() - 1; i++) {
				String sub = str2.substring(i, i + 2);
				if (Pattern.matches("[A-Z]*", sub)) {
					map2.put(sub, 1 + map2.getOrDefault(sub, 0));
					size2++;
				}
			}
			for (String key : map1.keySet()) {
				int a = map1.get(key);
				int b = map2.getOrDefault(key, 0);
				int min = Math.min(a, b);
				cnt1 += min;
			}
			cnt2 = size1 + size2 - cnt1;
			if (cnt2 == 0)
				ans = 1;
			else
				ans = (double) cnt1 / cnt2;
			return (int) (ans * 65536);
		}
	}
}
