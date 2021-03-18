package com.tm.guestbook.common.utility;

public class GuestBookSecurityContextHolder {
    private static final ThreadLocal<ExecutionContext> contextHolder = new ThreadLocal<>();
    public static ExecutionContext getContext() {
        return contextHolder.get();

    }

    public static void setExecutionContext(ExecutionContext executionContext) {
        contextHolder.set(executionContext);
    }

    public static void clearExecutionContext() {
        contextHolder.remove();
    }
}
