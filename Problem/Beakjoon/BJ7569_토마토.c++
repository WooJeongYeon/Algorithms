// tomato 배열을 char로 생성하니까 63%인가에서 계속 틀림(왜그런거지?)
#include <stdio.h>
#include <queue>
#include <tuple>
#define SIZE 101

using namespace std;

int M, N, H, ans = -1, startCnt = 0, needCnt = 0, size = 0;
int di[] = {-1, 1, 0, 0, 0, 0};
int dj[] = {0, 0, -1, 1, 0, 0};
int dk[] = {0, 0, 0, 0, -1, 1};
queue<tuple<int, int, int>> q;
char tomato[SIZE][SIZE][SIZE];

int main() {
    scanf("%d %d %d", &M, &N, &H);
    
    for(int k = 0 ; k < H ; k++) {
        for(int i = 0 ; i < N ; i++) {  
            for(int j = 0 ; j < M ; j++) {
                scanf("%d", &tomato[i][j][k]);
                if(tomato[i][j][k] == 1) {
                    q.push(make_tuple(i, j, k));
                    startCnt++;
                } else if(tomato[i][j][k] == 0) {
                    needCnt++;
                }
            }
        }
    }

    if(startCnt > 0 && needCnt == 0) {
        printf("0\n");
    } else {
        while(!q.empty()) {
            size = q.size();
            for(int s = 0 ; s < size ; s++) {
                for(int d = 0 ; d < 6 ; d++) {
                    int ni = get<0>(q.front()) + di[d];
                    int nj = get<1>(q.front()) + dj[d];
                    int nk = get<2>(q.front()) + dk[d];
                    if(ni >= 0 && ni < N && nj >= 0 && nj < M && nk >= 0 && nk < H && tomato[ni][nj][nk] == 0) {
                        tomato[ni][nj][nk] = 1;
                        needCnt--;
                        q.push(make_tuple(ni, nj, nk));
                    }
                }
                q.pop();
            }
            ans++;
        }

        if(needCnt > 0) {
            printf("-1\n");
        } else {
            printf("%d\n", ans);
        }
    }
}