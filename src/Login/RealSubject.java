package Login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.GUI;
/*
 * bulid a proxy realsubject to function the actual login process
 * check the combination input and jump to GUI if pass
 */
public class RealSubject extends Subject {
    //@Override
    boolean ccheckPass;
    public void loginRequest(String uIn,String pIn,JFrame frameA){
        try{
            ccheckPass = combinationCheck(uIn,pIn,frameA);
        }catch (IOException e){
            JOptionPane.showMessageDialog(frameA,  "You should never see this");
        }
        if(ccheckPass){
            new GUI().GUIinit();
            frameA.setVisible(false);
        } else JOptionPane.showMessageDialog(frameA,  "No valid combination");
    }
/*
 * check the combination of username and password from the txt file
 * @param String user's inputs
 * @param JFrame login frame
 * @return Boolean a boolean indicates whether the combination exists in the file
 */
    private boolean combinationCheck(String uIn, String pIn, JFrame frameA)throws IOException {
        Map<String, String> upCombination = new HashMap<String, String>();
        String path = "Combination.txt";
        File comb = new File(path);
        BufferedReader bReader = new BufferedReader(new FileReader(comb));
        String string = "";
        String pc[] = null;
        String key = "";
        String value = "";
        while ((string = bReader.readLine()) != null) {
            pc = string.split(",");
            key = pc[0];
            value = pc[1];
            upCombination.put(key, value);
        }
        Set<String> keySet = upCombination.keySet();

        ccheckPass=false;
        for(String string1:keySet){
            if (uIn.equals(string1)&& pIn.equals(upCombination.get(string1))){
                ccheckPass=true;
            }

        }

        return ccheckPass;
         //if(!ccheckPass) JOptionPane.showMessageDialog(frameA,  "No valid combination");



    }

}
