package net.minecraftforge.common;

public class ForgeVersion {
    public static final int majorVersion = 0;
    public static final int minorVersion = 0;
    public static final int revisionVersion = 0;
    public static final int buildVersion = 0;

    private static final Status status = Status.PENDING;
    private static final String target = null;

    public static Status getStatus() {
        throw new LinkageError();
    }

    public static String getTarget() {
        throw new LinkageError();
    }

    public static String getVersion() {
        throw new LinkageError();
    }

    public enum Status {
        PENDING,
        FAILED,
        UP_TO_DATE,
        OUTDATED,
        AHEAD,
        BETA,
        BETA_OUTDATED
    }
}