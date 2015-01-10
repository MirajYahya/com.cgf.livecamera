package com.cgf.livecamera.utility;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.content.res.AssetManager;

public class Extractor {
	private Context mContext = null;
	private String mName = null;
	private String mFolder = "datadir/";
	
	public Extractor(Context context, String name){
		this.mContext = context;
		this.mName = name;
	}
	
	public boolean getFile(){
		String path = null;
		
		try {
			AssetManager	am	= mContext.getResources().getAssets();
			InputStream	is	= am.open(this.mFolder + this.mName, AssetManager.ACCESS_STREAMING);
			ZipInputStream	zis	= new ZipInputStream(is);
			ZipEntry		ze	= zis.getNextEntry();

			if (ze != null) {
				path = mContext.getFilesDir().toString() + "/" + ze.getName();
				FileOutputStream fos = new FileOutputStream(path, false);
				byte[] buf = new byte[1024];
				int size = 0;

				while ((size = zis.read(buf, 0, buf.length)) > -1) {
					fos.write(buf, 0, size);
				}
				fos.close();
				zis.closeEntry();
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
