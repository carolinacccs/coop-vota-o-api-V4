package com.coop.votation.web;

public class ConflitoOperacaoException extends RuntimeException {
    public ConflitoOperacaoException() { super(); }
    public ConflitoOperacaoException(String message) { super(message); }
    public ConflitoOperacaoException(String message, Throwable cause) { super(message, cause); }
}
