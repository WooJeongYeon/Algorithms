package programmers;

// 모두 같다를 비트마스킹으로 할 수 있는 방법이 없나...? 이거 잘못해서 계속 디버깅했네ㅠㅠㅠ
public class PRO17679_프렌즈4블록 {
    public int solution(int m, int n, String[] board) {
        int ans = 0;
        char[][] map = new char[m][];
        boolean[][] visited;
        for(int i = 0 ; i < m ; i++) {
            map[i] = board[i].toCharArray();
        }
        while(true) {
            boolean isGo = false;
            visited = new boolean[m][n];
            for(int i = 0 ; i < m - 1 ; i++) {
                for(int j = 0 ; j < n - 1 ; j++) {
                    if(map[i][j] == '.') continue;
                    if(map[i][j] == map[i + 1][j] && map[i][j] == map[i][j + 1] && map[i][j] == map[i + 1][j + 1]) {
                        visited[i][j] = visited[i + 1][j] = visited[i][j + 1] = visited[i + 1][j + 1] = true;
                        isGo = true;
                    }
                }
            }

            if(!isGo) break;

            for(int j = 0 ; j < n ; j++) {
                int idx = m - 1;
                for(int i = m - 1 ; i >= 0 ; i--) {
                    if(visited[i][j]) {
                        ans++;
                    } else {
                        map[idx--][j] = map[i][j];
                    }
                }
                for(int i = idx ; i >= 0 ; i--) {
                    map[i][j] = '.';
                }
            }
        }


        return ans;
    }
}
