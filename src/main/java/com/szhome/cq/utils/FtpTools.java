package com.szhome.cq.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpTools {

	/** �Ƿ��ֹ��������� */
	private boolean handSwitch = false;

	private String workDirectory;

	public FtpTools() {

	}

	public FTPClient getClient() throws Exception {
		FTPClient ftp = new FTPClient();
		// ����Ϊ���ı���
		ftp.setControlEncoding("gb2312");
		int reply;
		try {
			ftp.connect(FtpClientConfig.server, FtpClientConfig.port);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.err.println("FTP server refused connection.");
				return null;
			}
			if (!ftp.login(FtpClientConfig.username, FtpClientConfig.password)) {
				System.out.println("failed to login.");
				ftp.logout();
				return null;
			}
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(FtpClientConfig.rootPath);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftp;
	}

	/**
	 * �ر�����
	 * 
	 * @throws IOException
	 */
	public void close(FTPClient client) throws IOException {
		if (client.isConnected()) {
			client.logout();
			client.disconnect();
			// Ҳ������Ϊnull
		}
	}

	/**
	 * ����ָ��Ŀ¼�Ƿ����
	 * 
	 * @param String
	 *            filePath Ҫ���ҵ�Ŀ¼
	 * @return boolean:����:true��������:false
	 * @throws IOException
	 */
	public boolean checkPathExist(String filePath, FTPClient client) {
		boolean existFlag = true;
		try {
			for(String dir:filePath.split("/")){
				if (!client.changeWorkingDirectory(dir)) {
					client.makeDirectory(dir);
					client.changeWorkingDirectory(dir);
					existFlag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existFlag;
	}

	/**
	 * ��ȡ��ǰ����Ŀ¼���ļ��б�
	 * 
	 * @return
	 * @throws IOException
	 */
	public String[] listFiles(FTPClient client) throws IOException {
		FTPFile[] files = client.listFiles("");
		int filesLength = files.length;
		String[] fileNameArr = new String[filesLength];
		for (int i = 0; i < filesLength; i++) {
			fileNameArr[i] = files[i].getName();
		}

		return fileNameArr;
	}

	/**
	 * �ϴ��ļ����ļ�����ʽ
	 * 
	 * @param path �ϴ�·��
	 * @param name �ϴ�����
	 * @param remoteName �ϴ�������
	 * @return
	 * @throws IOException
	 */
	public boolean upload(String path, String name, String remoteName,
			FTPClient client) throws IOException {
		FileInputStream fis = new FileInputStream(path + name);
		if (client.storeFile(remoteName, fis)) {
			System.out.println(" upload success !!! ");
			fis.close();
			return true;
		}
		fis.close();

		System.out.println(" upload fail !!! ");
		return false;
	}

	/**
	 * �ϴ��ļ�,����ʽ
	 * 
	 * @param path
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public boolean upload(InputStream stream, String name, String remoteName,
			FTPClient client) throws IOException {
		if (client.storeFile(getWorkDirectory() + remoteName, stream)) {
			System.out.println(" upload success !!! ");
			stream.close();

			return true;
		}
		stream.close();

		System.out.println(" upload fail !!! ");
		return false;
	}

	/**
	 * �����ļ�
	 * 
	 * @param path
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public boolean download(String path, String name, FTPClient client)
			throws UnsupportedEncodingException, IOException {
		FileOutputStream fos = new FileOutputStream(path + name);
		if (client.retrieveFile(new String(name
				.getBytes(FtpClientConfig.localEncoding),
				FtpClientConfig.remoteEncoding), fos)) {
			System.out.println("download success !!! ");
			fos.close();

			return true;
		}
		fos.close();

		System.out.println(" download fail !!! ");
		return false;
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param path
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public boolean removeFile(String path, String name, FTPClient client)
			throws IOException {
		client.changeWorkingDirectory(FtpClientConfig.rootPath + path);
		if (client.deleteFile(name)) {
			System.out.println("remove file success !!! ");

			return true;
		}

		System.out.println(" remove file fail !!! ");
		return false;
	}

	/**
	 * �ı乤��Ŀ¼
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void setWorkDirectory(String path, FTPClient client)
			throws IOException {
		workDirectory = (FtpClientConfig.rootPath + path);
		// ������ֶ����ƿ������øı乤��Ŀ¼
		if (handSwitch) {
			client.changeWorkingDirectory(workDirectory);
		}
	}

	/**
	 * ����Ŀ¼
	 * 
	 * @param pathname
	 * @return
	 * @throws IOException
	 */
	public boolean createDirectory(String pathname, FTPClient client)
			throws IOException {
		boolean okFlag = client.makeDirectory(pathname);
		return okFlag;
	}

	/**
	 * ��ȡ��ǰ����Ŀ¼
	 * 
	 * @return
	 */
	public String getWorkDirectory() {
		return workDirectory;
	}

	/**
	 * ���ֶ�����
	 * 
	 */
	public void openHandSwitch() {
		handSwitch = true;
	}

	/**
	 * �ر��ֶ�����
	 * 
	 */
	public void closeHandSwitch() {
		handSwitch = false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FtpTools ftpo = new FtpTools();
		try {
			Map<String,String> serverparams = new HashMap<String,String>();
			serverparams.put("ip","localhost");
			serverparams.put("port","21");
			serverparams.put("user","aaa");
			serverparams.put("password","a");
			serverparams.put("localpath","c://");
			FtpClientConfig.initConfig(serverparams);
			FTPClient ftp = ftpo.getClient();
			System.out.println(ftp.printWorkingDirectory());
			String[] str = ftpo.listFiles(ftp);
			System.out.println(str.length);
			for (String ss : str) {
				System.out.println("ss:" + ss);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

