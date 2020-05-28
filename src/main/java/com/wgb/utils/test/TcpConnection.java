package com.wgb.utils.test;

import ch.qos.logback.core.encoder.ByteArrayUtil;

import java.io.*;
import java.net.Socket;

/**
 * @author INNERPEACE
 * @date 2020/5/19 11:39
 */
public class TcpConnection {
	public static void main(String[] args) throws IOException {
		/*String address = "localhost";
		int port = "12345";*/
//		String serverName = args[0];
//		int port = Integer.parseInt(args[1]);
		String serverName = "localhost";
		int port = 33001;
		try {
			System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
			/*创建一个socket对象，serverName服务端IP地址，port服务端端口号*/
			Socket client = new Socket(serverName, port);
			/**返回服务端IP地址*/
			System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
			/*返回输出流*/
			OutputStream outToServer = client.getOutputStream();

			DataOutputStream out = new DataOutputStream(outToServer);
			/*发送信息*/
			String sendMessage = "600003000060101032007208000020000000C0003600018230303031313239383830313538363439383036383034330040313331303230323030313032303131363232303030303332303235363632303030303031313239380011000000030040003953657175656E6365204E6F323630303332303030303332303235363632303030303031313239380003303120";
//			out.writeUTF("Hello from " + client.getLocalSocketAddress());
//			out.writeUTF(sendMessage);
			byte[] bytes = {96, 0, 3, 0, 0, 96, 16, 16, 50, 0, 114, 8, 0, 0, 32, 0, 0, 0, -64, 0, 54, 0, 1, -126, 48, 48, 48, 49, 49, 50, 57, 56, 56, 48, 49, 53, 56, 54, 52, 57, 56, 48, 54, 56, 48, 52, 51, 0, 64, 49, 51, 49, 48, 50, 48, 50, 48, 48, 49, 48, 50, 48, 49, 49, 54, 50, 50, 48, 48, 48, 48, 51, 50, 48, 50, 53, 54, 54, 50, 48, 48, 48, 48, 48, 49, 49, 50, 57, 56, 0, 17, 0, 0, 0, 3, 0, 64, 0, 57, 83, 101, 113, 117, 101, 110, 99, 101, 32, 78, 111, 50, 54, 48, 48, 51, 50, 48, 48, 48, 48, 51, 50, 48, 50, 53, 54, 54, 50, 48, 48, 48, 48, 48, 49, 49, 50, 57, 56, 0, 3, 48, 49, 32};
			String hexString = ByteArrayUtil.toHexString(bytes);
//			String s = bytes.toString();
//			out.write(s.getBytes());
//			out.write(hexString.getBytes());
			out.writeUTF(hexString);
			/*返回输入流*/
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			/*readUTF()读取信息*/
			System.out.println("服务器响应： " + in.readUTF());
			client.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
