/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator;

import simulator.instruction.IFormatInstruction;
import simulator.instruction.Instruction;
import simulator.instruction.JFormatInstruction;
import simulator.instruction.Opcode;
import simulator.instruction.RFormatInstruction;

public class Converter {

	private Converter() {}

	
	public static int binaryStrToDecimalInt(String binaryCode) {
		return Integer.parseInt(binaryCode, 2);
	}
	
	public static byte binaryStrToByte(String binaryStr) {
		return Byte.parseByte(binaryStr);
	}
	
	public static byte[] binaryStrToFourByte(String binaryStr) {
		byte[] decimals = new byte[4];
		int temp = binaryStrToDecimalInt(binaryStr);
		
		for (int i = 0; i < 4; i++) {
			decimals[i] = (byte) ((temp >> (8 * (3 - i))) & 0xFF);
		}
		
		return decimals;
	}
	
	public static int fourByteToInt(byte[] bytes) {
		int num = 0;
				
		num += (bytes[0] & 0xFF) << 24;
		num += (bytes[1] & 0xFF) << 16;
		num += (bytes[2] & 0xFF) << 8;
		num += (bytes[3] & 0xFF);
		
		return num;
	}
	
}
