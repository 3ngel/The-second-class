package ru.arkhipov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Request;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Response;
import org.springframework.http.HttpStatus;
import ru.arkhipov.MySecondTestAppSpringBoot.service.CodeExceptionService;
import ru.arkhipov.MySecondTestAppSpringBoot.service.ValidationService;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
       Response response = Response.builder()
               .uid(request.getUid())
               .operationUid(request.getOperationUid())
               .systemTime(simpleDateFormat.format(new Date()))
               .code("succes")
               .errorCode("").build();
        try{
            validationService.isValid(bindingResult);
            codeExceptionService.isSupported(request);
        }
        catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidException");
            response.setErrorMessage("Ошибка валидации");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch(UnsupportedCodeException e){
            response.setErrorCode("Valid uid");
            response.setCode("failed");
            response.setErrorMessage("Ошибка uid");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Неизвестная ошибка");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
