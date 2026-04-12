package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] data, String dateFrom, String dateTo) {
        int johnSalary = 0;
        int andrewSalary = 0;
        int kateSalary = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        for (String emploData : data) {
            String dateData = emploData.split(" ")[0];
            int emploSalary = Integer.parseInt(emploData.split(" ")[3]);
            int emploHours = Integer.parseInt(emploData.split(" ")[2]);

            LocalDate currentDate = LocalDate.parse(dateData, formatter);
            if (!currentDate.isBefore(fromDate) && !currentDate.isAfter(toDate)) {
                switch (emploData.split(" ")[1]) {
                    case "John" -> johnSalary += emploSalary * emploHours;
                    case "Andrew" -> andrewSalary += emploSalary * emploHours;
                    case "Kate" -> kateSalary += emploSalary * emploHours;
                    default -> {
                        return "Default";
                    }
                }
            }
        }

        return String.format("Report for period %s - %s%nJohn - %d%nAndrew - %d%nKate - %d",
                dateFrom, dateTo, johnSalary, andrewSalary, kateSalary);
    }
}
