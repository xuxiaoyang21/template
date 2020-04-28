package com.starry.sky.ip;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddress {
    /**
     * 获取用户真实客户端地址ip
     * @param request 请求
     * @return ip地址
     */
    public String getIpAddress(HttpServletRequest request) {

        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP  "+ipAddress);
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP  "+ipAddress);
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            System.out.println(" ----- "+ipAddress);
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inetAddress.toString();
            }
        }
        if(ipAddress != null && ipAddress.length()>15) {
            if (ipAddress.indexOf(",")>0) {
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
