#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct student_type {
    char name[10];
    int num;
    int age;
    char sex;
};


struct student_type stud[10];

int main() {
    int i;
    FILE *fp;
    // 用只读的方式打开文件
    if ( ( fp = fopen("./34copystr.c", "rb") ) == NULL ) {
        printf("Can't open the file.\n");
        exit(1);
    }
    for (i = 1; i < 10; i += 2) {
        fseek(fp, 10L, 1);
        fread(&stud[i], 10, 1, fp);
    }

    for (i = 0; i < 10; i ++) {
        printf("%s %d %d %c", stud[i].name, stud[i].num, stud[i].age, stud[i].sex);
    }
    
    return 0;
}
