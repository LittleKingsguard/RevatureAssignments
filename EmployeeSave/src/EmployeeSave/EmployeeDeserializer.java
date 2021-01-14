package EmployeeSave;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class EmployeeDeserializer {
        public static void main(String []args){
            FileInputStream fis;
            ObjectInputStream ois;
            try {
                File file = new File("serial_employees.dat");
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                Employee emp;
                try {
                    while (true) {
                        emp = (Employee) ois.readObject();
                        System.out.println(emp.toString());
                    }
                } catch (EOFException ignored) {
                }
                ois.close();
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

}
