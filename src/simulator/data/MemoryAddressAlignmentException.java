/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.data;

public class MemoryAddressAlignmentException extends RuntimeException {
    public int address;

    public MemoryAddressAlignmentException(int address) {
        super("Memory address alignment error: " + String.format("0x%08x", address));

        this.address = address;
    }
}
