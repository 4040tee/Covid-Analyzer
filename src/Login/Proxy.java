package Login;

import javax.swing.JFrame;
/*
 * This class functions as a "proxy" and  creates a substitute login object
 * @author Menghan Wang
 * @param String users'input
 */
public class Proxy extends Subject {
    private RealSubject realSubject;
    @Override
    public void loginRequest(String uIn, String pIn, JFrame frameA) {
        // Use "lazy" initialization
        if (realSubject == null)
            realSubject = new RealSubject();

        realSubject.loginRequest(uIn,pIn,frameA);
    }

}
