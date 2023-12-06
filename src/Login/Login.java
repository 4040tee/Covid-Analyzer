
package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/*
 * main login function generates a frame for users to login to the main UI
 * Initialize a proxy for login request
 * 
 */


public class Login {
    public static void main(String[] args) {
        JFrame frameA = new JFrame("Login Example");
        frameA.setSize(350, 200);
        frameA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frameA.add(panel);
        Components(panel,frameA);
        frameA.setVisible(true);

    }
/*add designed components to the frame
 * @param Jpanal the login panel
 * @Param JFrame the login frame
 * @ return no return
 */
    private static void Components(JPanel panel,JFrame frameA) {


        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JTextField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Submit!");
        loginButton.setBounds(135, 80, 80, 25);
        panel.add(loginButton);
        login(loginButton,userText,passwordText,frameA);

    }
  /*
     * click the button to submit a new login request
     * @param JButton the submit Button
     * @param JTextField text of username and password input
     * @param JFrame the login frame
     * @return void
     */
    private static void login(JButton loginButton,final JTextField userText,final JTextField passwordText,final JFrame frameA){
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uIn=userText.getText();
                String pIn =passwordText.getText();
                Subject proxy=new Proxy();
                proxy.loginRequest(uIn,pIn,frameA);
            }
        });
    }

    }
