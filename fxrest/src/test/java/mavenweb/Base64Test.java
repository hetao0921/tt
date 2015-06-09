package mavenweb;

import com.alibaba.fastjson.util.Base64;

import sun.misc.BASE64Encoder;

public class Base64Test {
	public static void main(String[] args) {
		String json="{\"groupId\":\"64860fba-b058-4fb7-aaaf-02e11c20d2b9\",\"groupName\":\"默认全局分组\",\"groupType\":\"Default\",\"userId\":\"0000\",\"centerId\":\"B8975A5FEA2B@001\",\"members\":[{\"resourceId\":\"bbfef5aa179348c689c7b119e62157d4\",\"resourceName\":\"251\",\"resourceType\":\"Department\",\"parentId\":\"64860fba-b058-4fb7-aaaf-02e11c20d2b9\",\"sortIndex\":0,\"attributes\":[]},{\"resourceId\":\"c407a8f433064ecd958beb976c2c9502\",\"resourceName\":\"ds001\",\"resourceType\":\"Commander\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":0,\"attributes\":[]},{\"resourceId\":\"464926cb9165401ca10254f8c7343d52\",\"resourceName\":\"ds002\",\"resourceType\":\"Commander\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":1,\"attributes\":[]},{\"resourceId\":\"2a5312d6c50c4864bd07ed0f07874b70\",\"resourceName\":\"ds003\",\"resourceType\":\"Commander\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":2,\"attributes\":[]},{\"resourceId\":\"5b6ce053858d4401b6d29411c004b91c#0\",\"resourceName\":\"170[摄像机01]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":3,\"attributes\":[]},{\"resourceId\":\"5b6ce053858d4401b6d29411c004b91c#1\",\"resourceName\":\"170[摄像机02]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":4,\"attributes\":[]},{\"resourceId\":\"5b6ce053858d4401b6d29411c004b91c#2\",\"resourceName\":\"170[摄像机03]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":5,\"attributes\":[]},{\"resourceId\":\"5b6ce053858d4401b6d29411c004b91c#3\",\"resourceName\":\"170[摄像机04]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":6,\"attributes\":[]},{\"resourceId\":\"5b6ce053858d4401b6d29411c004b91c#4\",\"resourceName\":\"170[摄像机05]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":7,\"attributes\":[]},{\"resourceId\":\"4dc300f6e8764ed580fe3b159482fe78#0\",\"resourceName\":\"141[摄像机01]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":8,\"attributes\":[]},{\"resourceId\":\"4dc300f6e8764ed580fe3b159482fe78#1\",\"resourceName\":\"141[摄像机02]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":9,\"attributes\":[]},{\"resourceId\":\"4dc300f6e8764ed580fe3b159482fe78#2\",\"resourceName\":\"141[摄像机03]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":10,\"attributes\":[]},{\"resourceId\":\"4dc300f6e8764ed580fe3b159482fe78#3\",\"resourceName\":\"141[摄像机04]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":11,\"attributes\":[]},{\"resourceId\":\"4dc300f6e8764ed580fe3b159482fe78#4\",\"resourceName\":\"141[摄像机05]\",\"resourceType\":\"Camera\",\"parentId\":\"bbfef5aa179348c689c7b119e62157d4\",\"sortIndex\":12,\"attributes\":[]}]}";
	
		BASE64Encoder encode=new BASE64Encoder();
		json=encode.encode(json.getBytes());
		
		String eq="eyJncm91cElkIjoiNjQ4NjBmYmEtYjA1OC00ZmI3LWFhYWYtMDJlMTFjMjBkMmI5IiwiZ3JvdXBO"+
"YW1lIjoiPz8/Pz8/IiwiZ3JvdXBUeXBlIjoiRGVmYXVsdCIsInVzZXJJZCI6IjAwMDAiLCJjZW50"+
"ZXJJZCI6IkI4OTc1QTVGRUEyQkAwMDEiLCJtZW1iZXJzIjpbeyJyZXNvdXJjZUlkIjoiYmJmZWY1"+
"YWExNzkzNDhjNjg5YzdiMTE5ZTYyMTU3ZDQiLCJyZXNvdXJjZU5hbWUiOiIyNTEiLCJyZXNvdXJj"+
"ZVR5cGUiOiJEZXBhcnRtZW50IiwicGFyZW50SWQiOiI2NDg2MGZiYS1iMDU4LTRmYjctYWFhZi0w"+
"MmUxMWMyMGQyYjkiLCJzb3J0SW5kZXgiOjAsImF0dHJpYnV0ZXMiOltdfSx7InJlc291cmNlSWQi"+
"OiJjNDA3YThmNDMzMDY0ZWNkOTU4YmViOTc2YzJjOTUwMiIsInJlc291cmNlTmFtZSI6ImRzMDAx"+
"IiwicmVzb3VyY2VUeXBlIjoiQ29tbWFuZGVyIiwicGFyZW50SWQiOiJiYmZlZjVhYTE3OTM0OGM2"+
"ODljN2IxMTllNjIxNTdkNCIsInNvcnRJbmRleCI6MCwiYXR0cmlidXRlcyI6W119LHsicmVzb3Vy"+
"Y2VJZCI6IjQ2NDkyNmNiOTE2NTQwMWNhMTAyNTRmOGM3MzQzZDUyIiwicmVzb3VyY2VOYW1lIjoi"+
"ZHMwMDIiLCJyZXNvdXJjZVR5cGUiOiJDb21tYW5kZXIiLCJwYXJlbnRJZCI6ImJiZmVmNWFhMTc5"+
"MzQ4YzY4OWM3YjExOWU2MjE1N2Q0Iiwic29ydEluZGV4IjoxLCJhdHRyaWJ1dGVzIjpbXX0seyJy"+
"ZXNvdXJjZUlkIjoiMmE1MzEyZDZjNTBjNDg2NGJkMDdlZDBmMDc4NzRiNzAiLCJyZXNvdXJjZU5h"+
"bWUiOiJkczAwMyIsInJlc291cmNlVHlwZSI6IkNvbW1hbmRlciIsInBhcmVudElkIjoiYmJmZWY1"+
"YWExNzkzNDhjNjg5YzdiMTE5ZTYyMTU3ZDQiLCJzb3J0SW5kZXgiOjIsImF0dHJpYnV0ZXMiOltd"+
"fSx7InJlc291cmNlSWQiOiI1YjZjZTA1Mzg1OGQ0NDAxYjZkMjk0MTFjMDA0YjkxYyMwIiwicmVz"+
"b3VyY2VOYW1lIjoiMTcwWz8/PzAxXSIsInJlc291cmNlVHlwZSI6IkNhbWVyYSIsInBhcmVudElk"+
"IjoiYmJmZWY1YWExNzkzNDhjNjg5YzdiMTE5ZTYyMTU3ZDQiLCJzb3J0SW5kZXgiOjMsImF0dHJp"+
"YnV0ZXMiOltdfSx7InJlc291cmNlSWQiOiI1YjZjZTA1Mzg1OGQ0NDAxYjZkMjk0MTFjMDA0Yjkx"+
"YyMxIiwicmVzb3VyY2VOYW1lIjoiMTcwWz8/PzAyXSIsInJlc291cmNlVHlwZSI6IkNhbWVyYSIs"+
"InBhcmVudElkIjoiYmJmZWY1YWExNzkzNDhjNjg5YzdiMTE5ZTYyMTU3ZDQiLCJzb3J0SW5kZXgi"+
"OjQsImF0dHJpYnV0ZXMiOltdfSx7InJlc291cmNlSWQiOiI1YjZjZTA1Mzg1OGQ0NDAxYjZkMjk0"+
"MTFjMDA0YjkxYyMyIiwicmVzb3VyY2VOYW1lIjoiMTcwWz8/PzAzXSIsInJlc291cmNlVHlwZSI6"+
"IkNhbWVyYSIsInBhcmVudElkIjoiYmJmZWY1YWExNzkzNDhjNjg5YzdiMTE5ZTYyMTU3ZDQiLCJz"+
"b3J0SW5kZXgiOjUsImF0dHJpYnV0ZXMiOltdfSx7InJlc291cmNlSWQiOiI1YjZjZTA1Mzg1OGQ0"+
"NDAxYjZkMjk0MTFjMDA0YjkxYyMzIiwicmVzb3VyY2VOYW1lIjoiMTcwWz8/PzA0XSIsInJlc291"+
"cmNlVHlwZSI6IkNhbWVyYSIsInBhcmVudElkIjoiYmJmZWY1YWExNzkzNDhjNjg5YzdiMTE5ZTYy"+
"MTU3ZDQiLCJzb3J0SW5kZXgiOjYsImF0dHJpYnV0ZXMiOltdfSx7InJlc291cmNlSWQiOiI1YjZj"+
"ZTA1Mzg1OGQ0NDAxYjZkMjk0MTFjMDA0YjkxYyM0IiwicmVzb3VyY2VOYW1lIjoiMTcwWz8/PzA1"+
"XSIsInJlc291cmNlVHlwZSI6IkNhbWVyYSIsInBhcmVudElkIjoiYmJmZWY1YWExNzkzNDhjNjg5"+
"YzdiMTE5ZTYyMTU3ZDQiLCJzb3J0SW5kZXgiOjcsImF0dHJpYnV0ZXMiOltdfSx7InJlc291cmNl"+
"SWQiOiI0ZGMzMDBmNmU4NzY0ZWQ1ODBmZTNiMTU5NDgyZmU3OCMwIiwicmVzb3VyY2VOYW1lIjoi"+
"MTQxWz8/PzAxXSIsInJlc291cmNlVHlwZSI6IkNhbWVyYSIsInBhcmVudElkIjoiYmJmZWY1YWEx"+
"NzkzNDhjNjg5YzdiMTE5ZTYyMTU3ZDQiLCJzb3J0SW5kZXgiOjgsImF0dHJpYnV0ZXMiOltdfSx7"+
"InJlc291cmNlSWQiOiI0ZGMzMDBmNmU4NzY0ZWQ1ODBmZTNiMTU5NDgyZmU3OCMxIiwicmVzb3Vy"+
"Y2VOYW1lIjoiMTQxWz8/PzAyXSIsInJlc291cmNlVHlwZSI6IkNhbWVyYSIsInBhcmVudElkIjoi"+
"YmJmZWY1YWExNzkzNDhjNjg5YzdiMTE5ZTYyMTU3ZDQiLCJzb3J0SW5kZXgiOjksImF0dHJpYnV0"+
"ZXMiOltdfSx7InJlc291cmNlSWQiOiI0ZGMzMDBmNmU4NzY0ZWQ1ODBmZTNiMTU5NDgyZmU3OCMy"+
"IiwicmVzb3VyY2VOYW1lIjoiMTQxWz8/PzAzXSIsInJlc291cmNlVHlwZSI6IkNhbWVyYSIsInBh"+
"cmVudElkIjoiYmJmZWY1YWExNzkzNDhjNjg5YzdiMTE5ZTYyMTU3ZDQiLCJzb3J0SW5kZXgiOjEw"+
"LCJhdHRyaWJ1dGVzIjpbXX0seyJyZXNvdXJjZUlkIjoiNGRjMzAwZjZlODc2NGVkNTgwZmUzYjE1"+
"OTQ4MmZlNzgjMyIsInJlc291cmNlTmFtZSI6IjE0MVs/Pz8wNF0iLCJyZXNvdXJjZVR5cGUiOiJD"+
"YW1lcmEiLCJwYXJlbnRJZCI6ImJiZmVmNWFhMTc5MzQ4YzY4OWM3YjExOWU2MjE1N2Q0Iiwic29y"+
"dEluZGV4IjoxMSwiYXR0cmlidXRlcyI6W119LHsicmVzb3VyY2VJZCI6IjRkYzMwMGY2ZTg3NjRl"+
"ZDU4MGZlM2IxNTk0ODJmZTc4IzQiLCJyZXNvdXJjZU5hbWUiOiIxNDFbPz8/MDVdIiwicmVzb3Vy"+
"Y2VUeXBlIjoiQ2FtZXJhIiwicGFyZW50SWQiOiJiYmZlZjVhYTE3OTM0OGM2ODljN2IxMTllNjIx"+
"NTdkNCIsInNvcnRJbmRleCI6MTIsImF0dHJpYnV0ZXMiOltdfV19";
		System.out.println(json.equals(eq));
		json =new String(Base64.decodeFast(eq));
		System.out.println(json);
		
		String str1=" 阿斯达克   ";
		
		str1=	encode.encode(str1.getBytes());
		str1 =new String(Base64.decodeFast(str1));
		System.out.println(str1);
	}

}
