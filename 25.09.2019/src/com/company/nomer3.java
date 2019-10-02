package com.company;

import java.util.Scanner;
import java.util.Stack;

public class nomer3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean stat = true;
        Stack<Character> stack = new Stack<>();
        stack.push(' ');
        System.out.print("Enter string: ");
        StringBuilder str = new StringBuilder(scan.nextLine());

        int res = 1;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    stack.push(str.charAt(i));
                    break;
                case ')':
                    if ((stack.peek() != '(') || stack.empty()) {
                        stat = false;
                        break;
                    } else stack.pop();
                    break;
                case ']':
                    if ((stack.peek() != '[') || stack.empty()) {
                        stat = false;
                        break;
                    } else stack.pop();
                    break;
                case '}':
                    if ((stack.peek() != '{') || stack.empty()) {
                        stat = false;
                        break;
                    } else stack.pop();
                    break;
            }
            if (!stat) {
                res = 0;
                break;
            }
        }
        stack.pop();
        if (!stack.empty())
        System.out.print("Несоответствие скобок"); else System.out.println("Скобки расставлены правильно");
        }
    }
