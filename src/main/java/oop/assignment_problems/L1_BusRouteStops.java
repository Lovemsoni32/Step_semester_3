package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class L1_BusRouteStops {

    public static class Node {
        public String data;
        public Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node build(String[] stops) {
        if (stops == null || stops.length == 0) {
            return null;
        }

        Node head = new Node(stops[0]);
        Node current = head;
        for (int i = 1; i < stops.length; i++) {
            current.next = new Node(stops[i]);
            current = current.next;
        }
        return head;
    }

    public static void printRoute(Node head) {
        if (head == null) {
            System.out.println("null");
            System.out.println("Stops: 0, First: none, Last: none");
            return;
        }

        Node current = head;
        int count = 0;
        String first = head.data;
        String last = head.data;
        StringBuilder sb = new StringBuilder();

        while (current != null) {
            sb.append(current.data).append(" -> ");
            count++;
            last = current.data;
            current = current.next;
        }
        sb.append("null");

        System.out.println(sb.toString());
        System.out.println("Stops: " + count + ", First: " + first + ", Last: " + last);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(" ");
        }

        String input = sb.toString().trim();
        if (input.isEmpty()) {
            printRoute(null);
            return;
        }

        String[] stops;
        if (input.contains("[") && input.contains("]")) {
            int start = input.indexOf('[');
            int end = input.lastIndexOf(']');
            String inner = input.substring(start + 1, end).trim();
            if (inner.isEmpty()) {
                stops = new String[0];
            } else {
                String[] parts = inner.split(",");
                List<String> list = new ArrayList<>();
                for (String p : parts) {
                    String s = p.trim();
                    if (!s.isEmpty()) {
                        list.add(s);
                    }
                }
                stops = list.toArray(new String[0]);
            }
        } else if (input.contains(",")) {
            String[] parts = input.split(",");
            List<String> list = new ArrayList<>();
            for (String p : parts) {
                String s = p.trim();
                if (!s.isEmpty()) {
                    list.add(s);
                }
            }
            stops = list.toArray(new String[0]);
        } else {
            String[] parts = input.split("\\s+");
            List<String> list = new ArrayList<>();
            for (String p : parts) {
                String s = p.trim();
                if (!s.isEmpty()) {
                    list.add(s);
                }
            }
            stops = list.toArray(new String[0]);
        }

        Node head = build(stops);
        printRoute(head);
    }
}
