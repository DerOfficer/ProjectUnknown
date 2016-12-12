import Control.ProjectUnknownProperties;

import javax.swing.*;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class MainProgram {
    /*Innovative Änderungung:
    1. main methode neu geschrieben
    2. Iterator Schleifen in DrawingPanel durch streams ersetzt
    3. Update wird jz nach Draw aufgerufen, wie es die Dokumentation sagt
    4. Unnötige Initializierung von DrawingPanel.dt entfernt
    5. DT in locale Variable von paintComponent verwandelt
    6. Timer auf 16.6667 ms gesetzt
    7. Hinzufügen von mouseReleased und -pressed zu IInteractableObject
    8. Verschieben der Gate-Sprung-Logik in mouseReleased
    9. Gate springt mit Mitte zum cursor
    10. Rotigkeit des Balles geändert
    11. Methode zum abstürzen des programmes hinzugefügt
        */
    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> MainProgram.setup());
    }

    private static void setup(){
        new ProjectUnknownProperties();
    }

    public synchronized strictfp static final void CRASH(){
        throw new Error("baumwolle");
    }
}
