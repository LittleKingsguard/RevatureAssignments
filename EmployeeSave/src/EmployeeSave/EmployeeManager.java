package EmployeeSave;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class EmployeeManager {
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
        System.out.println(hash.toString());
    }
}
