import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();

        System.out.println(kmp(s, p, getPi(p)));
    }

    private static int[] getPi(String p) {
        int[] pi = new int[p.length()];
        int j = 0;
        for (int i = 1; i < p.length(); i++) {
            while (true) {
                if (j == 0) break;
                if (p.charAt(i) == p.charAt(j)) break;
                j = pi[--j];
            }
            if (p.charAt(i) == p.charAt(j)) pi[i] = ++j;
        }

        return pi;
    }

    private static int kmp(String s, String p, int[] pi) {
        int j = 0;
        int end = p.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            while (true) {
                if (j == 0) break;
                if (s.charAt(i) == p.charAt(j)) break;
                j = pi[--j];
            }
            if (s.charAt(i) != p.charAt(j)) continue;
            if (j == end)
                return 1;
            j++;
        }

        return 0;
    }
}