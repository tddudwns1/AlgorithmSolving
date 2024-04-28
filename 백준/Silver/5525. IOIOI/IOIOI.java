import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int s = Integer.parseInt(br.readLine());
        String str = br.readLine() + " ";

        int index = 0;
        int count = 0;
        portal:
        while (index < s) {
            if (str.charAt(index++) != 'I')
                continue;

            for (int j = 0; j < n - 1; j++) {
                if (str.charAt(index) != 'O')
                    continue portal;

                index++;

                if (str.charAt(index++) != 'I')
                    continue portal;
            }

            while (true) {
                if (str.charAt(index) != 'O')
                    continue portal;

                index++;

                if (str.charAt(index++) != 'I')
                    continue portal;

                count++;
            }
        }

        System.out.println(count);
    }
}