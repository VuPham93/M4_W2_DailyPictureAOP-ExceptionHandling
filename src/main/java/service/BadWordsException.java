package service;

public class BadWordsException extends Exception{
    public BadWordsException() {
    }

    public BadWordsException(String message) {
        super(message);
    }
}
