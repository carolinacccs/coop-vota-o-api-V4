package com.coop.votation.exception; // ou .web, dependendo de onde colocou o handler

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}