#include <stdio.h>
#define SIZE 25000001

int main() {
    int N, K, front = 1, back;
    char arr[SIZE];
    scanf("%d %d", &N, &K);
    back = N + 1;
    
    for(int i = 1 ; i <= N ; i++) {
        arr[i] = i;
    }

    printf("<");
    for(int i = 0 ; i < N - 1 ; i++) {
        for(int j = 0 ; j < K - 1 ; j++) {
            arr[back++] = arr[front++];

        }
        printf("%d, ", arr[front++]);
    }
    printf("%d>", arr[front]);

    return 0;
}