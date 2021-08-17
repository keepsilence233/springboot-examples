package cn.wengzi.util;

import java.util.Random;

/**
 * 随机生成密码
 *
 * @author leizige
 */
public class RandomCipherUtil {

    private static Random random;
    private static long seed;

    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    private static int uniform(int N) {
        return random.nextInt(N);
    }

    private static int uniform(int a, int b) {
        return a + uniform(b - a);
    }

    /**
     * 随机生成密码
     *
     * @param pwd_len 密码长度
     * @return 生成后的密码
     */
    public static String getGeneratePassword(int pwd_len) {
        char[] chArr = new char[pwd_len];
        chArr[0] = (char) ('0' + uniform(0, 10));
        chArr[1] = (char) ('A' + uniform(0, 26));
        chArr[2] = (char) ('a' + uniform(0, 26));

        char[] codes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z'};

        for (int i = 3; i < pwd_len; i++) {
            chArr[i] = codes[uniform(0, codes.length)];
        }

        for (int i = 0; i < pwd_len; i++) {
            int r = i + uniform(pwd_len - i);
            char temp = chArr[i];
            chArr[i] = chArr[r];
            chArr[r] = temp;
        }

        return new String(chArr);
    }
}
