package com.rvsharma.leetcode.HashMap;

import java.util.*;

public class PortPacketSending {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int[] packetsIds = {23, 223, 45, 56, 67, 89, 94, 223};
        int[] portsUsed = sentTimes(200, 2, packetsIds);
        System.out.println("The ports used for sending the packet Ids are: " + Arrays.toString(portsUsed));
    }

    public static int[] sentTimes(int numberOfPorts, int transmissionTimes, int[] packetIds){
        int[] portsUsed = new int[packetIds.length];
        int time = 1;
        Map<Integer, int[]> map = new HashMap<>();

        for(int i = 0; i < packetIds.length; i++){
            int portNum = hash(packetIds[i], numberOfPorts);

            if (map.containsKey(portNum)) {
                while (map.containsKey(portNum)) {
                    portNum++;
                }
            }
            map.put(portNum, new int[]{time, time + transmissionTimes});
            // free up the port
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Integer key = iterator.next();
                if(map.get(key)[1] <= time){
                    iterator.remove();
                }
            }
            time++;
            portsUsed[i] = portNum;
        }
        return portsUsed;
    }

    static int hash(int num1, int num2){
        return num1 % num2;
    }
}
