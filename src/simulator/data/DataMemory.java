/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.data;

public class DataMemory {
	
	public static final int BASE_OF_ADDRESS = 0x10000000;
	public static final int MAXIMUM_OF_ADDRESS = 0x10010000;
	
	private byte[] memory;
	
	
	public DataMemory() {
		memory = new byte[MAXIMUM_OF_ADDRESS - BASE_OF_ADDRESS];
		
		init();
	}


	public void init() {
		for (int i = 0; i < MAXIMUM_OF_ADDRESS - BASE_OF_ADDRESS; i++) {
			memory[i] = (byte) 0xFF;
		}
	}
	
	public void storeData(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			memory[i] = bytes[i];
		}
	}

	public byte getMemory(int address) {
		return memory[address - BASE_OF_ADDRESS];
	}

	public void setMemory(byte data, int address) {
		memory[address - BASE_OF_ADDRESS] = data;
	}
	
}
