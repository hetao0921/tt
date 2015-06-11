package corenet.netbase;

import java.util.Arrays;

import corenet.exchange.Encoding;

public class BaseHeader {
	private byte[] Buffer;

	public BaseHeader() {
		Buffer = new byte[BaseProtocol.HeadSize];
		Buffer[0] = BaseProtocol.Magic[0];
		Buffer[1] = BaseProtocol.Magic[1]; 
		Buffer[2] = BaseProtocol.Magic[2];
		Buffer[3] = BaseProtocol.Magic[3];
		SetVer(BaseProtocol.CurVer);
		setID(Encoding.getUuid());
		ClearOption();
	}

	public BaseHeader(byte[] Header) {
		Buffer = Header;
	}

	public final boolean ValidateMagic() {
		return Buffer[0] == BaseProtocol.Magic[0]
				&& Buffer[1] == BaseProtocol.Magic[1]
				&& Buffer[2] == BaseProtocol.Magic[2]
				&& Buffer[3] == BaseProtocol.Magic[3];
	}
	
	public final boolean ValidateVer() {
		return GetVer() == BaseProtocol.CurVer;
	}
	

	public static final boolean validateMagic(byte[] buffer, int offset) {
		return buffer[offset + 0] == BaseProtocol.Magic[0]
				&& buffer[offset + 1] == BaseProtocol.Magic[1]
				&& buffer[offset + 2] == BaseProtocol.Magic[2]
				&& buffer[offset + 3] == BaseProtocol.Magic[3];
	}
	
	public static final boolean validateVer(byte[] buffer, int offset) {
		return buffer[offset + BaseProtocol.VerOffset] == BaseProtocol.CurVer;
	}

	// 鍦ㄨ繖閲屽仛涓や釜澶勭悊锛屼竴涓槸锛屽鍏essionid 锛屼竴涓槸 濉炲叆鐩爣锛屽彲鑳芥槸涓�釜缁勶紝涔熷彲鑳芥槸涓�釜鐙珛id锛岀敋鑷冲彲鑳芥槸ALL
	public final String getTarget() {
		// 鑾峰緱瀛楃涓查暱搴︾殑璇�
		int lengthoffset = BaseProtocol.TargetLengthOffset;
		int length = Buffer[lengthoffset];

		int Offset = BaseProtocol.TargetOffset;
		byte[] target = Arrays.copyOfRange(Buffer, Offset, length + Offset);
		return Encoding.byteToString(target);
	}

	public final void setTarget(String target) {

		int Offset = BaseProtocol.TargetOffset;
		if (target == null)
			return;
		int lengthoffset = BaseProtocol.TargetLengthOffset;
		Buffer[lengthoffset] = (byte) Math.min(target.length(),
				BaseProtocol.TargetSize);

		byte[] barray = Encoding.StringToByte(target);
		for (int i = Offset; i < Offset+Math.min(target.length(),
				BaseProtocol.TargetSize); i++) {
			Buffer[i] = barray[i-Offset];
		}
	}

	// 濉炲叆鏉ユ簮
	public final String getSource() {
		int lengthoffset = BaseProtocol.SourceLengthOffset;
		int length = Buffer[lengthoffset];

		int Offset = BaseProtocol.SourceOffset;
		byte[] target = Arrays.copyOfRange(Buffer, Offset, length + Offset);
		return Encoding.byteToString(target);
	}

	public final void setSource(String source) {
		int Offset = BaseProtocol.SourceOffset;

		if (source == null)
			return;
		int lengthoffset = BaseProtocol.SourceLengthOffset;
		Buffer[lengthoffset] = (byte) Math.min(source.length(),
				BaseProtocol.SourceSize);
		byte[] barray = Encoding.StringToByte(source);
		for (int i = Offset; i < Offset+Math.min(source.length(),
				BaseProtocol.SourceSize); i++) {
			Buffer[i] = barray[i-Offset];
		}
	}
	
	//璁剧疆ID鍜岃幏鍙朓D
	public String getID(){
		int Offset = BaseProtocol.IDOffset;
		int length = BaseProtocol.IDSize;
		byte[] target = Arrays.copyOfRange(Buffer, Offset, length + Offset);
		return Encoding.byteToString(target);
	}
	
	
	public void setID(String uuid) {
		int Offset = BaseProtocol.IDOffset;
		if (uuid == null)
			return;
		if (uuid.length()!=BaseProtocol.IDSize)
			return;
		byte[] barray = Encoding.StringToByte(uuid);
		for (int i = Offset; i < Offset+BaseProtocol.IDSize; i++) {
			Buffer[i] = barray[i-Offset];
		}
		
	}
	
	
	public void setOther(int length) {
		
		int Offset = BaseProtocol.OtherOffset;
		Buffer[Offset] = (byte) (length);
		Buffer[Offset + 1] = (byte) (length >> 8);
		Buffer[Offset + 2] = (byte) (length >> 16);
		Buffer[Offset + 3] = (byte) (length >> 24);
		
		
	}
	
	public int getOther() {

		int Offset = BaseProtocol.OtherOffset;

		int Length = Byte2Int(Buffer[Offset])
				| Byte2Int(Buffer[Offset + 1]) << 8 & 0xff00
				| Byte2Int(Buffer[Offset + 2]) << 16 & 0xff0000
				| Byte2Int(Buffer[Offset + 3]) << 24 & 0xff000000;
		if (Length < 0)
			System.out.println(Length);

		return Length;
	}

	
	
	
	

	private void SetLength(int Length) {
		int Offset = BaseProtocol.LengthOffset;
		Buffer[Offset] = (byte) (Length);
		Buffer[Offset + 1] = (byte) (Length >> 8);
		Buffer[Offset + 2] = (byte) (Length >> 16);
		Buffer[Offset + 3] = (byte) (Length >> 24);
	}

	private int Byte2Int(byte b) {
		if (b >= 0)
			return b;

		return (b & 0x7f) + 0x80;
	}

	private int GetLegnth() {

		int Offset = BaseProtocol.LengthOffset;

		int Length = Byte2Int(Buffer[Offset])
				| Byte2Int(Buffer[Offset + 1]) << 8 & 0xff00
				| Byte2Int(Buffer[Offset + 2]) << 16 & 0xff0000
				| Byte2Int(Buffer[Offset + 3]) << 24 & 0xff000000;
		if (Length < 0)
			System.out.println(Length);

		return Length;
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: void SetMessageType(UInt16 Type)
	private void SetMessageType(short Type) {
		int Offset = BaseProtocol.TypeOffset;

		Buffer[Offset] = (byte) Type;
		Buffer[Offset + 1] = (byte) (Type >> 8);
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: UInt16 GetMessageType()
	private short GetMessageType() {
		int Offset = BaseProtocol.TypeOffset;

		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: UInt16 Type = Buffer[Offset];
		short Type = Buffer[Offset];
		Type += (short) (Buffer[Offset + 1] << 8 & 0xff00);

		return Type;
	}

	private byte GetPriority() {
		return Buffer[BaseProtocol.PriorityOffset];
	}

	private void SetPriority(byte Priority) {
		Buffer[BaseProtocol.PriorityOffset] = Priority;
	}

	private byte GetVer() {
		return Buffer[BaseProtocol.VerOffset];
	}

	private void SetVer(byte ver) {
		Buffer[BaseProtocol.VerOffset] = ver;
	}

	public final void ClearOption() {
		Buffer[BaseProtocol.OptionOffset] = 0;
		Buffer[BaseProtocol.OptionOffset + 1] = 0;
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public void DelOption(UInt16 Option)
	public final void DelOption(short Option) {
		Option = (short) ~Option;
		Buffer[BaseProtocol.OptionOffset] &= (byte) Option;
		Buffer[BaseProtocol.OptionOffset + 1] &= (byte) (Option >> 8);
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public void AddOption(UInt16 Option)
	public final void AddOption(short Option) {
		if (Option == BaseProtocol.OptionNone) {
			return;
		}

		Buffer[BaseProtocol.OptionOffset] |= (byte) Option;
		Buffer[BaseProtocol.OptionOffset + 1] |= (byte) (Option >> 8);

	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public UInt16 GetOption()
	public final short GetOption() {
		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: UInt16 Option =
		// (UInt16)(Buffer[BaseProtocol.OptionOffset]);
		short Option = (short) (Buffer[BaseProtocol.OptionOffset]);
		Option += (short) ((short) Buffer[BaseProtocol.OptionOffset + 1] << 8 & 0xff00);
		return Option;
	}

	public final int getBodyLength() {
		return GetLegnth();
	}

	public final void setBodyLength(int value) {
		SetLength(value);
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public UInt16 getType()
	public final short getType() {
		return GetMessageType();
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public void setType(UInt16 value)
	public final void setType(short value) {
		SetMessageType(value);
	}

	public final byte getPriority() {
		return GetPriority();
	}

	public final void setPriority(byte value) {
		SetPriority(value);
	}

	public final byte getVer() {
		return GetVer();
	}

	public final void setVer(byte value) {
		SetVer(value);
	}

	public final byte[] getData() {
		return Buffer;
	}

	public final void setData(byte[] value) {
		Buffer = value;
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public UInt16 getOption()
	public final short getOption() {
		return GetOption();
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public bool IsOptionSet(UInt16 Option)
	public final boolean IsOptionSet(short Option) {
		return (GetOption() & Option) != 0;
	}

	public final AsyncBuffer ToAsyncBuffer() {
		return new AsyncBuffer(Buffer, false);
	}
}