import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String str;
    static int left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            str = br.readLine();

            left = 0;
            right = str.length() - 1;

            sb.append(isPalindrome());
        }

        System.out.print(sb);
    }

    private static String isPalindrome() {
        if (checkCharacter())
            return "0\n";

        int tempLeft = left++;
        int tempRight = right;
        if (str.charAt(left) == str.charAt(right))
            if (checkCharacter())
                return "1\n";

        left = tempLeft;
        right = --tempRight;
        if (str.charAt(left) == str.charAt(right))
            if (checkCharacter())
                return "1\n";

        return "2\n";
    }

    private static boolean checkCharacter() {
        while (str.charAt(left) == str.charAt(right))
            if (++left >= --right)
                return true;
        
        return false;
    }
}