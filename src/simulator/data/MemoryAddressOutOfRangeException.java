/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.data;

public class MemoryAddressOutOfRangeException extends RuntimeException {
    public int address;

    public MemoryAddressOutOfRangeException(int address) {
        super("Memory address out of range: " + String.format("0x%08x",address));

        this.address = address;
    }
}
