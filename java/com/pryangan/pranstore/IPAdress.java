package com.pryangan.pranstore;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Pryangan Chowdhury on 06-01-2018.
 */

public class IPAdress {
    public static String getIP()
    {
        String ip = null;
        try{
             ip =InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
