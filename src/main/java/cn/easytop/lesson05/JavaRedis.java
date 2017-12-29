package cn.easytop.lesson05;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class JavaRedis {
	
	/**
	 * 写入数据到Redis时：
	 * 序列化（将对象转成字节数组）
	 * @param obj
	 * @return
	 * @throws IOException 
	 */
	public static byte[] ObjectToByte(Object obj) throws IOException{
		//写入内存流
		ByteOutputStream bos = new ByteOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		os.writeObject(obj);
		return bos.getBytes();
	}
	
	/**
	 * 从Redis中取数据时：
	 * 反序列化
	 * @param bt
	 * @return
	 * @throws IOException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Object ByteToObject(byte[] bt) throws IOException, ClassNotFoundException{
		ByteInputStream bis = new ByteInputStream(bt,bt.length);
		
		ObjectInputStream ois = new ObjectInputStream(bis);	
		return ois.readObject();
	}
	public static void main(String[] args) {
		
	}

	public void del(byte[] objectToByte) {
		// TODO Auto-generated method stub
		
	}

	public void set(byte[] objectToByte, byte[] objectToByte2) {
		// TODO Auto-generated method stub
		
	}

	public byte[] get(byte[] objectToByte) {
		// TODO Auto-generated method stub
		return null;
	}
}
