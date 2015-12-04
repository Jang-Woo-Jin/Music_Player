package OS;


/**
 * http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
 */
public class OSValidator {
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isMac() {
        return (OS.contains("mac"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix")
                || OS.contains("nux")
                || OS.contains("aix"));
    }

    private static boolean isSolaris() {
        return (OS.contains("sunos"));
    }

    public static boolean isPOSIX() {
        return (isMac() || isUnix() || isSolaris());
    }
}
