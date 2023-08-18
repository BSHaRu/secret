package n4_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
// UDP : 일방적으로 데이터를 전송만 해서 속도는 빠르나, 중간에 데이터 손실이 일어날 수가 있다.
public class UDPClientEx {

	public static void main(String[] args) {

		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			
			for(int i=1; i<4; i++) {
				String message = "message - " +i;
				byte[] bytes = message.getBytes();
				DatagramPacket datagramPacket 
						= new DatagramPacket(bytes, bytes.length,new InetSocketAddress("192.168.1.104",5001) );
						//  DatagramPacket(전달할 데이터, 전달할 데이터 크기, 전달할 위치)
				datagramSocket.send(datagramPacket);
				System.out.println("[ 보낸 바이트 수 ] " +bytes.length);
			}
			System.out.println("[발신 완료]");
			datagramSocket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
