package com.work.services.excepctions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProcessoAlredyExistsException extends ResponseStatusException {

    public ProcessoAlredyExistsException() {
        super(HttpStatus.CONFLICT, "Processo já cadastrado");
    }
}
