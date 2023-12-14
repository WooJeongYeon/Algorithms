#include <stdio.h>

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
char map[50][50] = {0};
int cnt = 0;
int T, M, N, K;

void dfs(int i, int j) {
    map[i][j] = 0;
    int di, dj;
    for(int d = 0 ; d < 4 ; d++) {
        di = i + dx[d];
        dj = j + dy[d];
        if(di >= 0 && di < M && dj >= 0 && dj < N && map[di][dj] == 1) {
            dfs(di, dj);
        }
    }
}

int main() {
    int x, y;
    
    scanf("%d", &T);
    
    for(int t = 0 ; t < T ; t++) {
        cnt = 0;
        scanf("%d %d %d", &M, &N, &K);
        for(int k = 0 ; k < K ; k++) {
            scanf("%d %d", &x, &y);
            map[x][y] = 1;
        }

        for(int i = 0 ; i < M ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(map[i][j] == 1) {
                    cnt++;
                    dfs(i, j);
                }
            }
        }
        printf("%d\n", cnt);
    }

    return 0;
}