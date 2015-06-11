package NVMP.jms.base;

public class BaseHeader {
	private byte[] Buffer;

	public BaseHeader() {
		Buffer = new byte[BaseProtocol.HeadSize];
		Buffer[0] = BaseProtocol.Magic[0];
		Buffer[1] = BaseProtocol.Magic[1];
		Buffer[2] = BaseProtocol.Magic[2];
		Buffer[3] = BaseProtocol.Magic[3];
		SetVer(BaseProtocol.CurVer);
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

	private void SetMessageType(short Type) {
		int Offset = BaseProtocol.TypeOffset;

		Buffer[Offset] = (byte) Type;
		Buffer[Offset + 1] = (byte) (Type >> 8);
	}

	private short GetMessageType() {
		int Offset = BaseProtocol.TypeOffset;

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

	public final void DelOption(short Option) {
		Option = (short) ~Option;
		Buffer[BaseProtocol.OptionOffset] &= (byte) Option;
		Buffer[BaseProtocol.OptionOffset + 1] &= (byte) (Option >> 8);
	}

	public final void AddOption(short Option) {
		if (Option == BaseProtocol.OptionNone) {
			return;
		}

		Buffer[BaseProtocol.OptionOffset] |= (byte) Option;
		Buffer[BaseProtocol.OptionOffset + 1] |= (byte) (Option >> 8);

	}

	public final short GetOption() {

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

	public final short getType() {
		return GetMessageType();
	}

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

	public final short getOption() {
		return GetOption();
	}

	public final boolean IsOptionSet(short Option) {
		return (GetOption() & Option) != 0;
	}

}