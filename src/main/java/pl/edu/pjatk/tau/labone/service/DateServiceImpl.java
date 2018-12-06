package pl.edu.pjatk.tau.labone.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateServiceImpl implements DateService {
    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }
}
