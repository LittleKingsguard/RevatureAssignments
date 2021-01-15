package arrlistassignment;

import java.util.ArrayList;

public class EmpManager {
    public static void main(String[] args){
        ArrayList<Employee> al = new ArrayList<Employee>();
        for (int i = 0; i < 4; i++) {
            al.add(new Employee("Employee" + (i + 1), i, "Salesman"));
        }
        display(al);
    }
    static void display(ArrayList al){
        for (Object e : al) {
            System.out.println(e.toString());
        }
    }
}

