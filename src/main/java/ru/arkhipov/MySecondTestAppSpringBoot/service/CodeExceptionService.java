package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Request;

public interface CodeExceptionService {
    void isSupported(Request request) throws UnsupportedCodeException;
}
