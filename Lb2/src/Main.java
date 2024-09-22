import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.HashSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // Функция для вывода содержимого ArrayList
    public static void printArrayList(ArrayList<Employee> list) {
        for (Employee item : list) {
            System.out.println(item);
        }
    }
    // Функция для вывода содержимого HashSet
    public static void printHashSet(HashSet<Employee> set) {
        for (Employee item : set) {
            System.out.println(item);
        }
    }
    // Функция для вывода содержимого TreeSet
    public static void printTreeSet(TreeSet<Employee> set) {
        for (Employee item : set) {
            System.out.println(item);
        }
    }
    public static void part1(){
        //создание контейнеров типа Long
        ArrayList<Long> a = new ArrayList<>();
        ArrayList<Long> b = new ArrayList<>();

        //заполнение контейнера данными
        a.add(9324L);
        a.add(124L);
        a.add(432L);
        a.add(64L);
        a.add(2L);
        a.add(124L);
        a.add(432L);
        a.add(64L);
        a.add(2L);

        //Вывод контейнера
        System.out.println(a);
        System.out.println("---------------");

        //удаление элемента по индексу
        a.remove(0);
        System.out.println(a);
        System.out.println("---------------");

        //удаление элемента по значению
        a.remove(432L);
        System.out.println(a);
        System.out.println("---------------");

        //Замена элементов
        for (int i = 0; i < a.size(); i++) {
            a.set(i,12L+i*2);
        }
        System.out.println(a);
        System.out.println("---------------");

        //заполнение второго контейнера данными
        for (int i = 0; i < 5; i++) {
            b.add(1L+i);
        }

        //доступ к элементам с использованеим итераторов
        Iterator <Long> iter = a.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("---------------");

        //удаление нескольких элементов из первого контейнера
        Long element = 14L; // Элемент после которого нужно удалить
        int n = 5; // Количество элементов для удаления
        int index=a.indexOf(element);
        System.out.println(a);
        for (int i = 0; i<n; i++) {
            if (index<a.size()) a.remove(index);
        }
        System.out.println(b);
        //Добавление элементов из второго контейнера в первый
        a.addAll(b);
        System.out.println(a);

    }
    public static void part2(){
        //создание контейнеров типа Long
        ArrayList<Employee> a = new ArrayList<>();
        ArrayList<Employee> b = new ArrayList<>();

        //заполнение контейнера данными
        a.add(new Employee("Вася",23,3));
        a.add(new Employee("Катя",20,0));
        a.add(new Employee("Сеня",20,1));
        a.add(new Employee("Кириллся",40,21));
        a.add(new Employee("Николай",33,10));

        //вывод содержимого контейнера
        printArrayList(a);
        System.out.println("---------------");

        a.remove(0);
        printArrayList(a);
        System.out.println("---------------");

        a.remove(new Employee("Вася",23,3));
        printArrayList(a);
        System.out.println("---------------");

        for (int i = 0; i < 2; i++) {
            a.set(i,new Employee("Олег",20+i,i*3));
        }

        printArrayList(a);
        System.out.println("---------------");

        b.add(new Employee("Аня",42,15));
        b.add(new Employee("Сема",27,7));
        b.add(new Employee("Ваня",34,8));
        b.add(new Employee("Дмитрий",30,1));

        Iterator <Employee> iter = a.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("---------------");

        //удаление нескольких элементов из первого контейнера
        Employee element = new Employee("Кириллся", 40, 21); // Элемент после которого нужно удалить
        int n = 2; // Количество элементов для удаления
        int index = a.indexOf(element);

        if (index != -1) { // Проверяем, найден ли элемент
            // Удаляем n элементов начиная с index + 1
            System.out.println(1);
            for (int i = 0; i < n; i++) {
                if (index < a.size()) {
                    a.remove(index); // Удаляем следующий элемент
                } else {
                    break; // Если индекс выходит за пределы списка, выходим из цикла
                }
            }
        }
        printArrayList(b);
        System.out.println("---------------");
        a.addAll(b);
        printArrayList(a);

    }
    public static void part3(){
        ArrayList<Employee> a = new ArrayList<>();
        a.add(new Employee("Вася",23,3));
        a.add(new Employee("Катя",20,0));
        a.add(new Employee("Катя",20,0));
        a.add(new Employee("Катя",20,0));
        a.add(new Employee("Сеня",20,1));
        a.add(new Employee("Кириллся",40,21));
        a.add(new Employee("Николай",33,12));
        Comparator<Employee> comparator = Comparator.comparing(Employee::getAge);
        a.sort(comparator);
        System.out.println("Рабочие по возрасту:");
        printArrayList(a);
        System.out.println("---------------");

        //создание второго контейнера типа TreeSet
        Comparator<Employee> employeeComparator = Comparator
                .comparing(Employee::getName)
                .thenComparing(Employee::getAge);

        TreeSet<Employee> employeeSet = new TreeSet<>(employeeComparator);

        for (Employee employee : a) {
            if (employee.getExperience() > 10) {
                employeeSet.add(employee);
            }
        }
        System.out.println("Рабочие со стажем более 10 лет в TreeSet:");
        printTreeSet(employeeSet);
        System.out.println("---------------");

        Comparator<Employee> employeeComparator1 = Comparator
                .comparing(Employee::getExperience);

        TreeSet<Employee> employeeSet1 = new TreeSet<>(employeeComparator1.reversed());
        employeeSet1.addAll(employeeSet);
        System.out.println("Отсортированные по убыванию стажа TreeSet:");
        printTreeSet(employeeSet1);
        System.out.println("---------------");

        Comparator<Employee> comparator1 = Comparator.comparing(Employee::getExperience);
        a.sort(comparator1.reversed());
        System.out.println("Отсортированные по убыванию стажа ArrayList:");
        printArrayList(a);
        System.out.println("---------------");

        HashSet<Employee> mergedSet = new HashSet<>(employeeSet1);
        mergedSet.addAll(a);
        System.out.println("Объединенные контейнеры в HashSet:");
        printHashSet(mergedSet);
        System.out.println("---------------");
    }
    public static void main(String[] args) {
//        part1();
        part2();
//        part3();

    }
}