public class Employee {
    private String name;
    private int age;
    private int experience;

    public Employee(String n,int a,int e) {
        name=n;
        age=a;
        experience=e;
    }

    @Override
    public String toString() {
        return "" +
                "name:" + name + " age=" + age +" experience=" + experience;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee employee = (Employee) obj;
        return age == employee.age && experience == employee.experience && name.equals(employee.name);
    }
    @Override
    public int hashCode() {
        return 31 * name.hashCode() + experience;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }
}
