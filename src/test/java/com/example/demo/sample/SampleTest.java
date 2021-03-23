package com.example.demo.sample;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Stack;

public class SampleTest {

    @Test
    void test() {
        solution(4, 2);
    }

    private Stack<Integer> st = new Stack<>();
    private boolean[] isUsed = new boolean[100];

    private void solution(int N, int M) {
        choose(1, N, M);
    }

    private void choose(int start, int N, int M) {
        if (st.size() >= M) {
            st.forEach(System.out::print);
            System.out.println("");
            return;
        }

        for (int i = start; i <= N; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                st.add(i);
                choose(i + 1, N, M);
                st.pop();
                isUsed[i] = false;
            }
        }
    }

    @Test
    void name() throws IOException {
        System.out.printf(check("1234"));
    }

    private String check(String input) {
        String f = "false";
        String t = "true";

        if (input.length() == 4 || input.length() == 6) {
            try {
                Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return f;
            }
            return t;
        }
        return f;
    }
}

