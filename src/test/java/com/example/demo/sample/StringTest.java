package com.example.demo.sample;

import org.junit.jupiter.api.Test;

import java.util.*;

public class StringTest {

    @Test
    public void test() {
        solution("ABA");
    }

    private final List<String> ans = new ArrayList<>();
    private final Set<String> check = new HashSet<>();
    boolean[] isUsed = new boolean[100];

    private void solution(String input) {
        System.out.println("input : " + input);
        char[] alphabet = input.toCharArray();
        choose(alphabet, "");
        Collections.sort(ans);
        ans.forEach(System.out::println);
    }

    private void choose(char[] alphabet, String value) {
        if (value.length() == alphabet.length) {
            if (check.contains(value)) return;
            check.add(value);
            ans.add(value);
        }
        for (int i = 0; i < alphabet.length; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                choose(alphabet, value + alphabet[i]);
                isUsed[i] = false;
            }
        }
    }
}
