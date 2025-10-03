package org.docksidestage.javatry.basic.st6.os;

public class Windows extends St6OperationSystem {
    public Windows(String loginId) {
        super("Windows", loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "C:\\Users\\" + super.loginId;
    }
}
