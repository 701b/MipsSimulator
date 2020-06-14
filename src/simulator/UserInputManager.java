/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator;

import java.util.Scanner;

public class UserInputManager {
	
	public static void main(String... args) {
		Simulator simulator = new Simulator();
		Scanner scanner = new Scanner(System.in);
		String inputStr;
		String[] inputStrs;
		
		while (true) {
			System.out.print("mips-sim> ");
			
			inputStr = scanner.nextLine();
			inputStrs = inputStr.split(" ");
			
			if (inputStrs[0].equals("loadinst")) {
				simulator.readInstructionBinaryFile(inputStrs[1]);
			} else if (inputStrs[0].equals("run")) {
				simulator.executeInstructions(Integer.parseInt(inputStrs[1]));
			} else if (inputStrs[0].equals("registers")) {
				simulator.printRegister();
			} else if (inputStrs[0].equals("read")) {
				if (inputStrs.length == 2) {
					if (simulator.readInstructionBinaryFile(inputStrs[1])) {
						simulator.printInstructions();
					}
				} else {
					simulator.printInstructions();
				}
			} else if (inputStrs[0].equals("loaddata")) {
				simulator.readDataMemoryBinaryFile(inputStrs[1]);
			}
		}
		
		//scanner.close();
	}
	
}