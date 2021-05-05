package Login;

import javax.swing.JFrame;
/*
 * a subject class require for implementing the proxy pattern 
 */
public abstract class Subject {
    public abstract void loginRequest(String uIn, String pIn, JFrame frameA);
}
