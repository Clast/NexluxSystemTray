package com.drich.workshop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        LSocket socket = new LSocket("192.168.1.169", 5577);

        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        BufferedImage trayIconImage = null;
        try {
            trayIconImage = ImageIO.read(Main.class.getResource("light.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int trayIconWidth = new TrayIcon(trayIconImage).getSize().width;
        TrayIcon trayIcon = new TrayIcon(trayIconImage.getScaledInstance(trayIconWidth, -1, Image.SCALE_SMOOTH));
        final SystemTray tray = SystemTray.getSystemTray();


        //Light Strip Categories
        CheckboxMenuItem active = new CheckboxMenuItem("Active");
        Menu color = new Menu("Color");

        MenuItem Red = new MenuItem("Red");
        MenuItem Mandarin = new MenuItem("Mandarin");
        MenuItem Gold = new MenuItem("Gold");
        MenuItem Lemon = new MenuItem("Lemon");
        MenuItem Lime = new MenuItem("Lime");
        MenuItem Green = new MenuItem("Green");
        MenuItem Emerald = new MenuItem("Emerald");
        MenuItem Cyan = new MenuItem("Cyan");
        MenuItem Blue = new MenuItem("Blue");
        MenuItem Iris = new MenuItem("Iris");
        MenuItem RoyalPurple = new MenuItem("Royal Purple");
        MenuItem Pink = new MenuItem("Pink");
        MenuItem Violet = new MenuItem("Violet");
        MenuItem White = new MenuItem("White");
        MenuItem Brighten5 = new MenuItem("Brighten 5%");
        MenuItem Brighten10 = new MenuItem("Brighten 10%");
        MenuItem Brighten25 = new MenuItem("Brighten 25%");
        MenuItem Darken5 = new MenuItem("Darken 5%");
        MenuItem Darken10 = new MenuItem("Darken 10%");
        MenuItem Darken25 = new MenuItem("Darken 25%");


        MenuItem exitItem = new MenuItem("Exit");

        //Add components to popup menu
        popup.add(active);
        popup.addSeparator();
        popup.add(color);
        popup.addSeparator();
        color.add(Red);
        color.add(Mandarin);
        color.add(Gold);
        color.add(Lemon);
        color.add(Lime);
        color.add(Green);
        color.add(Emerald);
        color.add(Cyan);
        color.add(Blue);
        color.add(Iris);
        color.add(RoyalPurple);
        color.add(Pink);
        color.add(Violet);
        color.add(White);
        popup.addSeparator();
        popup.add(Brighten5);
        popup.add(Brighten10);
        popup.add(Brighten25);
        popup.add(Darken5);
        popup.add(Darken10);
        popup.add(Darken25);
        popup.addSeparator();
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {

            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from System Tray");
            }
        });

        active.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb1Id = e.getStateChange();
                if (cb1Id == ItemEvent.SELECTED) {
                    socket.writeMsg("71230FA3");
                } else {
                    socket.writeMsg("71240FA4");
                }
            }
        });


        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem) e.getSource();
                //TrayIcon.MessageType type = null;
                System.out.println(item.getLabel());
                switch (item.getLabel()) {
                    case "Red":
                        socket.changeColor(255, 0, 0);
                        break;
                    case "Mandarin":
                        socket.changeColor(223, 22, 0);
                        break;
                    case "Gold":
                        socket.changeColor(161, 64, 0);
                        break;
                    case "Lemon":
                        socket.changeColor(159, 96, 0);
                        break;
                    case "Lime":
                        socket.changeColor(127, 128, 0);
                        break;
                    case "Green":
                        socket.changeColor(0, 255, 0);
                        break;
                    case "Emerald":
                        socket.changeColor(0, 233, 32);
                        break;
                    case "Cyan":
                        socket.changeColor(0, 191, 64);
                        break;
                    case "Blue":
                        socket.changeColor(0, 0, 255);
                        break;
                    case "Iris":
                        socket.changeColor(32, 0, 233);
                        break;
                    case "Royal Purple":
                        socket.changeColor(64, 0, 191);
                        break;
                    case "Pink":
                        socket.changeColor(159, 0, 96);
                        break;
                    case "Violet":
                        socket.changeColor(128, 0, 127);
                        break;
                    case "White":
                        socket.changeColor(255, 255, 255);
                        break;
                    case "Brighten 5%":
                        socket.bright(1.05);
                        break;
                    case "Brighten 10%":
                        socket.bright(1.10);
                        break;
                    case "Brighten 25%":
                        socket.bright(1.25);
                        break;
                    case "Darken 5%":
                        socket.bright(.95);
                        break;
                    case "Darken 10%":
                        socket.bright(.90);
                        break;
                    case "Darken 25%":
                        socket.bright(.75);
                        break;
                }

            }
        };

        Red.addActionListener(listener);
        Mandarin.addActionListener(listener);
        Gold.addActionListener(listener);
        Lemon.addActionListener(listener);
        Lime.addActionListener(listener);
        Green.addActionListener(listener);
        Emerald.addActionListener(listener);
        Cyan.addActionListener(listener);
        Blue.addActionListener(listener);
        Iris.addActionListener(listener);
        RoyalPurple.addActionListener(listener);
        Pink.addActionListener(listener);
        Violet.addActionListener(listener);
        Brighten5.addActionListener(listener);
        Brighten10.addActionListener(listener);
        Brighten25.addActionListener(listener);
        Darken5.addActionListener(listener);
        Darken10.addActionListener(listener);
        Darken25.addActionListener(listener);


        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = Main.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}

