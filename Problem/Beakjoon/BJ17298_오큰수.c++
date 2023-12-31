#include <stdio.h>
#define SIZE 1000001

int main() {
    int N, top = 0;
    int arr[SIZE];
    int stack[SIZE];

    scanf("%d", &N);
    for(int i = 0 ; i < N ; i++) {
        scanf("%d", &arr[i]);
    }

    stack[top++] = 0;

    for(int i = 1 ; i < N ; i++) {
        while(true) {
            if(top > 0 && arr[stack[top - 1]] < arr[i]) {
                arr[stack[--top]] = arr[i];
            } else {
                stack[top++] = i;
                break;
            }
        }
    }

    while(top > 0) {
        arr[stack[--top]] = -1;
    }

    for(int i = 0 ; i < N ; i++) {
        printf("%d ", arr[i]);
    }

    return 0;
}