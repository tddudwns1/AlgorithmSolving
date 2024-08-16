import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Employee {
        int boss;
        List<Integer> underlingCount = new ArrayList<>();

        public Employee(int boss) {
            this.boss = boss;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++)
            employees[i] = new Employee(Integer.parseInt(st.nextToken()));

        for (int i = n - 1; i > 0; i--) {
            if (employees[i].underlingCount.isEmpty()) {
                employees[employees[i].boss].underlingCount.add(0);
            } else {
                Collections.sort(employees[i].underlingCount, Collections.reverseOrder());
                int length = employees[i].underlingCount.size();

                int max = 0;

                for (int j = 0; j < length; j++)
                    max = Math.max(employees[i].underlingCount.get(j) + j, max);

                employees[employees[i].boss].underlingCount.add(max + 1);
            }
        }

        Collections.sort(employees[0].underlingCount, Collections.reverseOrder());
        int length = employees[0].underlingCount.size();

        int max = -1;

        for (int j = 0; j < length; j++)
            max = Math.max(employees[0].underlingCount.get(j) + j, max);

        System.out.println(max + 1);
    }
}