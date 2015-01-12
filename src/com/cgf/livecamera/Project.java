package com.cgf.livecamera;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Project {
	private Context mContext;
	private int mContentSize = 0;
	private SharedPreferences mSharedPreferences;
	private final String CONTENT_PREFIX = "project_item_";
	private final String CONTENT_SIZE = CONTENT_PREFIX + "size";
	
	Project(Context context){
		this.mContext = context;
		this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
		this.mContentSize = readContentSize();
	}
	
	ArrayList<Content> readContents(){
		ArrayList<Content> a = new ArrayList<Content>();
		for(int i = 0; i < this.mContentSize; i++){
			a.add(readContent(i));
		}
		return a;
	}
	
	int readContentSize(){
		return this.mSharedPreferences.getInt(CONTENT_SIZE, 0);
	}
	
	Content readContent(int id){
		String hash = this.mSharedPreferences.getString(CONTENT_PREFIX + id + "_hash", null);
		String name = this.mSharedPreferences.getString(CONTENT_PREFIX + id + "_name", null);
		int fileNumber = this.mSharedPreferences.getInt(CONTENT_PREFIX + id + "_fileNumber", 0);
		String folder = this.mSharedPreferences.getString(CONTENT_PREFIX + id + "_foler", null);
		String date = this.mSharedPreferences.getString(CONTENT_PREFIX + id + "_date", null);
		return new Content(hash,name,fileNumber,folder,date);
	}
	
	boolean updateContent(Content content){
		//����R���e���c����
		int id = searchContent(content);
		
		if(id == -1){
			return false;
		}
		
		SharedPreferences.Editor editor = this.mSharedPreferences.edit();
		editor.putString(CONTENT_PREFIX + id + "_name", content.mName);
		editor.putInt(CONTENT_PREFIX + id + "_fileNumber", content.mFileNumber);
		editor.putString(CONTENT_PREFIX + id + "_foler", content.mFolder);
		editor.putString(CONTENT_PREFIX + id + "_date", content.mDate);
		editor.apply();
		
		return true;
	}
	
	boolean addContent(Content content){
		int id = this.mContentSize + 1;
		
		//�n�b�V���l�v�Z
		String hash = encryptStr((new Date()).toString());
		String path = this.mContext.getApplicationContext().getFilesDir() + File.separator + hash;
		//TODO create folder;
		File folder = new File(path);
		folder.mkdir();
		
		SharedPreferences.Editor editor = this.mSharedPreferences.edit();
		editor.putString(CONTENT_PREFIX + id + "_hash", hash);
		editor.putString(CONTENT_PREFIX + id + "_name", content.mName);
		editor.putInt(CONTENT_PREFIX + id + "_fileNumber", content.mFileNumber);
		editor.putString(CONTENT_PREFIX + id + "_foler", path);
		editor.putString(CONTENT_PREFIX + id + "_date", content.mDate);
		
		
		//updateContentsSize
		editor.putInt(CONTENT_SIZE, this.mContentSize + 1);
		editor.apply();
		this.mContentSize = readContentSize();
		
		return true;
	}
	
	boolean delContent(Content content){
		//����R���e���c����
		int id = searchContent(content);
		
		if(id == -1){
			return false;
		}
		
		SharedPreferences.Editor editor = this.mSharedPreferences.edit();
		editor.remove(CONTENT_PREFIX + id + "_hash");
		editor.remove(CONTENT_PREFIX + id + "_name");
		editor.remove(CONTENT_PREFIX + id + "_fileNumber");
		editor.remove(CONTENT_PREFIX + id + "_foler");
		editor.remove(CONTENT_PREFIX + id + "_date");
		editor.apply();
		
		//updateContentsSize
		editor.putInt(CONTENT_SIZE, this.mContentSize - 1);
		editor.apply();
		this.mContentSize = readContentSize();
		
		return true;
	}
	
	boolean setCurrentContent(Content content){
		//����R���e���c����
		int id = searchContent(content);
		
		if(id == -1){
			return false;
		}
		
		SharedPreferences.Editor editor = this.mSharedPreferences.edit();
		editor.remove(CONTENT_PREFIX + "current" + "_hash");
		editor.remove(CONTENT_PREFIX + "current" + "_name");
		editor.remove(CONTENT_PREFIX + "current" + "_fileNumber");
		editor.remove(CONTENT_PREFIX + "current" + "_foler");
		editor.remove(CONTENT_PREFIX + "current" + "_date");
		editor.apply();
		
		return true;
	}
	
	Content getCurrentContent(){
		String hash = this.mSharedPreferences.getString(CONTENT_PREFIX + "current" + "_hash", null);
		String name = this.mSharedPreferences.getString(CONTENT_PREFIX + "current" + "_name", null);
		int fileNumber = this.mSharedPreferences.getInt(CONTENT_PREFIX + "current" + "_fileNumber", 0);
		String folder = this.mSharedPreferences.getString(CONTENT_PREFIX + "current" + "_foler", null);
		String date = this.mSharedPreferences.getString(CONTENT_PREFIX + "current" + "_date", null);
		return new Content(hash,name,fileNumber,folder,date);
	}
	
	private int searchContent(Content content){
		for(int i = 0; i < this.mContentSize; i++){
			String hash = this.mSharedPreferences.getString(CONTENT_PREFIX + i + "_hash", null);
			if(hash.equals(content.mHash)){
				return i;
			}
		}
		return -1;
	}
	
	public static String encryptStr(String text) {
	    // �ϐ�������
	    MessageDigest md = null;
	    StringBuffer buffer = new StringBuffer();
	 
	    try {
	        // ���b�Z�[�W�_�C�W�F�X�g�C���X�^���X�擾
	        md = MessageDigest.getInstance("SHA-256");
	    } catch (NoSuchAlgorithmException e) {
	        // ��O�������A�G���[���b�Z�[�W�o��
	        System.out.println("�w�肳�ꂽ�Í����A���S���Y��������܂���");
	    }
	 
	    // ���b�Z�[�W�_�C�W�F�X�g�X�V
	    md.update(text.getBytes());
	 
	    // �n�b�V���l���i�[
	    byte[] valueArray = md.digest();
	 
	    // �n�b�V���l�̔z������[�v
	    for(int i = 0; i < valueArray.length; i++){
	        // �l�̕����𔽓]�����A16�i���ɕϊ�
	        String tmpStr = Integer.toHexString(valueArray[i] & 0xff);
	 
	        if(tmpStr.length() == 1){
	            // �l���ꌅ�������ꍇ�A�擪��0��ǉ����A�o�b�t�@�ɒǉ�
	            buffer.append('0').append(tmpStr);
	        } else {
	            // ���̑��̏ꍇ�A�o�b�t�@�ɒǉ�
	            buffer.append(tmpStr);
	        }
	    }
	 
	    // ���������n�b�V���v�Z�l��ԋp
	    return buffer.toString();
	}
	
	public class Content{
		private String mHash = null;
		private String mName = null;
		private int mFileNumber = 0;
		private String mFolder = null;
		private String mDate = null;
		Content (String mHash, String name, int fileNumber, String folder, String date){
			this.mHash = mHash;
			this.mName = name;
			this.mFileNumber = fileNumber;
			this.mFolder = folder;
			this.mDate = date;
		}
		Content (String name, int fileNumber, String folder, String date){
			this.mHash = null;
			this.mName = name;
			this.mFileNumber = fileNumber;
			this.mFolder = folder;
			this.mDate = date;
		}
		String getHash(){
			return this.mHash;
		}
		String getName(){
			return this.mName;
		}
		int getFileNumber(){
			return this.mFileNumber;
		}
		String getFolder(){
			return this.mFolder;
		}
		String getDate(){
			return this.mDate;
		}
	}
}
