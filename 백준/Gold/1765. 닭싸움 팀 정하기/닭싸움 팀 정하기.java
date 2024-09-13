import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer;

    static class Student {
        int friend;
        int enemy = 0;

        public Student(int friend) {
            this.friend = friend;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        answer = n;

        Student[] students = new Student[n + 1];
        for (int i = 1; i <= n; i++) {
            students[i] = new Student(i);
        }

        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char relationship = st.nextToken().charAt(0);
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if (relationship == 'F') {
                unionFriend(p, q, students);
            } else {
                unionEnemy(p, q, students);
            }
        }

        System.out.println(answer);
    }

    private static int findFriend(int friend, Student[] students) {
        if (friend == students[friend].friend) {
            return friend;
        }

        return students[friend].friend = findFriend(students[friend].friend, students);
    }

    private static void unionFriend(int p, int q, Student[] students) {
        int pf = findFriend(p, students);
        int qf = findFriend(q, students);

        if (pf == qf) {
            return;
        }

        students[qf].friend = pf;
        answer--;
     }

    private static int findEnemy(int friend, Student[] students) {
        if (friend == students[friend].friend) {
            return friend;
        }

        return students[friend].friend = findEnemy(students[friend].friend, students);
    }

    private static void unionEnemy(int p, int q, Student[] students) {
        if (students[p].enemy == 0) {
            students[p].enemy = q;
        } else {
            unionFriend(students[p].enemy, q, students);
        }

        if (students[q].enemy == 0) {
            students[q].enemy = p;
        } else {
            unionFriend(p, students[q].enemy, students);
        }

//        if (pf == qf) {
//
//        return;
//    }
//        students[qf].friend = pf;
    }
}