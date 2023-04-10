
import java.util.Scanner;

public class SafeInput {
    //GetUserName
    /**
     *
     * @param pipe
     * @param prompt
     * @return
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {

        String retString = "";
        do
        {
            System.out.print("\n" +prompt + ": ");
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }
    //Favorite Number
    /**
     *
     * @param pipe
     * @param prompt
     * @return
     */
    public static int getInt(Scanner pipe, String prompt) {
        boolean done = false;
        int retValue = 0;
        String trash = "";

        do {
            System.out.print("\n" + prompt + ":  ");
            if (pipe.hasNextInt())
            {
                retValue = pipe.nextInt();
                pipe.nextLine();
                done = true;

            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid int not: " + trash);
            }
        } while (!done);

        return retValue;
    }
    public static double getDouble(Scanner pipe, String prompt) {

        boolean done = false;
        double retValue = 0.0;
        String trash = "";

        do {
            System.out.print("\n" + prompt + ":  ");
            if (pipe.hasNextDouble())
            {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                done = true;

            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid int not: " + trash);
            }
        } while (!done);

        return retValue;
    }

    //Date and Time of Birth

    /**
     *
     * @param pipe
     * @param prompt
     * @param lo
     * @param hi
     * @return
     */

    public static int getRangedInt(Scanner pipe, String prompt, int lo, int hi)
    {
        int result = 0;
        boolean done = false;
        String trash = "";
        do
        {

            System.out.print(prompt + "[" + lo + " - " + hi + "]: ");
            if(pipe.hasNextInt())
            {
                result = pipe.nextInt();
                pipe.nextLine();
                if(result >= lo && result <= hi)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a value in range [" + lo + " - " + hi + "]: " + result);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter an valid input [" + lo + " - " + hi + "]: " + trash);
            }


        }while(!done);

        return result;
    }

    //Check out
    public static double getRangeDouble(Scanner pipe, String prompt , double low , double high)
    {

        double retDouble = 0.0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ":  ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                if(retDouble >= low && retDouble <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]: " + retDouble);
                }

            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not: " + trash);
            }
        } while (!done);

        return retDouble;

    }

    public static boolean getYNConfirm(Scanner pipe , String prompt)
    {
        boolean retBoolean = false;
        boolean done = false;
        String addItem = "";

        do{
            System.out.print("\n" + prompt + "[Y/N]: ");
            addItem = pipe.nextLine();
            if(addItem.equalsIgnoreCase("Y"))
            {
                retBoolean = true;

                done = true;
            }
            else if (addItem.equalsIgnoreCase("N"))
            {
                retBoolean = false;
                done= true;
            }
            else
            {
                System.out.println("You must enter a Y or N not : "+ addItem);
            }

        }while (!done);
        return retBoolean;

    }

    //Reggie
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {

        String retValue = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " : " );
            retValue = pipe.nextLine();
            if (retValue.matches(regEx)) {
                done = true;
            } else {
                System.out.println("You must enter a string that matches the pattern; " + regEx);
            }
        } while (!done);
        return retValue;
    }

}


