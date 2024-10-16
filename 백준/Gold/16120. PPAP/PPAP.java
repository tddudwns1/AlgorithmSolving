import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(process(str));
    }

    private static String process(String str) {
        int limit = str.length();

        if (limit % 3 != 1)
            return "NP";

        int count = limit / 3 - 1;
        StringBuilder sb = new StringBuilder(str);

        int pCount = 2;
        int index = 0;

        String PPAP = "PPAP";
        String NP = "NP";
        char p = 'P';
        char a = 'A';

        if (str.equals("P"))
            return PPAP;
        if (str.charAt(index++) != p)
            return NP;
        if (str.charAt(index++) != p)
            return NP;

        while (index < limit - 1) {
            char now = str.charAt(index++);
            if (now == a) {

                if (str.charAt(index++) == a)
                    return NP;
                if (--pCount < 1)
                    return NP;
                if (--count < 0)
                    return PPAP;
            } else {
                pCount++;
            }
        }

        return NP;
    }
}