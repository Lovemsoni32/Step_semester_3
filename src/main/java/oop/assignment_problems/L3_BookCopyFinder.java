package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L3_BookCopyFinder {

    public static class Node {
        public String data;
        public Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static String find(Node head, String id) {
        Node current = head;
        int position = 1;
        int firstPosition = -1;
        int copies = 0;

        // Traverse the linked list exactly once
        while (current != null) {
            if (current.data.equals(id)) {
                if (firstPosition == -1) {
                    firstPosition = position;
                }
                copies++;
            }
            current = current.next;
            position++;
        }

        if (copies > 0) {
            return id + " first at position " + firstPosition + ", copies: " + copies;
        } else {
            return id + " not found";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }

        String fullInput = sb.toString().trim();
        if (fullInput.isEmpty()) {
            return;
        }

        String chainPart = "";
        String targetId = "";

        // Check for "id =" or "id=" pattern
        Pattern idPattern = Pattern.compile("(?:,\\s*)?id\\s*=\\s*(\\S+)");
        Matcher idMatcher = idPattern.matcher(fullInput);

        if (idMatcher.find()) {
            targetId = idMatcher.group(1).trim();
            chainPart = fullInput.substring(0, idMatcher.start()).trim();
        } else {
            // Check if input is multi-line
            String[] lines = fullInput.split("\n");
            if (lines.length >= 2) {
                chainPart = lines[0].trim();
                targetId = lines[lines.length - 1].trim();
                if (targetId.startsWith("id =")) {
                    targetId = targetId.substring("id =".length()).trim();
                } else if (targetId.startsWith("id=")) {
                    targetId = targetId.substring("id=".length()).trim();
                }
            } else {
                // Single line without "id ="
                String[] tokens = fullInput.split("\\s+");
                if (tokens.length >= 2) {
                    targetId = tokens[tokens.length - 1].trim();
                    StringBuilder cb = new StringBuilder();
                    for (int i = 0; i < tokens.length - 1; i++) {
                        cb.append(tokens[i]).append(" ");
                    }
                    chainPart = cb.toString().trim();
                }
            }
        }

        // Clean chainPart
        if (chainPart.startsWith("trolley =")) {
            chainPart = chainPart.substring("trolley =".length()).trim();
        } else if (chainPart.startsWith("trolley=")) {
            chainPart = chainPart.substring("trolley=".length()).trim();
        }

        String[] parts;
        if (chainPart.contains("->")) {
            parts = chainPart.split("->");
        } else if (chainPart.contains(",")) {
            parts = chainPart.split(",");
        } else {
            parts = chainPart.split("\\s+");
        }

        Node head = null;
        Node tail = null;
        for (String p : parts) {
            String val = p.trim();
            if (!val.isEmpty() && !val.equalsIgnoreCase("null")) {
                Node newNode = new Node(val);
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    tail = newNode;
                }
            }
        }

        String result = find(head, targetId);
        System.out.println(result);
    }
}
