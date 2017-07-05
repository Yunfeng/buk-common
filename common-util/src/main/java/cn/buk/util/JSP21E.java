package cn.buk.util;

import java.io.*;
import java.net.*;
import java.util.*;

public class JSP21E {
	private String remoteAddr = "";
	private int remotePort;
	private int Timeout = 6;
	private String Hostcmd = "";
	private String SearchStatus = "ER";

	public JSP21E() {
	}

	/**
	 * 实时查询航班信息 将查询参数通过SOCKET发送给查询服务器,并在指定的时间内等待返回结果.
	 */
	public String  realtimeSearch(String val) {

		setHostcmd(val);

		Socket socket = new Socket();
		try {
			socket.connect( new InetSocketAddress( getRemoteAddr(), getRemotePort() ), 3000 );

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out.write(getHostcmd(), 0, getHostcmd().length());
			out.flush();

			// read information from server
			// String info;

			boolean done = false;

			long ii = 0;

			Calendar aThen = Calendar.getInstance();
			Calendar aNow;
			while (!done) {
				aNow = Calendar.getInstance();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}

				ii = aNow.getTimeInMillis() - aThen.getTimeInMillis();

				if (ii > (getTimeout() * 1000))
					done = true;

				if (input.ready()) {
					setSearchStatus(input.readLine());
				System.out.println(getSearchStatus());
					done = true;
				}
			}

		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return getSearchStatus();
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public void setTimeout(int timeout) {
		Timeout = timeout;
	}

	public int getTimeout() {
		return Timeout;
	}

	public void setHostcmd(String hostcmd) {
		Hostcmd = hostcmd;
	}

	public String getHostcmd() {
		return Hostcmd;
	}

	public void setSearchStatus(String searchStatus) {
		SearchStatus = searchStatus;
	}

	public String getSearchStatus() {
		return SearchStatus;
	}
}