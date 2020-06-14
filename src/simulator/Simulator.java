/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import simulator.data.*;
import simulator.instruction.FunctionCode;
import simulator.instruction.IFormatInstruction;
import simulator.instruction.Instruction;
import simulator.instruction.JFormatInstruction;
import simulator.instruction.Opcode;
import simulator.instruction.RFormatInstruction;

public class Simulator {
	
	private InstructionMemory instructionMemory;
	private DataMemory dataMemory;
	private Register register;
	private Executer executer;
	
	
	public Simulator() {
		init();
	}
	
	
	public void init() {
		instructionMemory = new InstructionMemory();
		dataMemory = new DataMemory();
		register = new Register();
		executer = new Executer(register, dataMemory);
	}
	
	public boolean readInstructionBinaryFile(String fileName) {
		instructionMemory.init();

		try {
			File file = new File(fileName);
			byte[] data = new byte[(int) file.length()];
			DataInputStream stream = new DataInputStream(new FileInputStream(file));
			
			stream.readFully(data);
			stream.close();
			
			instructionMemory.storeInstruction(data);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void executeInstructions(int limit) {
		int executionCount = 0;
		
		register.init();

		try {

			while (true) {
				byte[] machineCodes = new byte[4];
				Instruction instruction = instructionMemory.getInstruction(register.pcReg);

				executionCount++;


				if (!executer.execute(instruction)) {
					System.out.println("unknown instruction");
					break;
				}

				if (limit == executionCount) {
					break;
				}

				if (executionCount >= InstructionMemory.MAXIMUM_OF_ADDRESS / 4) {
					break;
				}
			}
		} catch (MemoryAddressOutOfRangeException
				| SyscallExitException
				| MemoryAddressAlignmentException
				| InvalidSyscallException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Executed " + executionCount + " instructions");
	}
	
	public void printRegister() {
		for (int i = 0; i < Register.MAXIMUM_OF_REGISTER; i++) {
			System.out.println("$" + i + ": " + String.format("0x%08x", register.reg[i]));
		}
		
		System.out.println("HI: " + String.format("0x%08x", register.hiReg));
		System.out.println("LO: " + String.format("0x%08x", register.loReg));
		System.out.println("PC: " + String.format("0x%08x", register.pcReg));
	}


	public void printInstructions() {
		List<Instruction> instructionList = instructionMemory.getInstructionList();
		
		for (Instruction instruction : instructionList) {
			if (instruction.getOpcode() != Opcode.UNKNOWN) {
				if (instruction.getOpcode() == Opcode.R_FORMAT) {
					RFormatInstruction rFormatInstruction = (RFormatInstruction) instruction;
					
					switch (rFormatInstruction.getFunctionCode()) {
						case ADD:
						case ADDU:
						case AND:
						case NOR:
						case OR:
						case SLT:
						case SLTU:
						case SUB:
						case SUBU:
						case XOR:
							System.out.println(rFormatInstruction.getFunctionCode() + " $"
												+ rFormatInstruction.getDestinationRegister() + ", $"
												+ rFormatInstruction.getFirstSourceRegister() + ", $"
												+ rFormatInstruction.getSecondSourceRegister());
							break;
							
						case MULT:
						case MULTU:
						case DIV:
						case DIVU:
							System.out.println(rFormatInstruction.getFunctionCode() + " $"
												+ rFormatInstruction.getFirstSourceRegister() + ", $"
												+ rFormatInstruction.getSecondSourceRegister());
							break;
							
						case SLL:
						case SRA:
						case SRL:
							System.out.println(rFormatInstruction.getFunctionCode() + " $"
												+ rFormatInstruction.getDestinationRegister() + ", $"
												+ rFormatInstruction.getSecondSourceRegister() + ", "
												+ rFormatInstruction.getShiftAmount());
							break;
							
						case SLLV:
						case SRAV:
						case SRLV:
							System.out.println(rFormatInstruction.getFunctionCode() + " $"
												+ rFormatInstruction.getDestinationRegister() + ", $"
												+ rFormatInstruction.getSecondSourceRegister() + ", $"
												+ rFormatInstruction.getFirstSourceRegister());
							break;
							
						case JALR:
							System.out.println(rFormatInstruction.getFunctionCode() + " $"
												+ rFormatInstruction.getDestinationRegister() + ", $"
												+ rFormatInstruction.getFirstSourceRegister());
							break;
							
						case JR:
						case MTHI:
						case MTLO:
							System.out.println(rFormatInstruction.getFunctionCode() + " $"
												+ rFormatInstruction.getFirstSourceRegister());
							break;
							
						case MFHI:
						case MFLO:
							System.out.println(rFormatInstruction.getFunctionCode() + " $"
												+ rFormatInstruction.getDestinationRegister());
							break;
							
						case SYSCALL:
							System.out.println(rFormatInstruction.getFunctionCode());
							break;
							
						default:
							System.out.println("Unvalid instruction - rFormat");
					}
					
					if (rFormatInstruction.getFunctionCode() == FunctionCode.UNKNOWN) {
						System.out.println("unknown instruction");
						break;
					}
					
				} else {
					if (instruction instanceof IFormatInstruction) {
						IFormatInstruction iFormatInstruction = (IFormatInstruction) instruction;

						switch (iFormatInstruction.getOpcode()) {
							case ADDI:
							case ADDIU:
							case ANDI:
							case ORI:
							case SLTI:
							case SLTIU:
							case XORI:
								System.out.println(iFormatInstruction.getOpcode() + " $"
													+ iFormatInstruction.getDestinationRegister() + ", $"
													+ iFormatInstruction.getSourceRegister() + ", "
													+ iFormatInstruction.getConstant());
								break;
								
							case LUI:
								System.out.println(iFormatInstruction.getOpcode() + " $"
													+ iFormatInstruction.getDestinationRegister() + ", "
													+ iFormatInstruction.getConstant());
								break;
								
							case BEQ:
							case BNE:
								System.out.println(iFormatInstruction.getOpcode() + " $"
													+ iFormatInstruction.getSourceRegister() + ", $"
													+ iFormatInstruction.getDestinationRegister() + ", "
													+ iFormatInstruction.getConstant());
								break;
								
							case LB:
							case LBU:
							case LH:
							case LHU:
							case LW:
							case SB:
							case SH:
							case SW:
								System.out.println(iFormatInstruction.getOpcode() + " $"
													+ iFormatInstruction.getDestinationRegister() + ", "
													+ iFormatInstruction.getConstant() + "($"
													+ iFormatInstruction.getSourceRegister() + ")");
								break;
								
							default:
								System.out.println("Unvalid instruction - iFormat");
						}
					} else if (instruction instanceof JFormatInstruction) {
						JFormatInstruction jFormatInstruction = (JFormatInstruction) instruction;
						
						switch (jFormatInstruction.getOpcode()) {
							case J:
							case JAL:
								System.out.println(jFormatInstruction.getOpcode() + " "
										+ jFormatInstruction.getAddress());
								break;
								
							default:
								System.out.println("Unvalid instruction - jFormat");
						}
					}
				}
			} else {
				System.out.println("Unknown instruction");
				break;
			}
		}
	}
	
	public void readDataMemoryBinaryFile(String fileName) {
		dataMemory.init();

		try {
			File file = new File(fileName);
			byte[] data = new byte[(int) file.length()];
			DataInputStream stream = new DataInputStream(new FileInputStream(file));

			stream.readFully(data);
			stream.close();

			dataMemory.storeData(data);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
