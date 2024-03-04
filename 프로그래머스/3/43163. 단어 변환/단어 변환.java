class Solution {
    static int min = Integer.MAX_VALUE;
    static int answer = 0;
    
    public int solution(String begin, String target, String[] words) {
        // 없으면 return 0
        if (nowHasTarget(target, words)) {
            return answer;
        }
        
        // 변환 가능 table 생성
        boolean[][] canTranslate = new boolean[words.length][words.length];
        setCanTranslate(canTranslate, words);
        
        // dfs
        getMin(begin, target, words, canTranslate);
        
        return answer;
    }
    
    public static void getMin(String begin, String target, String[] words, boolean[][] canTranslate) {
        boolean[] visited = new boolean[words.length];
        int end = 0;
        for (int i = 0; i < words.length; i++){
            if (target.equals(words[i])){
                end = i;
                break;
            }
        }
        
        for (int i = 0; i < words.length; i++){
            if (compareDifferent(begin, words[i])) {
                visited[i] = true;
                dfs(1, i, end, visited, canTranslate);
                visited[i] = false;
            }
        }
    }
    
    public static void dfs(int count, int now, int end, boolean[] visited, boolean[][] canTranslate) {
        if (now == end){
            answer = min = Math.min(min, count);
            return;
        }
        
        for (int i = 0; i < visited.length; i++){
            if (visited[i]) {
                continue;
            }
            
            if (!canTranslate[now][i]) {
                continue;
            }
            
            visited[i] = true;
            dfs(count + 1, i, end, visited, canTranslate);
            visited[i] = false;
        }
    }
    
    public static boolean nowHasTarget(String target, String[] words) {
        for (String now : words) {
            if (now.equals(target)){
                return false;
            }
        }
        return true;
    }
    
    public static void setCanTranslate(boolean[][] canTranslate, String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                canTranslate[i][j] = compareDifferent(words[i], words[j]);
            }
            canTranslate[i][i] = false;
        }
    }
    
    public static boolean compareDifferent(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++){
            if (word1.charAt(i) != word2.charAt(i)){
                if (++count == 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
