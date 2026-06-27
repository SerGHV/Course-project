package view;

import controller.ProfileController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ProfileFrame extends JFrame {

    public ProfileFrame(User user) {

        setTitle("My profile");

        setSize(350,250);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4,2,10,10));

        JTextField loginField = new JTextField(user.getLogin());

        JTextField fullNameField = new JTextField(user.getFullName());

        JPasswordField passwordField = new JPasswordField();

        JButton saveButton = new JButton("Save");

        panel.add(new JLabel("Login"));
        panel.add(loginField);

        panel.add(new JLabel("Full name"));
        panel.add(fullNameField);

        panel.add(new JLabel("New password"));
        panel.add(passwordField);

        panel.add(new JLabel());
        panel.add(saveButton);

        add(panel);

        ProfileController controller = new ProfileController();

        saveButton.addActionListener(e -> {

            boolean ok = controller.updateProfile(
                    user,
                    loginField.getText(),
                    fullNameField.getText(),
                    new String(passwordField.getPassword())
            );

            if(ok){

                JOptionPane.showMessageDialog(this,"Profile updated");

                dispose();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Password must contain:\n" +
                                "8 symbols\n" +
                                "1 uppercase\n" +
                                "1 digit\n" +
                                "1 special character");

            }

        });

        setVisible(true);

    }

}