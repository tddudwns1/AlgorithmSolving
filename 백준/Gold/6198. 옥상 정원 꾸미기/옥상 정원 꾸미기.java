import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 스택
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 사실상 실 스택을 사용하지 않아도 가능하다
        // add pop 생략 가능
        // 속도 줄이는 키 포인트
        int[] stack = new int[n + 1];
        // 정답으로 출력할 카운팅 변수
        long count = 0;
        // 현재 스택의 커서를 나타내는 수
        // size 대체 가능
        int top = 0;

        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(br.readLine());

            // 만약 다음 건물이 현재 건물보다 크거나 같다면 제외
            // 기존의 stack.size() > 0 && stack.peek() <= now 대체
            while (top > 0 && stack[top] <= now)
                top--;

            // 현재 높이만큼 더함 (추가되는 건물을 보는 건물은 남은 건물들 만큼임)
            count += top;
            // 다음 위치에 건물 정보 저장
            stack[++top] = now;
        }

        System.out.println(count);
    }
}