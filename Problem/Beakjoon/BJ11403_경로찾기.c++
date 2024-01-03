// 경로가 있기만 하면 되니까 이렇게 푸네 - https://www.acmicpc.net/source/21746058
	// for (k = 0; k < n; ++k)
	// 	for (i = 0; i < n; ++i)
	// 		for (j = 0; j < n; ++j)
	// 			if (v[i][k] && v[k][j]) v[i][j] = 1;

#include <stdio.h>
#define SIZE 101
#define INF 1000

int main() {
    int N;
    int map[SIZE][SIZE];

    scanf("%d", &N);
    for(int i = 0 ; i < N ; i++) {
        for(int j = 0 ; j < N ; j++) {
            scanf("%d", &map[i][j]);
            if(map[i][j] == 0)
                map[i][j] = INF;
        }
    }

    for(int k = 0 ; k < N ; k++) {
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = map[i][j] < map[i][k] + map[k][j] ? map[i][j] : map[i][k] + map[k][j];
            }
        }
    }

    for(int i = 0 ; i < N ; i++) {
        for(int j = 0 ; j < N ; j++) {
            printf("%d ", map[i][j] < INF ? 1 : 0);
        }
        printf("\n");
    }
}