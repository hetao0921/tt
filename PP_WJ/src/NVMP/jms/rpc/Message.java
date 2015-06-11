package NVMP.jms.rpc;


public class Message 
{
	private java.util.HashMap<String, Object> _Params;
	private String _Operator; //这个是哪一个家伙上传的，防止重复操作。
	private String _Url;      // 表明这个消息和这个消息所携带的数据该如何处理。

	private static final String HeadStartTag = "<Head>";
	private static final String HeadEndTag = "</Head>";

	private static final int HeadStartTagLen = 6; // <Head> 
	private static final int HeadEndTagLen = 7; // </Head> 

	public Message()
	{
		_Params = new java.util.HashMap<String, Object>();
	}

	private boolean Decode(String Message)
	{
		String Head = "";
		String Body = "";

		try
		{
			if (!HeadStartTag.equals(Message.substring(0, HeadStartTagLen)))
			{
				return false;
			}

			int Pos = Message.indexOf(HeadEndTag, HeadStartTagLen);
			if (Pos < 0)
			{
				return false;
			}

			Head = Message.substring(HeadStartTagLen, Pos);
			Body = Message.substring(Pos + HeadEndTagLen, Pos + HeadEndTagLen + Message.length() - (Pos + HeadEndTagLen));

			DecodeHead(Head);
			DecodeBody(Body);

		}
		catch (RuntimeException e)
		{
			return false;
		}


		return true;
	}

	public Message(String Message)
	{
		Decode(Message);
	}

	public final void DecodeHead(String Message)
	{
		int Start = 0;
		String Name, Value;
		int Pos1, Pos2, Pos3;
		_Params = new java.util.HashMap<String, Object>();

		try
		{
			while (Start < Message.length())
			{
				Pos1 = Message.indexOf('<', Start);
				if (Pos1 < 0)
				{
					break;
				}

				Pos2 = Message.indexOf('>', Pos1 + 1);
				if (Pos2 < 0)
				{
					break;
				}

				Name = Message.substring(Pos1 + 1, Pos1 + 1 + Pos2 - Pos1 - 1);

				Pos3 = Message.indexOf("</" + Name + ">", Pos2 + 1);

				if (Pos3 < 0)
				{
					break;
				}

				Value = Message.substring(Pos2 + 1, Pos2 + 1 + Pos3 - Pos2 - 1);

				Start = Pos3 + Name.length() + 3;

				if (Name.equals("Url"))
				{
					_Url = Value;
				}
				else if (Name.equals("Operator"))
				{
					_Operator = Value;
				}

			}
		}
		catch (RuntimeException e)
		{

		}
	}

	public final void DecodeBody(String Message)
	{
		int Start = 0;
		String Name, Value;
		int Pos1, Pos2, Pos3;
		_Params = new java.util.HashMap<String, Object>();

		try
		{
			while (Start < Message.length())
			{
				Pos1 = Message.indexOf('<', Start);
				if (Pos1 < 0)
				{
					break;
				}

				Pos2 = Message.indexOf('>', Pos1 + 1);
				if (Pos2 < 0)
				{
					break;
				}

				Name = Message.substring(Pos1 + 1, Pos1 + 1 + Pos2 - Pos1 - 1);

				Pos3 = Message.indexOf("</" + Name + ">", Pos2 + 1);

				if (Pos3 < 0)
				{
					break;
				}

				Value = Message.substring(Pos2 + 1, Pos2 + 1 + Pos3 - Pos2 - 1);

				Start = Pos3 + Name.length() + 3;
				_Params.put(Name, Value);

			}
		}
		catch (RuntimeException e)
		{

		}
	}


	public final void AddParams(java.util.HashMap<String, Object> Params)
	{
		this._Params = Params;
	}

	public final Object GetParam(String name)
	{
		try
		{
			return _Params.get(name);
		}
		catch (RuntimeException e)
		{
		}

		return "";
	}

	

	public final void AddParam(String Name, Object Value)
	{
		_Params.put(Name, Value);
	}

	public final String MessageSrc()
	{
		return "";
	}

	public final java.util.HashMap<String, Object> GetParams()
	{
		return _Params;
	}

	public final String Serilize()
	{
		String Message = "";
		
		Message += "<Head>";
		Message += "<Url>" + _Url + "</Url>";
		Message += "<Operator>" + _Operator + "</Operator>";
		Message += "</Head>";


//		int Len = _Params.size();

		java.util.Iterator<java.util.Map.Entry<String, Object>> iterator = _Params.entrySet().iterator();

		while (iterator.hasNext())
		{
			java.util.Map.Entry<String, Object> Pair = iterator.next();
		    String str;
			if(Pair.getValue() == null) str = ""; 
			  else str = Pair.getValue().toString();
 
			Message += "<" + Pair.getKey() + ">" + str + "</" + Pair.getKey() + ">";

		}
		return Message;
	}




	public String get_Url() {
		return _Url;
	}

	public void set_Url(String _Url) {
		this._Url = _Url;
	}

	public String get_Operator() {
		return _Operator;
	}

	public void set_Operator(String _Operator) {
		this._Operator = _Operator;
	}


}
