package org.docksidestage.javatry.basic.st6.os;

public class OldWindows extends St6OperationSystem {
    public OldWindows(String loginId) {
        super("OldWindows", loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "\\"; // Old Windows固有の実装
    }

    @Override
    protected String getUserDirectory() {
        return "/Documents and Settings/" + super.loginId; // Old Windows固有の実装
    }
}
