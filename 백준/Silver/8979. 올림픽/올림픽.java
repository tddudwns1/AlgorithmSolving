import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Country implements Comparable<Country>{
        int num;
        int gold;
        int silver;
        int bronze;

        public Country(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country o) {
            if (gold != o.gold)
                return Integer.compare(gold, o.gold);
            if (silver != o.silver)
                return Integer.compare(silver, o.silver);
            return Integer.compare(bronze, o.bronze);
        }

        @Override
        public boolean equals(Object o) {
            return this == o;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, gold, silver, bronze);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Country[] countries = new Country[n];
        Country target = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            Country country = new Country(num, gold, silver, bronze);
            countries[i] = country;

            if (num == k)
                target = country;
        }

        Arrays.sort(countries);

        System.out.println(binarySearch(countries, target, n));
    }

    private static int binarySearch(Country[] countries, Country target, int n) {
        int left = 0;
        int right = n - 1;

        while(left < right) {
            int mid = (left + right) >> 1;

            if (countries[mid].compareTo(target) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left + 1;
    }
}