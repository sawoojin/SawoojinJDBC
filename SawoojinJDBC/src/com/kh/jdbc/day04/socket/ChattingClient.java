package com.kh.jdbc.day04.socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChattingClient {

	public static void main(String[] args) {
		String address = "127.0.0.1";
		int port = 9999;
		InputStream is = null;
		OutputStream os = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("서버와 연결 대기중...");
			Socket socket = new Socket(address, port);
			System.out.println("서버와 연결 성공");
			is = socket.getInputStream();
			os = socket.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
			
			while(true) {
				String resvMsg = dis.readUTF();
				if("exit".equalsIgnoreCase(resvMsg)) {
					System.out.println("서버에서 종료");
					break;
				}
				System.out.println("받은 메시지 : " + resvMsg);
				
				System.out.print("클라이언트 : ");
				String sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				if("exit".equals(sendMsg)) {
					System.out.println("클라이언트에서 종료");
					break;
				}
				dos.flush();
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
				dis.close();
				os.close();
				dos.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

	}

}
