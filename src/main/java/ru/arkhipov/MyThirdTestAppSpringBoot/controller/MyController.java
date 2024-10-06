package ru.arkhipov.MyThirdTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.MyThirdTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MyThirdTestAppSpringBoot.exception.ValidationFailedException;
import ru.arkhipov.MyThirdTestAppSpringBoot.model.*;
import org.springframework.http.HttpStatus;
import ru.arkhipov.MyThirdTestAppSpringBoot.service.CodeExceptionService;
import ru.arkhipov.MyThirdTestAppSpringBoot.service.ModifyRequestService;
import ru.arkhipov.MyThirdTestAppSpringBoot.service.ModifyResponseService;
import ru.arkhipov.MyThirdTestAppSpringBoot.service.ValidationService;
import ru.arkhipov.MyThirdTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;
@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyRequestService modifyRequestService;

    private final ModifyResponseService modifyResponseService;
    @Autowired
    public MyController(ValidationService validationService, @Qualifier("ModifySystemTimeResponseServece") ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService){
        this.validationService = validationService;
        this.modifyRequestService = modifyRequestService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult){
       log.info("request: {}", request);
        Response response = Response.builder()
               .uid(request.getUid())
               .operationUid(request.getOperationUid())
               .systemTime(DateTimeUtil.getCustomFromat().format(new Date()))
               .code(Codes.SUCCESS)
               .errorCode(ErrorCodes.EMPTY).build();
        log.info("response: {}", response);
        try{
            validationService.isValid(bindingResult);
        }
        catch (ValidationFailedException e) {
            log.error("response: {}", response);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            log.error("response: {}", response);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOW);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        modifyRequestService.notify(request);
        log.info("response: {}", response);
       return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
