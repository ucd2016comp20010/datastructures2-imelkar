package project20280.stacksqueues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertFromDecimalToBase{
    public ConvertFromDecimalToBase(){}
    public String convert(long dec_num, int base){
        LinkedStack<Character> resultStack = new LinkedStack<Character>();
        if(dec_num == 0){
            return "0";
        }
        long r;
        while(dec_num != 0){
            r = dec_num % base;
            if(r < 10) {
                resultStack.push((char) (r+48));
            }
            else if(r < 36) {
                resultStack.push((char) (r+55));
            }
            else{
                throw new IllegalArgumentException("Base is too big, sorry");
            }
            dec_num /= base;
        }
        String result = "";
        while(!resultStack.isEmpty()){
            result += resultStack.pop();
        }
        return result;
    }

    @Test
    void testConvertToBinary() {
//        assertEquals("10111", convertToBinary(23));
//        assertEquals("111001000000101011000010011101010110110001100010000000000000",
//                convertToBinary(1027010000000000000L));
    }

    @Test
    void testConvertToBaseBinary() {
        assertEquals("10111", convert(23, 2));
        assertEquals("111001000000101011000010011101010110110001100010000000000000",
                convert(1027010000000000000L, 2));
    }

    @Test
    void testConvertToBaseHex() {
        assertEquals("2D", convert(45, 16));
        assertEquals("E40AC2756C62000", convert(1027010000000000000L, 16));
    }
}