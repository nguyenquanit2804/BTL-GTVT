package com.gtvt.backendcustomermanagement.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class ConvertFromDate {

    public static String convertToDate(String date) {
        String fromDate = !StringUtil.isNullOrEmpty(date) ? StringUtils.joinWith(" ", StringUtils.trim(date), "00:00:00") : null;
        if (StringUtils.isEmpty(date)) {
            String currentDate = DateFormatUtils.format(new Date(), "dd/MM/yyyy");
            fromDate = StringUtils.joinWith(" ", currentDate, "00:00:00");
        }
        return fromDate;
    }

    public static String convertDate(String date) {
        String fromDate = !StringUtil.isNullOrEmpty(date) ? StringUtils.joinWith(" ", StringUtils.trim(date), "00:00:00") : null;
        if (StringUtils.isEmpty(date)) {
            String currentDate = DateFormatUtils.format(new Date(), "dd/MM/yyyy");
            fromDate = StringUtils.joinWith(" ", currentDate, "00:00:00");
        }
        return fromDate;
    }
}
