package org.docksidestage.javatry.basic.st6.os;

public class Mac extends  St6OperationSystem {
    public Mac(String loginId) {
        super("Mac", loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "/";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + super.loginId;
    }
}
