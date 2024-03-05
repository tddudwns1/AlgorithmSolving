class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {-1};
        
        int div = Math.floorDiv(s, n);
        if (div < 1){
            return answer;
        }
        answer = new int[n];
        
        int mod = Math.floorMod(s, n);
        
        for (int i = n - 1; i >= n - mod; i--) {
            answer[i] = div + 1;
        }
        
        for (int i = n - mod - 1; i >= 0; i--) {
            answer[i] = div;
        }
        
        return answer;
    }
}