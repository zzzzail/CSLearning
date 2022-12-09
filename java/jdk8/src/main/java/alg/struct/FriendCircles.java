package alg.struct;

/**
 * 朋友圈
 */
public class FriendCircles {
    
    public static void main(String[] args) {
    
    }
    
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }
    
}
