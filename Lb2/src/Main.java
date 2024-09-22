import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void part1(){
        ArrayList<Long> a = new ArrayList<>();
        ArrayList<Long> b = new ArrayList<>();

        a.add(9324L);
        a.add(124L);
        a.add(432L);
        a.add(64L);
        a.add(2L);

        System.out.println(a);
        System.out.println("---------------");

        a.remove(0);
        System.out.println(a);
        System.out.println("---------------");

        a.remove(432L);
        System.out.println(a);
        System.out.println("---------------");

        for (int i = 0; i < a.size(); i++) {
            a.set(i,12L+i*2);
        }

        System.out.println(a);
        System.out.println("---------------");

        for (int i = 0; i < 5; i++) {
            b.add(12L+i);
        }

        Iterator <Long> iter = a.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        for (int i = 1; i < a.size();) {
            a.remove(i);
        }
        System.out.println(b);
        a.addAll(b);
        System.out.println(a);

    }
    public static void part2(){
        ArrayList<Employee> a = new ArrayList<>();
        ArrayList<Employee> b = new ArrayList<>();

        a.add(new Employee("Вася",23,3));
        a.add(new Employee("Катя",20,0));
        a.add(new Employee("Сеня",20,1));
        a.add(new Employee("Кириллся",40,21));
        a.add(new Employee("Николай",33,10));

        System.out.println(a);
        System.out.println("---------------");

        a.remove(0);
        System.out.println(a);
        System.out.println("---------------");

        a.remove(new Employee("Вася",23,3));
        System.out.println(a);
        System.out.println("---------------");

//        for (int i = 0; i < a.size(); i++) {
//            a.set(i,12L+i*2);
//        }

        System.out.println(a);
        System.out.println("---------------");

        b.add(new Employee("Аня",42,15));
        b.add(new Employee("Сема",27,7));
        b.add(new Employee("Ваня",34,8));
        b.add(new Employee("Дмитрий",30,1));

        Iterator <Employee> iter = a.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        for (int i = 1; i < a.size();) {
            a.remove(i);
        }
        System.out.println(b);
        a.addAll(b);
        System.out.println(a);

    }
    public static void part3(){
        ArrayList<Employee> a = new ArrayList<>();
        Comparator<Employee> comparator = Comparator.comparing(Employee::getAge);
        a.sort(comparator);
        int i=0;
        int aga=23;
    }
    public static void main(String[] args) {
//        part1();
//        part2();
        part3();

    }
}