package lshh.securityexample.common.lib;

public class UserThreadHelper{
    private static final ThreadLocal<String> USER_ID = new ThreadLocal<>();

    public static String getUserId(){
        return USER_ID.get();
    }

    public static void setUserId(String adminId){
        USER_ID.set(adminId);
    }
}
