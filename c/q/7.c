#include <stdio.h>
#include <stdlib.h>

struct Student {
    char name[10];  // 姓名
    char gender;    // 性别
    int score;      // 分数
};

typedef char * STRING;

int main() {
    
    struct Student s1 = {"张二狗", 'M', 10};
    struct Student *p = &s1;
    printf("%s;%c;%d\n", p->name, p->gender, p->score);

    printf("\n");
    STRING str1 = "str1";
    puts(str1);

    // 文件操作
    FILE *fp;
    if ( (fp = fopen("./6.c", "r+")) == NULL ) {
        printf("Cannot open the file.");
        exit(1);
    }

    // 写文件
    // fputs("xxxxxxx", fp);

    // 读文件
    // char c;
    // while ( (c = fgetc(fp)) != EOF ) {
    //     putchar(c);
    // }

    char str[20];
    while ( fgets(str, 20, fp) != NULL ) {
        printf("%s", str);
    }

    fclose(fp);

    return 0;
}