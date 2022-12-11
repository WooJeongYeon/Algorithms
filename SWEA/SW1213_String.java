import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 1바이트씩 쓴다고 생각..하려했는데 target 길이가 1000까지 갈 수 있으니까 안되겠당
public class SW1213_String {
    static int ans;
    static String target, s;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int tc = 1 ; tc <= 10 ; tc++) {
            in.readLine();
            target = in.readLine();
            s = in.readLine();
            ans = 0;
            Set<String> set = new HashSet<>();
            if(target.length() <= s.length()) {
                set.add(target);
                for(int i = 0 ; i < s.length() - target.length() + 1 ; i++) {
                    if(set.contains(s.substring(i, i + target.length())))
                        ans++;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }
}