package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;
import java.util.Date;

@Service
public class AnualBonusServiceImpl implements AnualBonusService{

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays){
        int thisyear = Year.now().getValue();
        int days = 365;
        boolean four = thisyear % 4 == 0 ;
        boolean cent = thisyear % 100 ==0;
        boolean fourcent = thisyear %400==0;
        if ((four && !cent) || fourcent){ days = 366;}
        if (positions.isManager()){
            return (salary*bonus*days*positions.getPositionCoefficient()/workDays)*1.25;
        }
        else {
            return salary * bonus * days * positions.getPositionCoefficient() / workDays;
        }
    }
}
