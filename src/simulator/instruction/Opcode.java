/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.instruction;

public enum Opcode {
	R_FORMAT(0x0, ""),
	ADDI(0x8, "addi"),
	ADDIU(0x9, "addiu"),
	ANDI(0xC, "andi"),
	LUI(0xF, "lui"),
	ORI(0xD, "ori"),
	SLTI(0xA, "slti"),
	SLTIU(0xB, "sltiu"),
	XORI(0xE, "xori"),
	BEQ(0x4, "beq"),
	BNE(0x5, "bne"),
	LB(0x20, "lb"),
	LBU(0x24, "lbu"),
	LH(0x21, "lh"),
	LHU(0x25, "lhu"),
	LW(0x23, "lw"),
	SB(0x28, "sb"),
	SH(0x29, "sh"),
	SW(0x2B, "sw"),
	J(0x2, "j"),
	JAL(0x3, "jal"),
	UNKNOWN(999999, "");
	
	private int code;
	private String name;
	
	
	private Opcode(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	
	public static Opcode intToOpcode(int binaryCode) {
		for (Opcode opcode : Opcode.values()) {
			if (opcode.code == binaryCode) {
				return opcode;
			}
		}
		
		return UNKNOWN;
	}
	
	public String toString() {
		return name;
	}
}
