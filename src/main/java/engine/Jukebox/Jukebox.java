/**
 * Created by guillaume on 10/05/16.
 */
package engine.Jukebox;
import javazoom.jl.player.advanced.*;
import java.io.*;

// MP3, WMA, MPEG, WAV compatible

public class Jukebox {

    //Rep ou ce trouve les fichiers audio
    private final String rep = "src/main/java/engine/Jukebox/BibliSonMP3/";
    
    private boolean mute;
    
    private boolean isPlaying = false;
    private AdvancedPlayer player = null;

    private final String[] listeMP3 = {"alarme1.mp3","coule.mp3","eau.mp3","explosion.mp3","rocket.mp3","start.mp3","shortrocket.mp3"};
    private final int[][] secance = {{0},{1},{2},{3},{6},{4},{5},{6,3,1},{6,3},{6,2}};
    private final String[] nomSon = {"SousMarin","Coule","Plouf","Explosion","LitleFire","Fire","Ouverture","COULER","TOUCHER","EAU"};

    public Jukebox(){
        mute=false;
    }

    public void playSon(String nom){
        if(!mute){
            try {
            boolean trouve = false;
            //Compteur
            int i,k;

            //Recherche du son
            for (i=0;i<nomSon.length;i++){
                if(nomSon[i].equals(nom)){
                    for (k=0;k<secance[i].length;k++){
                        playSon(secance[i][k]);
                    }
                    trouve=true;
                    break;
                }
            }
            if(!trouve){
                System.out.println("Utilisez les noms si dessous :");
                System.out.println(this);
            }

        }catch (Exception e){
            displayExeption(e);
        }
        }
        
    }

    public void playSon(int n){
        if(!mute){
            try {
            //Initialisation
            String path=new String("");

            //Recherche du son
            path=listeMP3[n];


            //Creation de l'adresse
            path=rep+path;

            InputStream in = (InputStream)new BufferedInputStream(new FileInputStream(new File(path)));
            player = new AdvancedPlayer(in);

            play();
        }catch (Exception e){
            displayExeption(e);
        }    
        }
       
    }

    public void playSon(String nom,PlaybackListener listener){
        if(!mute){
            try {
            //Initialisation
            String path=new String("");

            //Compteur
            int i;

            //Recherche du son
            for (i=0;i<nomSon.length;i++){
                if(nomSon[i].equals(nom)){
                    path=listeMP3[i];
                    break;
                }
            }

            //Creation de l'adresse
            path=rep+path;

            InputStream in = (InputStream)new BufferedInputStream(new FileInputStream(new File(path)));
            player = new AdvancedPlayer(in);
            player.setPlayBackListener(listener);

            play();
        }catch (Exception e){
            displayExeption(e);
        }
        } 
    }
    

    private void play(){
        try {
            if (player != null) {
                isPlaying = true;
                player.play();
            }
        }catch (Exception e){

        }
    }

    private void play(int begin,int end){
        try {
            if (player != null) {
                isPlaying = true;
                player.play(begin,end);
            }
        }catch (Exception e){

        }
    }

    public void stop(){
        if (player != null) {
            isPlaying = false;
            player.stop();
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    

    public String toString(){
        String chaine = new String("");
        //Compteur
        int i;

        for (i=0;i<nomSon.length;i++){
            chaine=chaine+"<"+nomSon[i]+"> ";
        }
        return chaine;
    }

    public void demo(){
        int i;

        for(i=0;i<nomSon.length;i++){
            playSon(nomSon[i]);
        }
    }
    
    public void activeSon(boolean b){
        mute=!b;
    }

    private void displayExeption(Exception e){
        System.out.println("Erreur de lecture du fichier, vérifier que le nom est correct");
        System.out.println("Error :");
        System.out.println(e);
        System.out.println(this);
    }
}