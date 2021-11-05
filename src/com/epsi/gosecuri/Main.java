package com.epsi.gosecuri;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Files index = new Files();
        index.ReadStaffListFile();
        index.ReadToolsFile();
        index.ReadAgentFile();
        index.CreateIndex();
        index.CreateAgentPage();
    }
}
