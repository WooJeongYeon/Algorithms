#include <string.h>
#include <stdio.h>

typedef struct Stack {
    int top = 0;
    int arr[10001];
}Stack;

void push(Stack *s, int x) {
    s->arr[s->top++] = x; 
}

int pop(Stack *s) {
    if((s->top) == 0)
        return -1;
    else {
        s->top--;
        return s->arr[s->top];
    }
}

int size(Stack *s) {
    return s->top;
}

int top(Stack *s) {
    if((s->top) == 0)
        return -1;
    else
        return s->arr[(s->top) - 1];
}

int empty(Stack *s) {
    return (s->top) == 0;
}

int main() {
    int N;
    struct Stack stack;
    scanf("%d", &N);
    for(int i = 0 ; i < N ; i++) {
        char str[10];
        int x;
        scanf("%s", str);
        if(strcmp(str, "push") == 0) {
            scanf("%d\n", &x);
            push(&stack, x);
        } else if(strcmp(str, "top") == 0) {
            printf("%d\n", top(&stack));
        } else if(strcmp(str, "size") == 0) {
            printf("%d\n", size(&stack));
        } else if(strcmp(str, "empty") == 0) {
            printf("%d\n", empty(&stack));
        } else if(strcmp(str, "pop") == 0) {
            printf("%d\n", pop(&stack));
        }
    }
}