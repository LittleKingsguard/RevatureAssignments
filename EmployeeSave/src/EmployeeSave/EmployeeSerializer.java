package EmployeeSave;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class EmployeeSerializer {
    public static void main(String []args){
        File file = new File("Employees.txt");
        BufferedReader buff;
        HashMap<Integer, Employee> hash = new HashMap<>();
        StringTokenizer st;
        int id;
        try {
            buff = new BufferedReader(new FileReader(file));
            String str = buff.readLine();
            while (str != null){
                st = new StringTokenizer(str, ":");
                id = Integer.parseInt(st.nextToken());
                hash.put(id, new Employee(id, st.nextToken(), st.nextToken(), st.nextToken()));
                str = buff.readLine();
            }
            buff.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = new FileOutputStream(new File("serial_employees.dat"));
            ObjectOutputStream objout = new ObjectOutputStream(fos);
            hash.forEach((k, v) -> {
                try {
                    objout.writeObject(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            objout.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
