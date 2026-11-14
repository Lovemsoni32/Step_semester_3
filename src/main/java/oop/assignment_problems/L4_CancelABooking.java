package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L4_CancelABooking {

    public static class Node {
        public String data;
        public Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node removeFirst(Node head, String booking) {
        if (head == null) {
            System.out.println(booking + " not found");
            return null;
        }

        // Handle deletion if match is at the head
        if (head.data.equals(booking)) {
            return head.next;
        }

        // Otherwise find the node just before the match
        Node current = head;
        while (current.next != null && !current.next.data.equals(booking)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        } else {
            System.out.println(booking + " not found");
        }
        return head;
    }

    public static void printChain(Node head) {
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
            printChain(null);
            return;
        }

        String chainPart = "";
        String cancelBooking = "";

        // Look for "cancel =" or "cancel="
        Pattern cancelPattern = Pattern.compile("(?:,\\s*)?cancel\\s*=\\s*(\\S+)");
        Matcher cancelMatcher = cancelPattern.matcher(fullInput);

        if (cancelMatcher.find()) {
            cancelBooking = cancelMatcher.group(1).trim();
            chainPart = fullInput.substring(0, cancelMatcher.start()).trim();
        } else {
            String[] lines = fullInput.split("\n");
            if (lines.length >= 2) {
                chainPart = lines[0].trim();
                cancelBooking = lines[lines.length - 1].trim();
                if (cancelBooking.startsWith("cancel =")) {
                    cancelBooking = cancelBooking.substring("cancel =".length()).trim();
                } else if (cancelBooking.startsWith("cancel=")) {
                    cancelBooking = cancelBooking.substring("cancel=".length()).trim();
                }
            } else {
                String[] tokens = fullInput.split("\\s+");
                if (tokens.length >= 2) {
                    cancelBooking = tokens[tokens.length - 1].trim();
                    StringBuilder cb = new StringBuilder();
                    for (int i = 0; i < tokens.length - 1; i++) {
                        cb.append(tokens[i]).append(" ");
                    }
                    chainPart = cb.toString().trim();
                }
            }
        }

        if (chainPart.startsWith("bookings =")) {
            chainPart = chainPart.substring("bookings =".length()).trim();
        } else if (chainPart.startsWith("bookings=")) {
            chainPart = chainPart.substring("bookings=".length()).trim();
        }

        Node head = null;
        if (!chainPart.equalsIgnoreCase("(empty)") && !chainPart.equalsIgnoreCase("empty") && !chainPart.isEmpty()) {
            String[] parts;
            if (chainPart.contains("->")) {
                parts = chainPart.split("->");
            } else if (chainPart.contains(",")) {
                parts = chainPart.split(",");
            } else {
                parts = chainPart.split("\\s+");
            }

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
        }

        head = removeFirst(head, cancelBooking);
        printChain(head);
    }
}
