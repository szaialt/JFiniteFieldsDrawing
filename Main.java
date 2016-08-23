import java.awt.*;

//The main startes the settings dialog. 
class Main {
       private static Frame aFrame;
       private static SettingsDialog sd;
    
    public static void main(String[] args){
       aFrame = new Frame();

       sd = new SettingsDialog(aFrame);
      
    }
    

}