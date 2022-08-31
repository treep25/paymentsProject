package com.payments.web.tags;

import org.apache.logging.log4j.util.Strings;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class Local extends TagSupport {
    private String date;
    private String locale;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            if (getLocale().equals("en")) {
                date = Timestamp.valueOf(getDate()).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy h:mm:ss a"));
                out.println(date);
            } else{
                date = Timestamp.valueOf(getDate()).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss"));
                out.println(date);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return SKIP_BODY;
    }
}
