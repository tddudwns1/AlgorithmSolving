class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    static void dfs(int[] numbers, int target, int dep, int now){
        if(dep == numbers.length){
            if(target == now)
            answer++;
            return;
        }
        
        dfs(numbers, target, dep + 1, now + numbers[dep]);
        dfs(numbers, target, dep + 1, now - numbers[dep]);
    }
}