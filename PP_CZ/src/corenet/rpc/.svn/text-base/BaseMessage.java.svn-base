package corenet.rpc;

public class BaseMessage implements IMessage {
	private java.util.HashMap<String, Object> _Params;
	private String _ObjURL;
	private String _Action;

	private static final String HeadStartTag = "<Head>"; 
	private static final String HeadEndTag = "</Head>";

	private static final int HeadStartTagLen = 6; // <Head> 长度
	private static final int HeadEndTagLen = 7; // </Head> 长度

	public BaseMessage() {
		_Params = new java.util.HashMap<String, Object>();
	}

	private boolean Decode(String Message) {
		String Head = "";
		String Body = "";

		try {
			if (!HeadStartTag.equals(Message.substring(0, HeadStartTagLen))) {
				return false;
			}

			int Pos = Message.indexOf(HeadEndTag, HeadStartTagLen);
			if (Pos < 0) {
				return false;
			}

			Head = Message.substring(HeadStartTagLen, Pos);
			Body = Message.substring(Pos + HeadEndTagLen, Pos + HeadEndTagLen
					+ Message.length() - (Pos + HeadEndTagLen));

			DecodeHead(Head);
			DecodeBody(Body);

		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public BaseMessage(String Message) {
		Decode(Message);
	}

	public final void DecodeHead(String Message) {
		int Start = 0;
		String Name, Value;
		int Pos1, Pos2, Pos3;
		_Params = new java.util.HashMap<String, Object>();

		try {
			while (Start < Message.length()) {
				Pos1 = Message.indexOf('<', Start);
				if (Pos1 < 0) {
					break;
				}

				Pos2 = Message.indexOf('>', Pos1 + 1);
				if (Pos2 < 0) {
					break;
				}

				Name = Message.substring(Pos1 + 1, Pos1 + 1 + Pos2 - Pos1 - 1);

				Pos3 = Message.indexOf("</" + Name + ">", Pos2 + 1);

				if (Pos3 < 0) {
					break;
				}

				Value = Message.substring(Pos2 + 1, Pos2 + 1 + Pos3 - Pos2 - 1);

				Start = Pos3 + Name.length() + 3;

				if (Name.equals("ObjURL")) {
					_ObjURL = Value;
				} else if (Name.equals("Action")) {
					_Action = Value;
				}

			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public final void DecodeBody(String Message) {
		int Start = 0;
		String Name, Value;
		int Pos1, Pos2, Pos3;
		_Params = new java.util.HashMap<String, Object>();

		try {
			while (Start < Message.length()) {
				Pos1 = Message.indexOf('<', Start);
				if (Pos1 < 0) {
					break;
				}

				Pos2 = Message.indexOf('>', Pos1 + 1);
				if (Pos2 < 0) {
					break;
				}

				Name = Message.substring(Pos1 + 1, Pos1 + 1 + Pos2 - Pos1 - 1);

				Pos3 = Message.indexOf("</" + Name + ">", Pos2 + 1);

				if (Pos3 < 0) {
					break;
				}

				Value = Message.substring(Pos2 + 1, Pos2 + 1 + Pos3 - Pos2 - 1);

				Start = Pos3 + Name.length() + 3;
				_Params.put(Name, Value);

			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public final void AddParams(java.util.HashMap<String, Object> Params) {
		this._Params = Params;
	}

	public final Object GetParam(String name) {
		try {
			return _Params.get(name);
		} catch (RuntimeException e) {
		}

		return "";
	}

	public final String GetAction() {
		return _Action;
	}

	public final void SetAction(String Action) {
		_Action = Action;
	}

	public final void SetObjURL(String ObjURL) {
		this._ObjURL = ObjURL;
	}

	public final String GetObjURL() {
		return _ObjURL;
	}

	public final void AddParam(String Name, Object Value) {
		_Params.put(Name, Value);
	}

	public final String MessageSrc() {
		return "";
	}

	public final java.util.HashMap<String, Object> GetParams() {
		return _Params;
	}

	public final String Serilize() {
		StringBuffer sMessage = new StringBuffer();

		// String Message = "";
		sMessage.append("<Head>");
		// Message += "<Head>";
		sMessage.append("<Action>").append(_Action).append("</Action>");
		// Message += "<Action>" + _Action + "</Action>";
		sMessage.append("<ObjURL>").append(_ObjURL).append("</ObjURL>");
		// Message += "<ObjURL>" + _ObjURL + "</ObjURL>";
		sMessage.append("</Head>");
		// Message += "</Head>";

		int Len = _Params.size();

		java.util.Iterator<java.util.Map.Entry<String, Object>> iterator = _Params
				.entrySet().iterator();

		while (iterator.hasNext()) {
			java.util.Map.Entry<String, Object> Pair = iterator.next();
			String str;
			if (Pair.getValue() == null)
				str = "";
			else
				str = Pair.getValue().toString();
			sMessage.append("<").append(Pair.getKey()).append(">");
			sMessage.append(str).append("</").append(Pair.getKey()).append(">");
			// Message += "<" + Pair.getKey() + ">" + str + "</" + Pair.getKey()
			// + ">";

		}
		return sMessage.toString();
	}

}
