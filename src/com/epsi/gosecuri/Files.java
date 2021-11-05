package com.epsi.gosecuri;

import java.io.*;
import java.util.*;

public class Files {
    private final List<String> staffList = new ArrayList<>();
    private final Map<String, String> toolsList = new HashMap<>();
    private final List<Agent> agents = new ArrayList<>();

    private final String Header = """
            <!doctype html>
            <html lang="fr">
            <head>
                <meta charset="utf-8">
                <title>Go Securi</title>
                <link rel="stylesheet" href="../css/bootstrap.css">
                <link rel="stylesheet" href="../css/style.css">
                <script src="../js/bootstrap.js"></script>
            </head>
            <body>
                <main class="container-fluid">
                    <div class="row">
                        <div class="col">
                            <nav class="navbar navbar-light bg-light">
                              <div class="container-fluid">
                                <a class="navbar-brand" href="index.html"><img src="../images/GoSecuri.PNG" alt="Logo Go Securi"></a>
                              </div>
                            </nav>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
            """;

    private final String Footer = """
                    </div>
                </div>
            </main></body>""".indent(4);
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
                    if (!str.isEmpty()){

                        System.out.println(str);
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
        for (Agent a : agents) {
            result += "<div><a href=\"" + (a.getSurname().charAt(0)+a.getName()).toLowerCase() + ".html\">" + a.getSurname() + " " + a.getName() + "</a>";
        }
        return result;
    }
    public void CreateIndex(){
        String agentsList = getAgentListNames();
        try{
            new FileOutputStream("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\web\\index.html", false).close();
            FileWriter writeIndex = new FileWriter("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\web\\index.html");
            writeIndex.write(Header);
            writeIndex.write(agentsList);
            writeIndex.write(Footer);
            writeIndex.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CreateAgentPage(){
        //String agentTools = getAgentData();
        for (Agent a : agents) {
            try{
                new FileOutputStream("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\web\\"+ (a.getSurname().charAt(0)+a.getName()).toLowerCase() +".html", false).close();
                FileWriter writeAgent = new FileWriter("C:\\Users\\aca\\Documents\\Java\\Go-Securi_v2\\src\\com\\epsi\\gosecuri\\assets\\web\\"+ (a.getSurname().charAt(0)+a.getName()).toLowerCase() +".html");
                writeAgent.write(Header);
                writeAgent.write("<div class=\"col-6\"><h1>"+ a.getSurname()+ " " + a.getName() +"</h1></div>" +
                                 "<div class=\"col-6 \"><img src=\"../images/"+ a.getPhoto() + "\" alt=\"Carte d'identité de "+ a.getSurname()+ " " + a.getName() +"\"></div></div>" +
                                 "<div class=\"row\"><div class=\"col\">");
                for (String tool : a.getToolsList()) {
                    writeAgent.write( "<div class=\"form-check\"><input class=\"form-check-input\" type=\"checkbox\" value=\""+ tool + "\" id=\"id"+tool.substring(0, 4)+"\" checked readonly>\n" +
                              "  <label class=\"form-check-label\" for=\"id"+tool.substring(0, 4)+"\">\n" +
                              tool + "\n" +
                              "  </label></div>");
                }
                writeAgent.write(Footer);
                writeAgent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
