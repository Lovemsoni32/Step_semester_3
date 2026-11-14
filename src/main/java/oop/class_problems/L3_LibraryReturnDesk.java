package oop.class_problems;

import java.util.Scanner;

public class L3_LibraryReturnDesk {

    static class BookStack {
        private String[] items = new String[3];
        private int top = -1;

        public boolean isFull() {
            return top == 2;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(String book) {
            if (isFull()) {
                System.out.println(book + " rejected: pile is full");
            } else {
                items[++top] = book;
                System.out.println(book + " placed on pile");
            }
        }

        public void pop() {
            if (isEmpty()) {
                System.out.println("pile is empty");
            } else {
                String book = items[top--];
                System.out.println("shelved " + book);
            }
        }
    }

    static class StudentQueue {
        private String[] items = new String[3];
        private int front = 0;
        private int rear = 0;
        private int size = 0;

        public boolean isFull() {
            return size == 3;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void enqueue(String student) {
            if (isFull()) {
                System.out.println(student + " rejected: line is full");
            } else {
                items[rear] = student;
                rear = (rear + 1) % 3;
                size++;
                System.out.println(student + " joined the line");
            }
        }

        public void dequeue() {
            if (isEmpty()) {
                System.out.println("no one waiting");
            } else {
                String student = items[front];
                front = (front + 1) % 3;
                size--;
                System.out.println("served " + student);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookStack stack = new BookStack();
        StudentQueue queue = new StudentQueue();

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(" ");
        }

        String input = sb.toString().trim();
        if (input.isEmpty()) {
            return;
        }

        String normalized = input.replace(',', ' ');
        Scanner tokenScanner = new Scanner(normalized);
        while (tokenScanner.hasNext()) {
            String op = tokenScanner.next();
            if (op.equalsIgnoreCase("RETURN")) {
                if (tokenScanner.hasNext()) {
                    stack.push(tokenScanner.next());
                }
            } else if (op.equalsIgnoreCase("WAIT")) {
                if (tokenScanner.hasNext()) {
                    queue.enqueue(tokenScanner.next());
                }
            } else if (op.equalsIgnoreCase("SHELVE")) {
                stack.pop();
            } else if (op.equalsIgnoreCase("SERVE")) {
                queue.dequeue();
            }
        }
    }
}

