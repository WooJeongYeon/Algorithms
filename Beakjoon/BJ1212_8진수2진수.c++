#include <stdio.h>
#include <string.h>
#define SIZE 333335

int main() {
    char arr[SIZE];
    int len, n;
    bool isStart = false;

    scanf("%s", arr);
    len = strlen(arr);

    n = arr[0] - '0';
    for(int j = 2 ; j >= 0 ; j--) {
        if(!isStart && n >> j == 0)
            continue;
        isStart = true;
        printf("%d", n >> j & 1);
    }

    for(int i = 1 ; i < len ; i++) {
        n = arr[i] - '0';
        for(int j = 2 ; j >= 0 ; j--) {
            printf("%d", n >> j & 1);
        }
    }

    if(arr[0] == '0') {
        printf("0");
    }

    printf("\n");
}