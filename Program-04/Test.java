import java.util.Scanner;

class CRC
{
    private String gp;

    public CRC(String gp)
    {
        this.gp = gp;
    }

    String computeChecksum(String data)
    {
        char tempDataCRC[] = data.toCharArray();
        int gpLen = gp.length();
        int noOfIteration = tempDataCRC.length - gpLen;

        for (int i = 0; i <= noOfIteration; i++)
        {
            if (tempDataCRC[i] == '1')
            {
                for (int j = 0; j < gpLen; j++)
                {
                    if (tempDataCRC[i + j] == gp.charAt(j))
                        tempDataCRC[i + j] = '0';
                    else
                        tempDataCRC[i + j] = '1';
                }
            }
        }

        int crcLength = gpLen - 1;
        String crc = new String(tempDataCRC, tempDataCRC.length - crcLength, crcLength);
        return crc;
    }

    String getDataCrc(String data, int zeroCount)
    {
        StringBuilder zeros = new StringBuilder();

        for (int i = 0; i < zeroCount; i++)
            zeros.append("0");

        String paddedData = data + zeros.toString();
        String crc = computeChecksum(paddedData);

        return data + crc;
    }

    public boolean validate(String dataCrc)
    {
        String rem = computeChecksum(dataCrc);

        for (int i = 0; i < rem.length(); i++)
        {
            if (rem.charAt(i) == '1')
                return false;
        }

        return true;
    }
}

public class Test
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Data bits: ");
        String data = input.next();

        System.out.println("Enter Codeword length: ");
        int codewordLength = input.nextInt();

        System.out.println("Enter Divisor (Generator Polynomial): ");
        String gp = input.next();

        CRC crcObject = new CRC(gp);

        int zeroCount = codewordLength - data.length();

        if (zeroCount < 0)
        {
            System.out.println("Error: Codeword length cannot be less than the data word length!");
            input.close();
            return;
        }

        String dataCRC = crcObject.getDataCrc(data, zeroCount);
        System.out.println("Generated Codeword (Data + CRC): " + dataCRC);

        System.out.println("Enter Received Data Bits (Codeword to validate): ");
        String receivedData = input.next();

        boolean v = crcObject.validate(receivedData);
        if (v)
            System.out.println("Correct Data");
        else
            System.out.println("Invalid Data");

        input.close();
    }
}