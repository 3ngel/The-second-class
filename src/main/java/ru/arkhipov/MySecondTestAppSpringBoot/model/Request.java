package ru.arkhipov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    public String[] Systems = new String[]{"ERP", "CRM", "WMS"};

    @NotBlank
    @Size(min = 1,max = 32)
    private String uid;

//    Идентификатор операции
    @NotBlank
    @Size(min = 1,max = 32)
    private String operationUid;

    //Имя системы обработки
    private String systemName;
    //Системное время
    @NotBlank
    private String systemTime;
    private String source;
    private Positions position;
    private Double salary;
    private Double bonus;
    private Integer workDays;
    private Boolean isManager;
    @Min(1)
    @Max(100000)
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
    @Override
    public String toString(){
        return "{"+
                "uid='"+uid+"\'"+
                "operationUid='"+operationUid+"\'"+
                "systemName='"+systemName+"\'"+
                "systemTime='"+systemTime+"\'"+
                "source='"+source+"\'"+
                "communicationId='"+communicationId+"\'"+
                "templateId='"+templateId+"\'"+
                "productCode='"+productCode+"\'"+
                "smsCode='"+smsCode+"\'"+
                "}";
    }
}
