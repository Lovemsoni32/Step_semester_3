package oop.assignment_problems;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L5_PlateStackOnAChain {

    public static class Node {
        public String data;
        public Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class PlateStack {
        private Node top;
        private int size;

        public PlateStack() {
            this.top = null;
            this.size = 0;
        }

        public void push(String plate) {
            Node newNode = new Node(plate);
            newNode.next = top;
            top = newNode;
            size++;
            System.out.println("push " + plate + " -> top " + top.data + ", size " + size);
        }

        public void pop() {
            if (top == null) {
                System.out.println("pop -> stack is empty");
            } else {
                String plate = top.data;
                top = top.next;
                size--;
                System.out.println("pop -> " + plate + ", size " + size);
            }
        }

        public void peek() {
            if (top == null) {
                System.out.println("peek -> stack is empty");
            } else {
                System.out.println("peek -> " + top.data);
            }
        }

        public boolean isEmpty() {
            boolean empty = (top == null);
            System.out.println("isEmpty -> " + empty);
            return empty;
        }

        public int getSize() {
            return size;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(" ");
        }

        String input = sb.toString().trim();
        if (input.isEmpty()) {
            return;
        }

        PlateStack stack = new PlateStack();

        // Pattern matches:
        // 1) push(P1), pop(), peek(), isEmpty()
        // 2) push P1, pop, peek, isEmpty
        Pattern pattern = Pattern.compile("(push|pop|peek|isEmpty)(?:\\s*\\(\\s*([^)]*)\\s*\\)|\\s+([^\\s,;]+))?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String cmd = matcher.group(1);
            String arg = null;
            if (matcher.group(2) != null && !matcher.group(2).trim().isEmpty()) {
                arg = matcher.group(2).trim();
            } else if (matcher.group(3) != null && !matcher.group(3).trim().isEmpty()) {
                arg = matcher.group(3).trim();
            }

            if (cmd.equalsIgnoreCase("push")) {
                if (arg != null) {
                    stack.push(arg);
                }
            } else if (cmd.equalsIgnoreCase("pop")) {
                stack.pop();
            } else if (cmd.equalsIgnoreCase("peek")) {
                stack.peek();
            } else if (cmd.equalsIgnoreCase("isEmpty")) {
                stack.isEmpty();
            }
        }
    }
}
