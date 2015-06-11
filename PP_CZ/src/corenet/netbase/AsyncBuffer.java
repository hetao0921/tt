package corenet.netbase;

import java.nio.ByteBuffer;

public class AsyncBuffer 
{
	/**
	 *by zzw
	 * 
	 * ·â×°»º³åÇø£¬¶ÔÓÚÍšµÀ¶øÑÔ£¬Ã»ÓÐÖ±œÓŽŠÀíµÄ£¬Ö»ºÍ»º³åÇøŽòœ»µÀ£¬È»ºó»º³åÇøºÍÍšµÀŽòœ»µÀ¡£
	 * 
	 * ÕâÀïÎÒœšÒé·â×°µÄÊÇnioµÄ ByteBuffer 
	 * 
	 * */
	
	
	
	private byte[] _Data;
	private int _Offset;

//C# TO JAVA CONVERTER TODO TASK: C# optional parameters are not converted to Java:
//ORIGINAL LINE: public AsyncBuffer(byte[] Data, bool ToCopy = false)
	public AsyncBuffer(byte[] Data, boolean ToCopy)
	{
		if (ToCopy)
		{
			_Data = new byte[Data.length];
			//Data.copyTo(_Data, 0);
			/*
			 * by zzw
			 * */			
			_Data = java.util.Arrays.copyOf(Data,Data.length );
		}
		else
		{
			this._Data = Data;
		}

		setOffset(0);
	}

	public AsyncBuffer(int Size)
	{
		_Data = new byte[Size];
		_Offset = 0;
	}
	
	public void addByte(byte[] bs) {
		if(bs == null) return;
		for(int i=_Offset;i<_Offset+bs.length;i++ ) {
			_Data[i] = bs[i-_Offset];			
		}
		_Offset = _Offset+bs.length;
	}
	
	
	
	
	

	public final int getOffset()
	{
		return _Offset;
	}
	public final void setOffset(int value)
	{
		_Offset = value;
	}

	public final byte[] getData()
	{
		return _Data;
	}

	public final int getSize()
	{
		return _Data.length;
	}

	public final void Reset()
	{
		_Offset = 0;
	}

	public ByteBuffer getByteBuffer() {	
		return ByteBuffer.wrap(_Data);			
	}
	
	public void putByteBuffer(ByteBuffer bbf) {
		this._Data = bbf.array();
		
	}
	
	
	
	
}
