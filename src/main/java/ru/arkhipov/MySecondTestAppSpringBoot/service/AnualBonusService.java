package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
