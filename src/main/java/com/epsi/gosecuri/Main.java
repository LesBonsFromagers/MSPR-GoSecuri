package com.epsi.gosecuri;

public class Main {

    public static void main(String[] args) {
        Files index = new Files();
        index.CreateHtpasswdAdmin();
        index.ReadStaffListFile();
        index.ReadToolsFile();
        index.ReadAgentFile();
        index.CreateIndex();
        index.CreateAgentPage();
    }
}

// autoscan-sky.com  ->  refaire les cartes d'identités