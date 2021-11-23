package conexion;

import java.util.ArrayList;
import java.util.List;

public interface Interfaz{
    public String getResponse(String data);
    public void sendUsername(String username);
    public ArrayList<String> getAllUsernames();
    public boolean kahootIsStarted();
    public ArrayList<String> getRespuestas();
}
