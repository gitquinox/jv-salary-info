package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();
        report.append(String.format(Locale.US, "Report for period %s - %s", fromDate, toDate));
        for (String name : names) {
            int currentNameSalary = 0;
            for (String emploData : data) {
                String[] parts = emploData.split(" ");
                String dateData = parts[0];
                String employeeName = parts[1];
                int emploSalary = Integer.parseInt(parts[3]);
                int emploHours = Integer.parseInt(parts[2]);
                LocalDate currentDate = LocalDate.parse(dateData, formatter);

                if (!currentDate.isBefore(fromDate) && !currentDate.isAfter(toDate)) {
                    if (name.equals(employeeName)) {
                        currentNameSalary += emploSalary * emploHours;
                    }
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(currentNameSalary);
        }
        return report.toString();
    }
}
