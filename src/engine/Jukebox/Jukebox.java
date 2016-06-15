package engine.Jukebox;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;

public class Jukebox {
    private final String rep = "src/engine/Jukebox/BibliSonMP3/";
    private boolean mute = false;
    private boolean isPlaying = false;
    private AdvancedPlayer player = null;
    private final String[] listeMP3 = new String[]{"alarme1.mp3", "coule.mp3", "eau.mp3", "explosion.mp3", "rocket.mp3", "start.mp3", "shortrocket.mp3"};
    private final int[][] secance = new int[][]{{0}, {1}, {2}, {3}, {6}, {4}, {5}, {6, 3, 1}, {6, 3}, {6, 2}};
    private final String[] nomSon = new String[]{"SousMarin", "Coule", "Plouf", "Explosion", "LitleFire", "Fire", "Ouverture", "COULER", "TOUCHER", "EAU"};

    public Jukebox() {
    }

    public void playSon(String nom) {
        if(!this.mute) {
            try {
                boolean e = false;

                for(int i = 0; i < this.nomSon.length; ++i) {
                    if(this.nomSon[i].equals(nom)) {
                        for(int k = 0; k < this.secance[i].length; ++k) {
                            this.playSon(this.secance[i][k]);
                        }

                        e = true;
                        break;
                    }
                }

                if(!e) {
                    System.out.println("Utilisez les noms si dessous :");
                    System.out.println(this);
                }
            } catch (Exception var5) {
                this.displayExeption(var5);
            }
        }

    }

    public void playSon(int n) {
        if(!this.mute) {
            try {
                new String("");
                String e = this.listeMP3[n];
                e = "src/engine/Jukebox/BibliSonMP3/" + e;
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(e)));
                this.player = new AdvancedPlayer(in);
                this.play();
            } catch (Exception var4) {
                this.displayExeption(var4);
            }
        }

    }

    public void playSon(String nom, PlaybackListener listener) {
        if(!this.mute) {
            try {
                String e = new String("");

                for(int i = 0; i < this.nomSon.length; ++i) {
                    if(this.nomSon[i].equals(nom)) {
                        e = this.listeMP3[i];
                        break;
                    }
                }

                e = "src/engine/Jukebox/BibliSonMP3/" + e;
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(e)));
                this.player = new AdvancedPlayer(in);
                this.player.setPlayBackListener(listener);
                this.play();
            } catch (Exception var6) {
                this.displayExeption(var6);
            }
        }

    }

    private void play() {
        try {
            if(this.player != null) {
                this.isPlaying = true;
                this.player.play();
            }
        } catch (Exception var2) {
            ;
        }

    }

    private void play(int begin, int end) {
        try {
            if(this.player != null) {
                this.isPlaying = true;
                this.player.play(begin, end);
            }
        } catch (Exception var4) {
            ;
        }

    }

    public void stop() {
        if(this.player != null) {
            this.isPlaying = false;
            this.player.stop();
        }

    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public String toString() {
        String chaine = new String("");

        for(int i = 0; i < this.nomSon.length; ++i) {
            chaine = chaine + "<" + this.nomSon[i] + "> ";
        }

        return chaine;
    }

    public void demo() {
        for(int i = 0; i < this.nomSon.length; ++i) {
            this.playSon(this.nomSon[i]);
        }

    }

    public void activeSon(boolean b) {
        this.mute = !b;
    }

    private void displayExeption(Exception e) {
        System.out.println("Erreur de lecture du fichier, vérifier que le nom est correct");
        System.out.println("Error :");
        System.out.println(e);
        System.out.println(this);
    }
}
