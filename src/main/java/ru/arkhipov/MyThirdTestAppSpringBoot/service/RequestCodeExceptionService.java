package ru.arkhipov.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MyThirdTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MyThirdTestAppSpringBoot.model.Request;

@Service
public class RequestCodeExceptionService  implements CodeExceptionService{

    @Override
    public void isSupported(Request request ) throws UnsupportedCodeException {
        if (request.getUid().equals("123")){
            throw new UnsupportedCodeException("123 не работает");
        }
    }
}
