package gui;
import model.ResponModel;
import network.connectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class gui_Algo {

    private JTextField pesan;
    private JTextField status;
    private JTextField komentar;
    private JPanel panel1;
    private JButton clossButton;
    private JButton submitButton;
    private JButton minimize;

    public JPanel getPanel1(){
        return panel1;
    }
    public JButton getMinimize (){
        return minimize;
    }

    public gui_Algo() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesan.setText("");
                status.setText("");
                komentar.setText("");
                try{
                    connectURI connection = new connectURI();
                    URL myAddress = connection.buildURL("http://harber.mimoapps.xyz/api/getaccess.php");
                    String response = connection.getResponseFromHttpUrl(myAddress);

                    JSONArray responseJSON = new JSONArray(response);
                    ArrayList<ResponModel> responseModel = new ArrayList<>();
                    for (int i = 0; i < responseJSON.length(); i++) {
                        ResponModel resModel = new ResponModel();
                        JSONObject myJSONObject = responseJSON.getJSONObject(i);
                        resModel.setMassege(myJSONObject.getString("message"));
                        resModel.setStatus(myJSONObject.getString("status"));
                        resModel.setComent(myJSONObject.getString("comment"));
                        responseModel.add(resModel);
                    }
                    for(ResponModel respond : responseModel){
                        pesan.setText(respond.getMassege());
                        status.setText(respond.getStatus());
                        komentar.setText(respond.getComent());
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        clossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
}