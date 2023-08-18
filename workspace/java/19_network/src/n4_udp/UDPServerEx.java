package n4_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerEx {

	public static void main(String[] args) {
		try {
			DatagramSocket datagramSocket = new DatagramSocket(5001);  // DatagramSocket : 수신전용
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							DatagramPacket packet = new DatagramPacket(new byte[100], 100); // 수신전용이라 어디서 전달 받을진 알필요없음.
							datagramSocket.receive(packet);
							String data = new String(packet.getData(),0,packet.getLength());
							System.out.println("[받은 내용 : ] "+data);
						} catch (IOException e) {
							e.printStackTrace();
							System.out.println("수신 종료");
						}
					}//while end
				} // run end
			});
			t.start();
			
			Thread.sleep(30000);
			datagramSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
