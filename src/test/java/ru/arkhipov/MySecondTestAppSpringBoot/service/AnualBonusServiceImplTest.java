package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

import static org.junit.jupiter.api.Assertions.*;

class AnualBonusServiceImplTest {

    @Test
    void calculate() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workdays = 243;
        double salary = 100000.00;

        double result = new AnualBonusServiceImpl().calculate(position, salary, bonus, workdays);

        double expected = 360493.8271604938;
        assertEquals(result, expected);
    }
}