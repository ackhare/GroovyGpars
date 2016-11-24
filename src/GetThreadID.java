/**
 * Created by chetan on 17/11/16.
 */
public class GetThreadID {
    public static native int get_tid();

    static {
        System.loadLibrary("GetThreadID");
    }
}
