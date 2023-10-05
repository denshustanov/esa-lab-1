package org.example.service.impl;

import org.example.service.OrderNumberGenerator;

import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Stateless
public class DateWithRandomOrderNumberGenerator implements OrderNumberGenerator {
    public static final String DATE_MASK = "dd-MM-yy";

    public static final String DIGITS = "0123456789";

    public static final int RAND_LENGTH = 6;

    @Override
    public String generate() {
        StringBuilder numberBuilder = new StringBuilder();

        Date date = new Date();
        numberBuilder.append(new SimpleDateFormat(DATE_MASK).format(date));
        Random random = new Random();
        numberBuilder.append('-');
        for (int i = 0; i < RAND_LENGTH; i++) {
            char randomChar = DIGITS.charAt(random.nextInt(DIGITS.length()));
            numberBuilder.append(randomChar);
        }

        return numberBuilder.toString();
    }
}
