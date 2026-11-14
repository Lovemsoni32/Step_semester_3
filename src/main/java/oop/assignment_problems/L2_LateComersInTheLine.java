package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L2_LateComersInTheLine {

    public static class Node {
        public String data;
        public Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node addFirst(Node head, String name) {
        Node newNode = new Node(name);
        newNode.next = head;
        return newNode;
    }

    public static Node addLast(Node head, String name) {
        Node newNode = new Node(name);
        if (head == null) {
            return newNode;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        return head;
    }

    public static void printLine(Node head) {
        Node current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }

        String fullInput = sb.toString().trim();
        if (fullInput.isEmpty()) {
            printLine(null);
            return;
        }

        Node head = null;

        // Separate initial line configuration from operations
        String initialLinePart = "";
        String operationsPart = "";

        // Find where the first operation (addFirst or addLast) starts
        Pattern opPattern = Pattern.compile("(addFirst|addLast)\\s*(?:\\(|\\s)");
        Matcher opMatcher = opPattern.matcher(fullInput);

        if (opMatcher.find()) {
            int opStart = opMatcher.start();
            initialLinePart = fullInput.substring(0, opStart).trim();
            operationsPart = fullInput.substring(opStart).trim();
        } else {
            initialLinePart = fullInput;
            operationsPart = "";
        }

        // Parse initial line if present
        if (!initialLinePart.isEmpty()) {
            String cleaned = initialLinePart;
            if (cleaned.startsWith("line =")) {
                cleaned = cleaned.substring("line =".length()).trim();
            } else if (cleaned.startsWith("line=")) {
                cleaned = cleaned.substring("line=".length()).trim();
            }

            if (!cleaned.equalsIgnoreCase("(empty)") && !cleaned.equalsIgnoreCase("empty")) {
                String[] parts;
                if (cleaned.contains("->")) {
                    parts = cleaned.split("->");
                } else if (cleaned.contains(",")) {
                    parts = cleaned.split(",");
                } else {
                    parts = cleaned.split("\\s+");
                }

                for (String p : parts) {
                    String name = p.trim();
                    if (!name.isEmpty() && !name.equalsIgnoreCase("null")) {
                        head = addLast(head, name);
                    }
                }
            }
        }

        // Parse operations
        // Supports addFirst(Name), addLast(Name), addFirst Name, addLast Name
        Pattern fullOpPattern = Pattern.compile("(addFirst|addLast)\\s*(?:\\(\\s*([^)]+)\\s*\\)|\\s+([^\\s,;]+))");
        Matcher matcher = fullOpPattern.matcher(operationsPart);

        while (matcher.find()) {
            String op = matcher.group(1);
            String name = matcher.group(2) != null ? matcher.group(2).trim() : matcher.group(3).trim();

            if (op.equalsIgnoreCase("addFirst")) {
                head = addFirst(head, name);
            } else if (op.equalsIgnoreCase("addLast")) {
                head = addLast(head, name);
            }
        }

        printLine(head);
    }
}
