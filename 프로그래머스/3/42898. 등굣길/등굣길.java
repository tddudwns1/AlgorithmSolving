class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        // 초기 설정
        final long DIV_VALUE = 1_000_000_007;
        
        boolean[][] isPuddle = new boolean[n + 1][m + 1];
        for (int[] now : puddles) {
            isPuddle[now[1]][now[0]] = true;
        }
        
        long[][] memo = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
                if (isPuddle[i][1]) {
                    break;
                }
                memo[i][1] = 1;
        }
        for (int i = 1; i <= m; i++) {
                if (isPuddle[1][i]) {
                    break;
                }
                memo[1][i] = 1;
        }
        
        // 탐색
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                if (isPuddle[i][j]) {
                    continue;
                }
                memo[i][j] = (memo[i - 1][j] + memo[i][j - 1]) % DIV_VALUE;
            }
        }
        
        return answer = (int) memo[n][m];
    }
}