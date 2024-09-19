package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Request;

@Service
public class RequestCodeExceptionService  implements CodeExceptionService{

    @Override
    public void isSupported(Request request ) throws UnsupportedCodeException {
        if (request.getUid().equals("123")){
            throw new UnsupportedCodeException("123 не работает");
        }
    }
}
