package com.drich.workshop;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java.lang.Math.min;

public class LSocket {

    private Socket socket;
    DataOutputStream dOut;
    int curR, curG, curB;

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public LSocket(String host, int port) {
        try {
            socket = new Socket(host, port);
            dOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean writeMsg(String s) {
        try {
            dOut.write(hexStringToByteArray(s));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeColor(int r, int g, int b) {
        int checksum = 49 + r + g + b + 15 & 255;

        String hexR = convertColor(r);
        String hexG = convertColor(g);
        String hexB = convertColor(b);
        String hexChecksum = Integer.toHexString(checksum);

        curR = r;
        curG = g;
        curB = b;

        return writeMsg("31" + hexR + hexG + hexB + "00000F" + hexChecksum);
    }

    public String convertColor(int color) {
        if (color == 0)
            return "00";
        return Integer.toHexString(color);
    }

    public boolean bright(double l) {
        return changeColor((int) (min(255, curR * l)), (int) min(255, curG * l), (int) min(255, curB * l));
    }

}
