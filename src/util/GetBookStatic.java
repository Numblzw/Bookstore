package util;

public class GetBookStatic {
	
	public String getBookStatic(int staticInt){
		if(staticInt == 0){
			return "�ȴ�����";
		}else if(staticInt == 1){
			return "�ѷ���";
		}else if(staticInt == 2){
			return "�����͵�";
		}
		return null ;
	}
}
