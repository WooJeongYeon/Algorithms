// pop 시에 자식들끼리 비교해서 작은 거 구하고, 나온 값과 현재값을 비교하기도 하네(순서 차이인듯)
#include <stdio.h>
#define SIZE 100001
#define swap(a, b) {int tmp = a; a = b; b = tmp;}

int heap[SIZE];
int size = 0;

void print() {
    for(int i = 1 ; i <= size ; i++) {
        printf("%d ", heap[i]);
    }
    printf("\n");
}

int pop() {
    if(size == 0)
        return 0;
    int n = heap[1];
    heap[1] = heap[size];
    heap[size--] = 0;

    int idx = 1;
    while(idx * 2 <= size) {
        int target = idx;
        int left = idx * 2;
        int right = idx * 2 + 1; 
        if(heap[left] < heap[target]) {
            target = left;
        }
        if(right <= size && heap[right] < heap[target]) {
            target = right;
        }
        if(idx == target)
            break;
        swap(heap[idx], heap[target]);
        idx = target;
    }

    return n;
}

void push(int n) {
    heap[++size] = n;
    int idx = size;
    while(1 <= idx / 2 && heap[idx / 2] > heap[idx]) {
        swap(heap[idx / 2], heap[idx]);
        idx /= 2;
    }
}

int main() {
    int N, x;
    scanf("%d", &N);
    for(int i = 0 ; i < N ; i++) {
        scanf("%d", &x);
        if(x == 0) {
            printf("%d\n", pop());
        } else {
            push(x);
        }
    }
}