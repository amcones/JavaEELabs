import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestMethod {
    public static Object executeMethod(String className, String methodName, Object[] args) {
        try {
            Class<?> c = Class.forName(className);
            int paralen=(args==null?0: args.length);
            Class<?>[] paras=new Class[paralen];
            for (int i=0;i<paralen;i++) {
                paras[i]=args[i].getClass();
            }
            Method m = c.getMethod(methodName, paras);
            return m.invoke(c.newInstance(), args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        executeMethod("Student","f1",null);
        executeMethod("Student","f1", new String[]{"test"});
        System.out.println(executeMethod("Student","f1",new Object[]{"test",1}));
    }
}
