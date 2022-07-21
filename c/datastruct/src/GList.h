typedef char DataType;

// 标记位枚举，atom=0表示原子；list=1表示表结点
typedef enum {
    atom, list
} NodeTag;

// 广义表的结点
typedef struct GLNode {
    NodeTag tag; // 标记位，用以区分原子和表结点
    union {
        DataType data;          // 数据
        struct GLNode *slink;  // 子表的指针
    };
    struct GLNode *link;       // 下一个结点的指针
} GLNode;

// 广义表
typedef GLNode *GList;

// 建立广义表的存储结构
GList CreateGList(GList GL);

void CreateGListTest();

// 打印广义表
void PrintGList(GList GL);

// 查找广义表中的数据
void FindGListX(GList GL, DataType x, int *mark);

// 求广义表的表头
GList head(GList GL);

// 求广义表的表尾
GList tail(GList GL);

// 求广义表的深度
void depth(GList GL, int *maxdh);
