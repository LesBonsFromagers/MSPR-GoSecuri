package com.epsi.gosecuri;

import java.io.*;
import java.util.*;

public class Files {
    private List<String> staffList = new ArrayList<>();
    private Map<String, String> toolsList = new HashMap<>();
    private List<Agent> agents = new ArrayList<>();

    //CreateIndex
    //  List agents links
    //CreateAgentPage
    //  Add agent data
    //  Add list agent tools with checkbox
    public void ReadStaffListFile(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\staff.txt"));
            while (bufferedReader.readLine() != null){
                this.staffList.add(bufferedReader.readLine());
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadToolsFile(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\liste.txt"));
            while (bufferedReader.readLine() != null){
                this.toolsList.put(bufferedReader.readLine().split("\t")[0], bufferedReader.readLine().split("\t")[1]);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadAgentFile(){
        String name = null;
        String surname = null;
        String assignment = null;
        String pwd = null;
        List<String> toolsListAgent;

        for (String a : staffList){
            try{
                toolsListAgent = new ArrayList<String>();
                BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\agents\\" + a + ".txt"));
                int i = 1;
                while(bufferedReader.readLine() != null){
                    if (i == 1){
                        name = bufferedReader.readLine();
                    }else if (i == 2){
                        surname = bufferedReader.readLine();
                    }else if (i == 3){
                        assignment = bufferedReader.readLine();
                    }else if (i == 4 ){
                        pwd = bufferedReader.readLine();
                    }else{
                        toolsListAgent.add(toolsList.get(bufferedReader.readLine()));
                    }
                    i++;
                }
                bufferedReader.close();
                Agent agent = new Agent(name, surname, pwd, assignment, toolsListAgent);
                agents.add(agent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void CreateIndex(){
        try{
            new FileOutputStream("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\web\\index.html", false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
