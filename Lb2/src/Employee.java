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
                    "name:" + name + "\n"+
                    "age=" + age +"\n"+
                    "experience=" + experience +"\n"+
                    "___________________"+"\n";
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
