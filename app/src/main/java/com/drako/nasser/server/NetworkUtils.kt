package com.drako.nasser.server

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.Enumeration

class NetworkUtils(private val context: Context) {
    fun getWifiIPAddress(): String? {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo? = wifiManager.connectionInfo
        val ipAddress = wifiInfo?.ipAddress ?: 0
        return if(ipAddress == 0) {
            null
        } else {
            String.format(
                "%d.%d.%d.&d",
                ipAddress and 0xff,
                ipAddress shr 8 and 0xff,
                ipAddress shr 16 and 0xff,
                ipAddress shr 24 and 0xff
            )
        }
    }




    fun getAllIPAddresses(): List<String> {
        val ipAddresses = mutableListOf<String>()

        try {
            val networkInterfaces: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (networkInterfaces.hasMoreElements()) {
                val networkInterface: NetworkInterface = networkInterfaces.nextElement()
                val inetAddresses: Enumeration<InetAddress> = networkInterface.inetAddresses

                while (inetAddresses.hasMoreElements()) {
                    val inetAddress: InetAddress = inetAddresses.nextElement()
                    if (!inetAddress.isLoopbackAddress) {
                        ipAddresses.add(inetAddress.hostAddress)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ipAddresses
    }



    fun getAllIPv4Addresses(): List<String> {
        val ipv4Addresses = mutableListOf<String>()

        try {
            val networkInterfaces: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (networkInterfaces.hasMoreElements()) {
                val networkInterface: NetworkInterface = networkInterfaces.nextElement()
                val inetAddresses: Enumeration<InetAddress> = networkInterface.inetAddresses

                while (inetAddresses.hasMoreElements()) {
                    val inetAddress: InetAddress = inetAddresses.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        ipv4Addresses.add(inetAddress.hostAddress)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ipv4Addresses
    }





}