package ru.arkhipov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.*;
import org.springframework.http.HttpStatus;
import ru.arkhipov.MySecondTestAppSpringBoot.service.CodeExceptionService;
import ru.arkhipov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.arkhipov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final CodeExceptionService codeExceptionService;
    @Autowired
    public MyController(ValidationService validationService,CodeExceptionService codeExceptionService){
        this.validationService = validationService;
        this.codeExceptionService = codeExceptionService;
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
            codeExceptionService.isSupported(request);
        }
        catch (ValidationFailedException e) {
            log.error("response: {}", response);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch(UnsupportedCodeException e){
            log.error("response: {}", response);
            response.setErrorCode(ErrorCodes.VALID_UID);
            response.setCode(Codes.FAILED);
            response.setErrorMessage(ErrorMessages.VALIDATIONUID);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            log.error("response: {}", response);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOW);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("response: {}", response);
       return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
