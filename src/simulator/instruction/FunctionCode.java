/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.instruction;

public enum FunctionCode {
	ADD(0x20, "add"),
	ADDU(0x21, "addu"),
	AND(0x24, "and"),
	NOR(0x27, "nor"),
	OR(0x25, "or"),
	SLT(0x2A, "slt"),
	SLTU(0x2B, "sltu"),
	SUB(0x22, "sub"),
	SUBU(0x23, "subu"),
	XOR(0x26, "xor"),
	SLL(0x0, "sll"),
	SLLV(0x4, "sllv"),
	SRA(0x3, "sra"),
	SRAV(0x7, "srav"),
	SRL(0x2, "srl"),
	SRLV(0x6, "srlv"),
	DIV(0x1A, "div"),
	DIVU(0x1B, "divu"),
	JALR(0x9, "jalr"),
	JR(0x8, "jr"),
	MFHI(0x10, "mfhi"),
	MFLO(0x12, "mflo"),
	MTHI(0x11, "mthi"),
	MTLO(0x13, "mtlo"),
	MULT(0x18, "mult"),
	MULTU(0x19, "multu"),
	SYSCALL(0xC, "syscall"),
	UNKNOWN(999999, "");
	
	private int code;
	private String name;
	
	
	private FunctionCode(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	
	public static FunctionCode intToFunctionCode(int code) {
		for (FunctionCode functionCode : FunctionCode.values()) {
			if (functionCode.code == code) {
				return functionCode;
			}
		}
		
		return UNKNOWN;
	}
	
	public String toString() {
		return name;
	}
}
