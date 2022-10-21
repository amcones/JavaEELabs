import java.lang.reflect.*;
public class TestProxy {
    public static void main(String[] args)
            throws Exception {

        PersonInf t1 = new PersonSQLImp();
        //以指定的target来创建动态代理
        PersonInf p = (PersonInf) MyProxyFactory.getProxy(t1);
        p.add();
        p.delect();
        p.save();
        p.delect();

        Dog t2 = new GunDog();
        Dog dog = (Dog) MyProxyFactory.getProxy(t2);
        dog.info();
        dog.run();
    }
}

class MyProxyFactory
{
    public static Object getProxy(Object target)
        throws Exception
    {
        MyInvokationHandler handler = new MyInvokationHandler();
        handler.setTarget(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handler);
    }
}

class MyInvokationHandler implements InvocationHandler
{
    private Object target;
    public void setTarget(Object target)
    {
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before!");
        Object result = method.invoke(target,args);
        System.out.println("after!");
        return result;
    }
}
