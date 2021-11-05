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
            String str;
            while ((str = bufferedReader.readLine()) != null){
                this.staffList.add(str);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadToolsFile(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\liste.txt"));
            String str;
            while ((str = bufferedReader.readLine()) != null){
                this.toolsList.put(str.split("\t")[0], str.split("\t")[1]);
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
                toolsListAgent = new ArrayList<>();
                BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\agents\\" + a + ".txt"));
                int i = 1;
                String str;
                while((str = bufferedReader.readLine()) != null){
                    if (i == 1){
                        name = str;
                    }else if (i == 2){
                        surname = str;
                    }else if (i == 3){
                        assignment = str;
                    }else if (i == 4 ){
                        pwd = str;
                    }else{
                        toolsListAgent.add(toolsList.get(str));
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
    public String getAgentListNames(){
        String result = "<h1>Liste des agents</h1>";
        StringBuilder res = new StringBuilder();
        for (Agent a : agents) {
            result += "<div><a href=\"assets/web/"+ a.getSurname().charAt(0)+a.getName() +".html\">" + a.getSurname() + " " + a.getName() + "</a>";
            //res.append("<div>" + "<a href=\"assets/web/").append(a.getSurname().charAt(0)).append(a.getName()).append("\">").append(a.getSurname()).append(" ").append(a.getName()).append("</a>").append("</div>");
        }
        return result;
    }
    public void CreateIndex(){
        String agentsList = getAgentListNames();
        System.out.println(agentsList);
        try{
            new FileOutputStream("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\web\\index.html", false).close();
            FileWriter writeIndex = new FileWriter("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\web\\index.html");
            writeIndex.write(
                    "<!doctype html>\n" +
                    "<html lang=\"fr\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <title>Go Securi</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"assets/css/bootstrap.css\">\n" +
                    "    <link rel=\"stylesheet\" href=\"assets/css/style.css\">\n" +
                    "    <script src=\"assets/js/bootstrap.js\"></script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <main class=\"container-fluid\">\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"col\">\n" +
                    "                <nav class=\"navbar navbar-light bg-light\">\n" +
                    "                  <div class=\"container-fluid\">\n" +
                    "                    <a class=\"navbar-brand\" href=\"web/index.html\"><img class=\"fit-picture\" src=\"assets/images/GoSecuri.PNG\" alt=\"Logo Go Securi\"></a>\n" +
                    "                  </div>\n" +
                    "                </nav>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"col\">\n" +
                                        agentsList +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </main>" +
                    "</body>"
            );
            writeIndex.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
