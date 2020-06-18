package util;

public class GetAskForBookStatic {
	public String getAskForBookStatic(int staticInt){
		if(staticInt == 0){
			return "尚无此书";
		}else if(staticInt == 1){
			return "书已到";
		}
		return null;
	}
}
