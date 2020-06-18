package util;

public class GetBookStatic {
	
	public String getBookStatic(int staticInt){
		if(staticInt == 0){
			return "等待发货";
		}else if(staticInt == 1){
			return "已发货";
		}else if(staticInt == 2){
			return "货已送到";
		}
		return null ;
	}
}
