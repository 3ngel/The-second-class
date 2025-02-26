package ru.arkhipov.MySecondTestAppSpringBoot.model;

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

    @NotBlank
    @Size(min = 1,max = 32)
    private String uid;

    @NotBlank
    @Size(min = 1,max = 32)
    private String operationUid;
    private String systemName;
    @NotBlank
    private String systemTime;
    private String source;
    @Min(1)
    @Max(100000)
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
}
