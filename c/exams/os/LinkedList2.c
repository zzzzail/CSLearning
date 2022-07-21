#include <stdio.h>
#include <stdlib.h>
typedef struct node {
    char c;
    struct node *next;
} ListNode;
void printListNode(ListNode *head) {
    ListNode *p = head->next;
    while (p != NULL) {
        printf("%c", p->c);
        p = p->next;
    }
    printf("\n");
}
int main() {
    ListNode l;
    l.c = '\0';
    l.next = NULL;

    int N = 100;
    char str[100] = {"abcdefghijklmnopq"};
    ListNode *cur = &l;
    for (int i = 0; i < N; ++i) {
        if (str[i] == '\0') break;
        ListNode *n = (ListNode *) malloc(sizeof(ListNode));
        n->c = str[i]; 
        n->next = NULL;
        cur->next = n;
        cur = cur->next;
    }
    printListNode(&l);

    return 0;
}