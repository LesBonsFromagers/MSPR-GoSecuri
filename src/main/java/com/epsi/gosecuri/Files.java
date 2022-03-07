package com.epsi.gosecuri;

import org.apache.commons.codec.digest.DigestUtils;
import java.io.*;
import java.util.*;


public class Files {
    private final List<String> staffList = new ArrayList<>();
    private final Map<String, String> toolsList = new HashMap<>();
    private final List<Agent> agents = new ArrayList<>();

    // Relative Path
    private final String path = "Pipeline-MSPR/src/main/java/com/epsi/gosecuri/assets/";
    // HTML template
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
                    <div class="row header-container">
                        <div class="col">
                            <nav class="navbar navbar-light">
                              <div class="container-fluid img-container">
                                <a class="navbar-brand logo" href="index.html"><img class="logo" src="../images/GoSecuri.PNG" alt="Logo Go Securi"></a>
                              </div>
                            </nav>
                        </div>
                    </div>
                    <div class="row content-container">
                        <div class="col">
            """;
    private final String Footer = """
                    </div>
                </div>
            </main></body>""".indent(4);

    /**
     * Read the file that contains the list of agents
     */
    public void ReadStaffListFile(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path+"staff.txt"));
            String str;
            while ((str = bufferedReader.readLine()) != null){
                this.staffList.add(str);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the file that contains the list of tools
     */
    public void ReadToolsFile(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path+"liste.txt"));
            String str;
            while ((str = bufferedReader.readLine()) != null){
                //Split the line at the tabulation (\t)
                this.toolsList.put(str.split("\t")[0], str.split("\t")[1]);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the file that contains the infos of one agent
     */
    public void ReadAgentFile(){
        String name = null;
        String surname = null;
        String assignment = null;
        String pwd = null;
        List<String> toolsListAgent;

        for (String a : staffList){
            try{
                toolsListAgent = new ArrayList<>();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path+"agents/" + a + ".txt"));
                int i = 1;
                String str;
                // For each line in the file I collect the data and put them in variables
                while((str = bufferedReader.readLine()) != null){
                    if (!str.isEmpty()){
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
                // Instantiate an agent
                Agent agent = new Agent(name, surname, pwd, assignment, toolsListAgent);
                agents.add(agent);
                // Create the Password for the .htpassword
                CreateHtpasswd(agent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * loop over all agents to create an HTML list of link to the content of an agent
     * @return result -> the string that contains the HTML list of links
     */
    public String getAgentListNames(){
        String result = "<h1>Liste des agents</h1>";
        for (Agent a : agents) {
            result += "<div class=\"agent_link_div\"><a class=\"agent_link\" href=\"" + (a.getSurname().charAt(0)+a.getName()).toLowerCase() + ".html\">" + a.getSurname() + " " + a.getName() + "</a></div>";
        }
        return result;
    }

    /**
     * Create the HTML file for the index and write the agents list on it
     */
    public void CreateIndex(){
        // Get the list of links to the page of each agent
        String agentsList = getAgentListNames();
        try{
            // Create the HTML file
            new FileOutputStream(path+"web/index.html", false).close();
            FileWriter writeIndex = new FileWriter(path+"web/index.html");
            // Write the HTML template and the list of agents
            writeIndex.write(Header);
            writeIndex.write(agentsList);
            writeIndex.write(Footer);
            writeIndex.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create an HTML file for each agent and writes his content
     */
    public void CreateAgentPage(){
        // loop over each agent
        for (Agent a : agents) {
            try{
                // Create the HTML file
                new FileOutputStream(path+"web/"+ (a.getSurname().charAt(0)+a.getName()).toLowerCase() +".html", false).close();
                FileWriter writeAgent = new FileWriter(path+"web/"+ (a.getSurname().charAt(0)+a.getName()).toLowerCase() +".html");
                // Write the HTML template
                writeAgent.write(Header);
                // Write the infos of an agent
                writeAgent.write("<div><h1>"+ a.getSurname()+ " " + a.getName() +"</h1></div>" +
                                 "<div><p>Mission : "+ a.getAssignment() + "</p></div>" +
                                 "<div><img src=\"../images/"+ a.getPhoto().toLowerCase() + "\" alt=\"Carte d'identité de "+ a.getSurname()+ " " + a.getName() +"\"></div></div>" +
                                 "<div class=\"checklist_container\"><div class=\"col checklist\">");
                // Get the tools for each agents
                // Check in the HashMap if the tools equals the key and get the value
                for (String tools : this.toolsList.values()){
                        if (a.getToolsList().contains(tools)){
                            writeAgent.write( "<div class=\"form-check\"><input class=\"checkbox\" type=\"checkbox\" value=\""+ tools + "\" id=\"id"+tools.substring(0, 4)+"\" disabled checked readonly>\n" +
                                    "  <label style=\"color: #789424; font-weight: bold;\" for=\"id"+tools.substring(0, 4)+"\">\n" +
                                    tools + "\n" +
                                    "  </label></div>");
                        }else{
                            writeAgent.write( "<div class=\"form-check\"><input class=\"checkbox\" type=\"checkbox\" value=\""+ tools + "\" id=\"id"+tools.substring(0, 4)+"\" disabled readonly>\n" +
                                    "  <label for=\"id"+tools.substring(0, 4)+"\">\n" +
                                    tools + "\n" +
                                    "  </label></div>");
                        }

                }

                writeAgent.write(Footer);
                writeAgent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    /**
//     * Create the .htpasswd with the admin logs
//     */
//    public void CreateHtpasswdAdmin(){
//        String str = "admin:{SHA}"+ Base64.getEncoder().encodeToString(DigestUtils.sha1("admin"));
//        try {
//            new FileOutputStream(".htpasswd", false).close();
//            BufferedWriter writeHtpasswd = new BufferedWriter(new FileWriter(".htpasswd",true));
//            writeHtpasswd.write(str);
//            writeHtpasswd.newLine();
//            writeHtpasswd.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Create the password for each agent
//     * @param a -> an agent
//     */
//    public void CreateHtpasswd(Agent a){
//        String str = (a.getSurname().charAt(0)+a.getName()).toLowerCase()+":{SHA}"+ Base64.getEncoder().encodeToString(DigestUtils.sha1(a.getPassword()));
//        try {
//            BufferedWriter writeHtpasswd = new BufferedWriter(new FileWriter(".htpasswd",true));
//            writeHtpasswd.write(str);
//            writeHtpasswd.newLine();
//            writeHtpasswd.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
