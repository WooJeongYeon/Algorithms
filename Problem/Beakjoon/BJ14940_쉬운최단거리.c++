// 왜 dfs는 안될까?
// pair 처음 써봄. int[2] 넣으려니까 컴파일 에러나네ㅠ -> 클래스로 point 만들어도 될 듯
// 클래스로 만들어도 push할 때 이런식으로 넣네 - que.push({x,y,cost+1});
#include <stdio.h>
#include <queue>
#define SIZE 1001
#define INF 1001

using namespace std;

int N, M, startI, startJ, dist = 1;
int map[SIZE][SIZE] = {0};
int di[] = {-1, 1, 0, 0};
int dj[] = {0, 0, -1, 1};
queue<pair<int, int>> q;

int main() {
    scanf("%d %d", &N, &M);
    for(int i = 0 ; i < N ; i++) {
        for(int j = 0 ; j < M ; j++) {
            scanf("%d", &map[i][j]);
            if(map[i][j] == 1) {
                map[i][j] = INF;
            }
            if(map[i][j] == 2) {
                startI = i;
                startJ = j;
                map[i][j] = 0;
            }
        }
    }

    q.push(make_pair(startI, startJ));

    while(!q.empty()) {
        int size = q.size();
        for(int s = 0 ; s < size ; s++) {
            int i = q.front().first;
            int j = q.front().second;
            q.pop();
            for(int d = 0 ; d < 4 ; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                if(ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] == INF) {
                    map[ni][nj] = dist;
                    q.push(make_pair(ni, nj));
                }
            }
        }
        dist++;
    }

    for(int i = 0 ; i < N ; i++) {
        for(int j = 0 ; j < M ; j++) {
            if(map[i][j] == INF) {
                printf("-1 ");
            } else {
                printf("%d ", map[i][j]);
            }
        }
        printf("\n");
    }

    return 0;
}