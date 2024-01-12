#include <stdio.h>
#define SIZE 128

int N;
int map[SIZE][SIZE];
int colorN[2] = {0};

void divideConquer(int si, int sj, int len) {
    if(len == 1) {
        colorN[map[si][sj]]++;
        return;
    }

    int startV = map[si][sj];
    bool isDivide = false;
    for(int i = 0 ; i < len ; i++) {
        for(int j = 0 ; j < len ; j++) {
            if(startV != map[si + i][sj + j]) {
                isDivide = true;
                break;
            }
        }
    }

    if(isDivide) {
        int half = len / 2;
        divideConquer(si, sj, half);
        divideConquer(si + half, sj, half);
        divideConquer(si, sj + half, half);
        divideConquer(si + half, sj + half, half);
    } else {
        colorN[map[si][sj]]++;
    }
}

int main() {
    scanf("%d", &N);
    for(int i = 0 ; i < N ; i++) {
        for(int j = 0 ; j < N ; j++) {
            scanf("%d", &map[i][j]);
        }
    }

    divideConquer(0, 0, N);

    printf("%d\n%d\n", colorN[0], colorN[1]);
    return 0;
}