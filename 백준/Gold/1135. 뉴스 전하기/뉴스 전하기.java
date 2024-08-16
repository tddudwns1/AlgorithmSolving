import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 그리디 활용
 */
public class Main {
    /**
     * 직원 클래스
     * 보스의 번호와 부하가 전파하는 최소 시간을 저장
     */
    static class Employee {
        int boss;
        List<Integer> underlingCount = new ArrayList<>();

        public Employee(int boss) {
            this.boss = boss;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 직원의 수
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 직원 정보를 기록할 배열
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++)
            // 보스 번호를 저장
            employees[i] = new Employee(Integer.parseInt(st.nextToken()));

        // 말단 직원부터 올라오며 최소 전파 시간 계산
        for (int i = n - 1; i > 0; i--)
            employees[employees[i].boss].underlingCount.add(getMinTime(employees[i]));

        System.out.println(getMinTime(employees[0]));
    }

    /**
     * 최소 전파 시간 계산 메서드
     *  1. 자신의 부하 직원들이 계산한 자신들의 최소 전파 시간 목록을 확인한다
     *  2. 가장 오래 걸리는 직원부터 전파한다
     *  3. 해당 시간과 그 직원에게 전파하는 시간을 합해 비교한다
     *  4. 그 중 가장 오래 걸리는 시간 기준으로 자신에게 전파 될 시간 + 1을 보스에게 전달한다
     * @param employees
     * @return
     */
    private static int getMinTime(Employee employees) {
        // 전파 받은 시간을 오래 걸리는 시간 순으로 정렬
        Collections.sort(employees.underlingCount, Collections.reverseOrder());

        int max = -1;

        for (int j = 0; j < employees.underlingCount.size(); j++)
            max = Math.max(employees.underlingCount.get(j) + j, max);
        
        return max + 1;
    }
}