/**
 * 
 */
package bean;

/**
 * @author Danny
 *
 */
public class ValuePassTest {

	/**
	 * @description 
	 * @author Danny
	 * @date 2016年6月30日 上午9:58:13
	 * @version 
	 */
	
	private static int j = 10;
	
	public static void main(String[] args) {
		int n = 3;
		 System.out.println("Before change, n = " + n);
		 changeData(n);
		 System.out.println("After changeData(n), n = " + n);
		 
		 StringBuffer sb = new StringBuffer("Hello ");
	        System.out.println("Before change, sb = " + sb);
	        changeData(sb, 1);
	        System.out.println("After changeData(n), sb = " + sb);
	        
	        StringBuffer sb_new = new StringBuffer("Hello ");
	        System.out.println("Before change, sb = " + sb_new);
	        changeData(sb_new, 2);
	        System.out.println("After changeData(n), sb = " + sb_new);
	        
	        System.out.println("Before change, j = " + j);
	        j = 5;
	        System.out.println("After change, j = " + j);
	}
	
	public static void changeData(int nn) {
		 nn = 10;
		 }
	
	public static void changeData(StringBuffer strBuf, int from) {
		if (from==1) {
			strBuf.append("World!");
		} else {
			strBuf = new StringBuffer("Hi ");
	           strBuf.append("World!");
		}
    }
}
