import java.io.*;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        Task question = new Task();

        question.solution(in, out);
        out.close();
    }
}

class Task {
    int N, K;
    int[] lines;

    void solution(InputReader in, OutputWriter out) {
        K = in.nextInt();
        N = in.nextInt();
        lines = new int[K];

        long min = 1, max = 0;
        for (int i = 0; i < K; i++) {
            lines[i] = in.nextInt();
            max = Math.max(max, lines[i]);
        }

        long mid, ans = 0;
        while (min <= max) {
            mid = (min + max) >> 1;
            int tmp = 0;

            for (int line : lines) {
                tmp += (line/mid);
            }
            if (tmp >= N) { // 더 많이 만들었음.. --> 더 길게 가보자
                ans = Math.max(mid, ans);
                min = mid + 1;
            } else max = mid - 1;
        }
        out.print(ans);
    }
}

class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public int read() {
        if (numChars == -1) {
            throw new InputMismatchException();
        } else {
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException var2) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }
    }

    public boolean isSpaceChar(int c) {
        return c == 32 || c == 10 || c == 13 || c == 9 || c == -1;
    }

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int nextInt() {
        int c = this.read();
        while (isSpaceChar(c)) {
            c = this.read();
        }
        byte sgn = 1;
        if (c == 45) {
            sgn = -1;
            c = this.read();
        }
        int res = 0;
        while (c >= 48 && c <= 57) {
            res *= 10;
            res += c - 48;
            c = this.read();
            if (isSpaceChar(c)) {
                return res * sgn;
            }
        }
        throw new InputMismatchException();
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public void print(long i) {
        writer.print(i);
    }

    public void close() {
        writer.close();
    }
}
