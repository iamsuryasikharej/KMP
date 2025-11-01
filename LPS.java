import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.IntStream;

public class LPS {
    static String x = "aababcabcdabcdeabcdef";

    public static void main(String[] args) {
        char[] str = x.toCharArray();
        int[] lps = new int[str.length];
        char[] lpschar = new char[str.length];
        lps[0] = 0;
        lpschar[0] = str[0];

        int j = 0;

        for (int i = 1; i < str.length; i++) {
            j = lps[i - 1];
            if (str[i] == str[j]) {
                lps[i] = j + 1;
                lpschar[i] = str[i];
            } else if (j != 0) {
                while (j > 0 && str[i] != str[j]) {
                    j = lps[j - 1];
                }
            }
            if (str[i] == str[j]) {
                {
                    lps[i] = j + 1;
                    lpschar[i] = str[i];
                }
            } else {
                lps[i] = 0;
                lpschar[i] = str[i];
            }

        }
        for (int x : lps) {
            System.out.print(x);
        }

        System.out.println();

        System.out.println(lpschar);
    }

}
