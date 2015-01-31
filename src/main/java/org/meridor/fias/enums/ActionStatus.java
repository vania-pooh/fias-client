package org.meridor.fias.enums;

import java.math.BigInteger;

public enum ActionStatus {
    
    INITIALIZED(01),
    ADDED(10),
    MODIFIED(20),
    GROUP_MODIFIED(30),
    REMOVED(30),
    PARENT_REMOVED(31),
    MERGED(40),
    PARENT_MERGED(41),
    REMOVED_VIA_MERGE(42),
    ADDED_VIA_MERGE(43),
    RESUBORDONER(50),
    PARENT_RESUBORDONER(51),
    REMOVED_VIA_DIVISION(60),
    ADDED_VIA_DIVISION(61),
    RESTORED(70);

    private final Integer actionStatus;

    ActionStatus(Integer actionStatus) {
        this.actionStatus = actionStatus;
    }

    public BigInteger getActionStatus() {
        return new BigInteger(actionStatus.toString());
    }
}
