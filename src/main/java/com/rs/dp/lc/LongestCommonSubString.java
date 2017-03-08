package com.rs.dp.lc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Ravi Sharma on 3/7/2017.
 */
public class LongestCommonSubString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("dp/lc/LongestCommonSubString-Input.txt"));
        String S = sc.next();
        String T = sc.next();
        Set<String> set = AllCommonSubStr(S, T);
        System.out.println(set);
        int longest = LengthLongestCommonSubStr(S, T);
        System.out.println("Length of Longest Common String --> " + longest);
        set = LongestCommonSubStr(S, T);
        System.out.println("Longest Common Strings are --> " + set);
    }

    public static Set<String> LongestCommonSubStr(String S, String T) {
        if (S == null || S.length() == 0) return Collections.EMPTY_SET;
        if (T == null || T.length() == 0) return Collections.EMPTY_SET;
        int z = 0;
        Set<String> set = new HashSet<>();
        int[][] L = new int[S.length()][T.length()];
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    if (i == 0 || j == 0)
                        L[i][j] = 1;
                    else
                        L[i][j] = L[i - 1][j - 1] + 1;
                    if (L[i][j] > z) {
                        z = L[i][j];
                        set.clear();
                        set.add(S.substring(i - z + 1, i + 1));
                    } else {
                        if (L[i][j] == z)
                            set.add(S.substring(i - z + 1, i + 1));
                    }
                } else {
                    L[i][j] = 0;
                }
            }
        }
        return set;
    }


    public static int LengthLongestCommonSubStr(String S, String T) {
        if (S == null || S.length() == 0) return 0;
        if (T == null || T.length() == 0) return 0;
        int z = 0;
        int[][] L = new int[S.length()][T.length()];
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    if (i == 0 || j == 0)
                        L[i][j] = 1;
                    else
                        L[i][j] = L[i - 1][j - 1] + 1;
                    if (L[i][j] > z) {
                        z = L[i][j];

                    }
                } else {
                    L[i][j] = 0;
                }
            }
        }
        return z;
    }

    public static Set<String> AllCommonSubStr(String S, String T) {
        if (S == null || S.length() == 0) return Collections.EMPTY_SET;
        if (T == null || T.length() == 0) return Collections.EMPTY_SET;
        int z = 0;
        Set<String> ret = new HashSet<>();
        int[][] L = new int[S.length()][T.length()];
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    if (i == 0 || j == 0)
                        L[i][j] = 1;
                    else
                        L[i][j] = L[i - 1][j - 1] + 1;
                    if (L[i][j] > z) {
                        z = L[i][j];
                        ret.add(S.substring(i - z + 1, i + 1));
                    } else {
                        if (L[i][j] == z)
                            ret.add(S.substring(i - z + 1, i + 1));
                    }
                } else {
                    L[i][j] = 0;
                }
            }
        }
        return ret;
    }

}
