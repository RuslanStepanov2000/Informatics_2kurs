package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Queue1();
        Stack1();
    }

    static void Queue1() {
        int N = 5457123;
        int N1 = N;
        LinkedList queue = new LinkedList<Integer>();
        LinkedList queueRev = new LinkedList<Integer>();

        for (int i = 0; i < 7; i++) {// Количество цифр в числt
            queue.add(N % 10);
            N = ((N - N % 10) / 10);
            queueRev.add(N1 / (int) Math.pow(10, 6 - i));
            N1 = (N1 % (int) Math.pow(10, 6 - i));


        }
        System.out.println("Queue " + queue);
        System.out.println("QueueRev " + queueRev);

    }

    static void Stack1() {
        int N = 5457123;
        int N1 = N;
        Stack stack = new Stack();
        Stack stackRev = new Stack();

        for (int i = 0; i < 7; i++) {
            stackRev.add(N % 10);
            N = ((N - N % 10) / 10);
            stack.add(N1 / (int) Math.pow(10, 6 - i));
            N1 = (N1 % (int) Math.pow(10, 6 - i));
        }
        System.out.println("ReveseStack" + stackRev);
        System.out.println("Stack" + stack);
    }


}
