/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.data;

public class Register {
	
	public static final int MAXIMUM_OF_REGISTER = 32;
	
	public static final int ZERO = 0;
	public static final int RESULTS = 2;
	public static final int FIRST_ARGUMENT = 4;
	public static final int RETURN_ADDRESS = 31;
	
	public int[] reg;
	public int pcReg;
	public int hiReg;
	public int loReg;
	

	public Register() {
		reg = new int[MAXIMUM_OF_REGISTER];
		
		init();
	}
	
	
	public void init() {
		for (int i = 0; i < MAXIMUM_OF_REGISTER; i++) {
			reg[i]= 0; 
		}
		
		pcReg = 0;
	}
}
