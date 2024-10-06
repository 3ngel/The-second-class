package ru.arkhipov.MyThirdTestAppSpringBoot.service;

import ru.arkhipov.MyThirdTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MyThirdTestAppSpringBoot.model.Request;

public interface CodeExceptionService {
    void isSupported(Request request) throws UnsupportedCodeException;
}
