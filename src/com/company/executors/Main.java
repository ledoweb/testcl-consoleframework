package com.company.executors;

import com.company.datahandlers.InfoGroup;
import com.company.gameobjects.Cartella;
import com.company.view.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Cartella> cartelle = new ArrayList<>();
    private static int numeroCartelle;
    private static Scanner mainScanner=new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here
        /*
        mainScanner=new Scanner(System.in);


        System.out.println("Tombola: impostare numero cartelle...");
        numeroCartelle = mainScanner.nextInt();
        System.out.println(String.format("Hai selezionato %d cartelle. Inserisci i valori per ogni cartella...(scrivere \"random\" per ottenere generazione casuale)", numeroCartelle));
        for (int i = 0; i < numeroCartelle; i++) {
            System.out.println(String.format("Inserimento cartella numero %d", i + 1));
            Cartella newCartella = new Cartella();
            if (mainScanner.hasNext("random")) {
                newCartella.setRandomNumbers();
            } else {
                newCartella.setNumbers();
            }
            System.out.println("Cartella inserita, è corretta (invio per confermare)?");
            newCartella.print();
            mainScanner.next();
            cartelle.add(newCartella);


        }*/

        InfoGroup i1=new InfoGroup();
        i1.setTitle("Titolo 1");
        i1.setValue("Prova1","testo");
        i1.setValue("Prova2","testo");

        InfoGroup i2=new InfoGroup();
        i2.setTitle("Titolo 2");
        i2.setValue("Prova1","testo");
        i2.setValue("Prova2","testo");

        InfoGroup i3=new InfoGroup();
        i3.setTitle("Titolo 3");
        i3.setValue("Prova1","testotesto");
        i3.setValue("Prova2","testo");

        Sidebar prova=new Sidebar(new InfoGroup[]{i1,i2,i3});
        //Viewport provaViewPort=new Viewport(Elements.VIEWPORT_HORIZONTAL_SIZE,Elements.VIEWPORT_VERTICAL_SIZE);
        Viewport provaViewPort=new Viewport();
        Viewport insideViewPort=new Viewport();
        Viewport nestedViewport=new Viewport();
        Frame provaFrame = new Frame(new Cartella().getRandomNumbersTable());
        Frame provaFrame2=new Frame(new Cartella().getRandomNumbersTable());
        Frame provaFrame3=new Frame(i3);
        provaViewPort.addFrame(provaFrame,2);
        provaViewPort.addFrame(provaFrame2,1);
        provaViewPort.addFrame(provaFrame2,2);
        provaViewPort.addFrame(provaFrame2,0);

        CityView provaCityView=new CityView(null,14,6);
        provaViewPort.addFrame(provaCityView,1);


        provaViewPort.addFrame(provaFrame3,1);
        insideViewPort.addFrame(provaFrame,0);
        insideViewPort.addFrame(provaFrame3,1);
        nestedViewport.addFrame(provaFrame3,0);
        nestedViewport.addFrame(provaFrame,0);
        Frame provaFrameViewport=new Frame(insideViewPort);
        Frame nestedFrameViewport=new Frame(nestedViewport);
        provaViewPort.addFrame(provaFrameViewport,0);
        insideViewPort.addFrame(nestedFrameViewport,0);
        Screen screenManager=new Screen();
        screenManager.refreshScreen(provaViewPort,prova,null);

        while(true){
            String input=mainScanner.nextLine();
            i1.setValue("Prova1",input);
            prova.editInfoGroup(0,i1);
            screenManager.refreshScreen();

        }

    }
}
