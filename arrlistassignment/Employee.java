package arrlistassignment;

public class Employee {
    private String name;
    private int id;
    private String role;

    public Employee(String name, int id, String role) {
        this.name = name;
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
