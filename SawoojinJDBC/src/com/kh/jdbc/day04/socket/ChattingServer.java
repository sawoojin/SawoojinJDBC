package com.kh.jdbc.day04.socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChattingServer {

	public static void main(String[] args) {
		// 네트워크 프로그래밍
		ServerSocket serverSocket = null;
		int port = 9999;
		OutputStream os = null;
		InputStream is = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		Scanner sc = new Scanner(System.in);
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("클라이언트 연결 대기중...");
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트 연결 성공");
			os = socket.getOutputStream();
			is = socket.getInputStream();
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
			
			while(true) {
				System.out.print("서버 : ");
				String sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				dos.flush();
				if ("exit".equalsIgnoreCase(sendMsg)) {
					System.out.println("서버에서 종료");
					break;
				}

				String resvMsg = dis.readUTF();
				if ("exit".equals(resvMsg)) {
					System.out.println("클라이언트에서 종료");
					break;
				}
				System.out.println("받은 메시지 : " + resvMsg);
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				os.close();
				dos.close();
				is.close();
				dis.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

}
