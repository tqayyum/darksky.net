package util;

import framework.actions_web.BasePage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil extends BasePage {

    public String getHr(int range) {
        SimpleDateFormat df = new SimpleDateFormat("ha");
        Date hr = new Date();
        String currentHr = df.format(hr);
        return currentHr;
    }

    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd");
        Date date = new Date();
        String currentDate = df.format(date);
        return currentDate;
    }
}
