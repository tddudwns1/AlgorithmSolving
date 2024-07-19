import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 친구 네트워크 정보를 저장하기 위한 정보
 * 임의의 root node, 그 root 기준 rank(depth), 그 root 기준 size(친구 수)
 */
class Info {
    String parent;
    int size = 1;

    public Info(String parent) {
        this.parent = parent;
    }
}

public class Main {
    static Map<String, Info> infos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int f = Integer.parseInt(br.readLine());

            infos = new HashMap<>();

            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String person1 = st.nextToken();
                String person2 = st.nextToken();

                if (!infos.containsKey(person1))
                    infos.put(person1, new Info(person1));

                if (!infos.containsKey(person2))
                    infos.put(person2, new Info(person2));

                union(person1, person2);

                sb.append(infos.get(infos.get(find(person1)).parent).size).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static String find(String person) {
        Info now = infos.get(person);

        if (person.equals(now.parent))
            return person;

        return now.parent = find(now.parent);
    }

    private static void union(String person1, String person2) {
        String key1 = find(person1);
        String key2 = find(person2);

        if (key1.equals(key2))
            return;

        Info info1 = infos.get(key1);
        Info info2 = infos.get(key2);

        info1.parent = info2.parent;
        info2.size += info1.size;
    }
}