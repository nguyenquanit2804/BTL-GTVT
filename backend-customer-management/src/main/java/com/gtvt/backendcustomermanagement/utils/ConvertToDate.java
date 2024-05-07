package com.gtvt.backendcustomermanagement.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class ConvertToDate {

    public static String convertToDate(String date) {
        String toDate = !StringUtil.isNullOrEmpty(date) ? StringUtils.joinWith(" ", StringUtils.trim(date), "23:59:59") : null;
        if (StringUtils.isEmpty(date)) {
            String currentDate = DateFormatUtils.format(new Date(), "dd/MM/yyyy");
            toDate = StringUtils.joinWith(" ", currentDate, "23:59:59");
        }
        return toDate;
    }
}
