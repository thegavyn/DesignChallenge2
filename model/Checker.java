package model;

import java.time.LocalDateTime;

/**
 * Created by Mina on 3/10/2017.
 */
public class Checker {

    public static boolean isOverlapping(int xStart, int xEnd,
                                 int yStart, int yEnd) {
        boolean isOverlapping = false;

        if (yStart >= xEnd)
            isOverlapping = false;
        if (xStart >= yEnd)
            isOverlapping = false;
        if (xEnd == yEnd)
            isOverlapping = true;
        if (xStart == yStart)
            isOverlapping = true;

        return isOverlapping;
    }

//    public boolean isDateOverlapping(Calendar startDate1, Calendar endDate1,
//                                 Calendar startDate2, Calendar endDate2) {
//        if (!isOverlapping(startDate1.get(Calendar.YEAR), endDate1.get(Calendar.YEAR),
//                           startDate2.get(Calendar.YEAR), endDate2.get(Calendar.YEAR)))
//            return false;
//        if (!isOverlapping(startDate1.get(Calendar.MONTH), endDate1.get(Calendar.MONTH),
//                           startDate2.get(Calendar.MONTH), endDate2.get(Calendar.MONTH)))
//            return false;
//        if (!isOverlapping(startDate1.get(Calendar.DAY_OF_MONTH), endDate1.get(Calendar.DAY_OF_MONTH),
//                           startDate2.get(Calendar.DAY_OF_MONTH), endDate2.get(Calendar.DAY_OF_MONTH)))
//            return false;
//        if (!isOverlapping(startDate1.get(Calendar.HOUR_OF_DAY), endDate1.get(Calendar.HOUR_OF_DAY),
//                           startDate2.get(Calendar.HOUR_OF_DAY), endDate2.get(Calendar.HOUR_OF_DAY)))
//            return false;
//        if (!isOverlapping(startDate1.get(Calendar.MINUTE), endDate1.get(Calendar.MINUTE),
//                           startDate2.get(Calendar.MINUTE), endDate2.get(Calendar.MINUTE)))
//            return false;
//
//        return true;
//    }

    public static boolean isDateOverlapping(LocalDateTime startDate1, LocalDateTime endDate1,
                                     LocalDateTime startDate2, LocalDateTime endDate2) {
        if (!isOverlapping(startDate1.getYear(), endDate1.getYear(),
                startDate2.getYear(), endDate2.getYear()))
            return false;
        if (!isOverlapping(startDate1.getMonthValue(), endDate1.getMonthValue(),
                startDate2.getMonthValue(), endDate2.getMonthValue()))
            return false;
        if (!isOverlapping(startDate1.getDayOfMonth(), endDate1.getDayOfMonth(),
                startDate2.getDayOfMonth(), endDate2.getDayOfMonth()))
            return false;
        if (!isOverlapping(startDate1.getHour(), endDate1.getHour(),
                startDate2.getHour(), endDate2.getHour()))
            return false;
        if (!isOverlapping(startDate1.getMinute(), endDate1.getMinute(),
                startDate2.getMinute(), endDate2.getMinute()))
            return false;

        return true;
    }

//    public boolean isYearOverlapping(int startYear1, int endYear1,
//                                     int startYear2, int endYear2) {
//        return isOverlapping(startYear1, endYear1, startYear2, endYear2);
//    }

//    public boolean isDatesOverlapping(int startYear1, int endYear1, int startMonth1, int endMonth1, int startDay1, int endDay1, int startHour1, int endHour1, int startMinute1, int endMinute1,
//                                      int startYear2, int endYear2, int startMonth2, int endMonth2, int startDay2, int endDay2, int startHour2, int endHour2, int startMinute2, int endMinute2) {
//        if (!isOverlapping(startYear1, endYear1,
//                           startYear2, endYear2))
//            return false;
//        if (!isOverlapping(startMonth1, endMonth1,
//                           startMonth2, endMonth2))
//            return false;
//        if (!isOverlapping(startDay1, endDay1,
//                           startDay2, endDay2))
//            return false;
//        if (!isOverlapping(startHour1, endHour1,
//                           startHour2, endHour2))
//            return false;
//        if (!isOverlapping(startMinute1, endMinute1,
//                           startMinute2, endMinute2))
//            return false;
//
//        return true;
//    }
}
