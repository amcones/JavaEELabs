import java.lang.reflect.Field;

public class Student {
    private int id;  //表示学生的序号
    private String name;  //表示学生的姓名
    private boolean male;  //表示学生的性别
    private double account; //表示学生的账户余额

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.male = true;
        this.account = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public void f1() {
        System.out.println("f1 method");
    }

    public void f1(String s) {
        System.out.println("f1 method " + s);
    }

    public String f1(String s, Integer i) {
        return ("f1 method " + s + " " + i);
    }
}

class Test {
    public static void main(String[] args) throws IllegalAccessException {
        Student stu = new Student();
        Class<? extends Student> c = stu.getClass();
        Field[] declaredFields = c.getDeclaredFields();
        for (Field f : declaredFields) {
            f.setAccessible(true);
            if (f.getName().equals("id")) {
                f.setInt(stu, 10);
                System.out.println(f.get(stu));
            }
            if (f.getName().equals("name")) {
                f.set(stu, "小明");
                System.out.println(f.get(stu));
            }
            if (f.getName().equals("male")) {
                f.setBoolean(stu, true);
                System.out.println(f.get(stu));
            }
            if (f.getName().equals("account")) {
                f.setDouble(stu, 12.34);
                System.out.println(f.get(stu));
            }
        }
    }
}
