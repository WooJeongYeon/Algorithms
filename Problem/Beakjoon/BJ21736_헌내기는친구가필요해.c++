#include <stdio.h>
#define SIZE 601

int N, M, cnt = 0, startI, startJ;
char map[SIZE][SIZE];
int di[] = {-1, 1, 0, 0};
int dj[] = {0, 0, -1, 1};

void dfs(int i, int j) {
    for(int d = 0 ; d < 4 ; d++) {
        int ni = i + di[d];
        int nj = j + dj[d];
        if(ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] != 0 && map[ni][nj] != 'X') {
            if(map[ni][nj] == 'P')
                cnt++;
            map[ni][nj] = 0;
            dfs(ni, nj);
        }
    }
}

int main() {
    scanf("%d %d", &N, &M);
    for(int i = 0 ; i < N ; i++) {
        scanf("%s", map[i]);
        for(int j = 0 ; j < M ; j++) {
            if(map[i][j] == 'I') {
                startI = i;
                startJ = j;
            }
        }
    }

    map[startI][startJ] = 0;
    dfs(startI, startJ);

    if(cnt == 0)
        printf("TT\n");
    else
        printf("%d\n", cnt);
}