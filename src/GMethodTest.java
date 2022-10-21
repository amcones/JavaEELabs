import java.util.ArrayList;

class Circle implements Comparable<Circle> {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle radius: " + radius;
    }

    @Override
    public int compareTo(Circle c) {
        return (int) (this.radius - c.radius);
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Circle c)){
            return false;
        }
        return c.radius == this.radius;
    }
}

public class GMethodTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Circle> circles = new ArrayList<Circle>();
        list.add(14);
        list.add(24);
        list.add(14);
        list.add(12);
        circles.add(new Circle(3));
        circles.add(new Circle(2.9));
        circles.add(new Circle(5.9));
        circles.add(new Circle(3));
        ArrayList<Integer> newList = removeDuplicates(list);
        ArrayList<Circle> newcircles = removeDuplicates(circles);
        System.out.println(newList);  //是否去掉一个相同的“14”
        System.out.println(newcircles); //是否去掉一个半径为3的圆
        Integer[] numbers = {1, 2, 3};
        System.out.println(max(numbers));
        Circle[] c = {new Circle(3), new Circle(2.9), new Circle(5.9)};
        System.out.println(max(c));
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> tmp = new ArrayList<E>(list.size());
        for (E e : list) {
            if (!tmp.contains(e))
                tmp.add(e);
        }
        return tmp;
    }

    public static <E extends Comparable<E>> E max(E[] list) {
        E ans = list[0];
        for (E e : list) {
            if (e.compareTo(ans)>0) ans = e;
        }
        return ans;
    }
}
