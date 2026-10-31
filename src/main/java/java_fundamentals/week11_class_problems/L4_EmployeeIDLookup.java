package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 4: Employee ID Lookup in an Unsorted Directory
 *
 * Builds a HashMap for fast O(1) average department queries.
 * Time Complexity: O(1) average lookup, O(n) build
 * Additional Space Complexity: O(n)
 */
public class L4_EmployeeIDLookup {

    public static class EmployeeRecord {
        public final String employeeId;
        public final String department;

        public EmployeeRecord(String employeeId, String department) {
            this.employeeId = employeeId;
            this.department = department;
        }
    }

    /**
     * Builds HashMap lookup structure from employee records.
     */
    public static Map<String, String> buildLookup(EmployeeRecord[] records) {
        Map<String, String> lookup = new HashMap<>();
        if (records != null) {
            for (EmployeeRecord rec : records) {
                if (rec != null && rec.employeeId != null) {
                    lookup.put(rec.employeeId, rec.department);
                }
            }
        }
        return lookup;
    }

    /**
     * Looks up department by employee ID. Returns "Unknown" if not found.
     */
    public static String getDepartment(Map<String, String> lookup, String employeeId) {
        if (lookup == null || employeeId == null) {
            return "Unknown";
        }
        return lookup.getOrDefault(employeeId, "Unknown");
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

        List<EmployeeRecord> records = new ArrayList<>();
        List<String> queryIds = new ArrayList<>();

        if (input.contains("(") && input.contains(")")) {
            Pattern recPat = Pattern.compile("\\(\\s*\"([^\"]+)\"\\s*,\\s*\"([^\"]+)\"\\s*\\)");
            Matcher recMat = recPat.matcher(input);
            while (recMat.find()) {
                records.add(new EmployeeRecord(recMat.group(1).trim(), recMat.group(2).trim()));
            }

            Pattern queryPat = Pattern.compile("(?i)getDepartment\\s*\\([^,]+,\\s*\"([^\"]+)\"\\)");
            Matcher queryMat = queryPat.matcher(input);
            while (queryMat.find()) {
                queryIds.add(queryMat.group(1).trim());
            }

            if (queryIds.isEmpty()) {
                int lastBracket = input.lastIndexOf(']');
                if (lastBracket != -1) {
                    String after = input.substring(lastBracket + 1);
                    Pattern qp = Pattern.compile("\"([^\"]+)\"");
                    Matcher qm = qp.matcher(after);
                    while (qm.find()) {
                        queryIds.add(qm.group(1).trim());
                    }
                }
            }
        } else {
            Scanner sc = new Scanner(input);
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                for (int i = 0; i < n; i++) {
                    String id = sc.next();
                    String dept = sc.next();
                    records.add(new EmployeeRecord(id, dept));
                }
                while (sc.hasNext()) {
                    queryIds.add(sc.next());
                }
            }
        }

        Map<String, String> lookup = buildLookup(records.toArray(new EmployeeRecord[0]));
        for (String qId : queryIds) {
            String dept = getDepartment(lookup, qId);
            System.out.println("\"" + dept + "\"");
        }
    }
}
