package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class L3_LibraryBookShelf {

    public static void shelf(int[] books) {
        if (books == null || books.length <= 1) {
            return;
        }
        for (int i = 1; i < books.length; i++) {
            int key = books[i];
            int j = i - 1;
            while (j >= 0 && books[j] > key) {
                books[j + 1] = books[j];
                j--;
            }
            books[j + 1] = key;
            System.out.println("After placing " + key + ": " + Arrays.toString(books));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        String input = sb.toString().trim();

        int[] books;
        if (input.contains("[") && input.contains("]")) {
            int start = input.indexOf('[');
            int end = input.indexOf(']');
            String content = input.substring(start + 1, end).trim();
            String[] tokens = content.split("[,\\s]+");
            List<Integer> list = new ArrayList<>();
            for (String t : tokens) {
                if (!t.trim().isEmpty()) {
                    list.add(Integer.parseInt(t.trim()));
                }
            }
            books = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                books[i] = list.get(i);
            }
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 0) {
                if (list.size() > 1 && list.get(0) == list.size() - 1) {
                    books = new int[list.size() - 1];
                    for (int i = 1; i < list.size(); i++) {
                        books[i - 1] = list.get(i);
                    }
                } else {
                    books = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        books[i] = list.get(i);
                    }
                }
            } else {
                books = new int[0];
            }
        }

        shelf(books);
    }
}
