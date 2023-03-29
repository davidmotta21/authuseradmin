package com.prueba.authuseradminds.helpers;

public enum ETypeAuthResult {
    AUTHENTICATED(1),
    UNAUTHENTICATED(2),
    NOSERVICE(3);

    private int value;

    ETypeAuthResult(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String text() {
        switch (this.value) {
            case 1:
                return "AUTHENTICATED";
            case 2:
                return "UNAUTHENTICATED";
            case 3:
                return "NOSERVICE";
            default:
                return "UNAUTHENTICATED";
        }
    }
}
