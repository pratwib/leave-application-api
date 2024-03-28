package com.pratwib.leaveapplicationapi.exception;

public class NotInPendingStatusException extends IllegalArgumentException {
    public NotInPendingStatusException() {
        super("Already been approved or rejected");
    }

}
