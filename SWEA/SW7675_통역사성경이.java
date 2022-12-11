import java.util.Scanner;

// 입력받는걸 잘못했었나봐... BufferedReader에서 Scanner로 바꿔서 한 단어씩 받으니까 되네...ㅠㅠㅠㅠ
public class SW7675_통역사성경이 {
    static int TC, N, ans;
    static String[] sentenceArr, wordArr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        TC = sc.nextInt();

        for(int tc = 1 ; tc <= TC ; tc++) {
            N = sc.nextInt();
            sb.append("#").append(tc).append(" ");
            int cnt = 0;
            while(cnt < N) {
                String s = sc.next();
                boolean isEnd = false;
                char c = s.charAt(s.length() - 1);
                if(c == '!' || c == '?' || c == '.') {
                    s = s.substring(0, s.length() - 1);
                    isEnd = true;
                }
                if(s.charAt(0) >= 'A' && s.charAt(0) <= 'Z'
                        && s.matches("^([A-Z])([a-z])*$")) {
                    ans++;
                }
                if(isEnd) {
                    sb.append(ans).append(" ");
                    cnt++;
                    ans = 0;
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}