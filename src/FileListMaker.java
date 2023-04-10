import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileListMaker {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        ArrayList<String> arrList = new ArrayList<>();

        String menuPrompt = " A : Add \n D : Delete\n V : View\n O : Open\n C : Clear\n S : Save\n Q : Quit\n Select From Menu Option";
        String cmd = "";
        String fileName = "";
        boolean done = false;
        boolean deleteOrSave = false;


        do {

            cmd = SafeInput.getRegExString(in, menuPrompt, "[AaDdVvOoCcSspQq]");
            switch (cmd) {
                case "A":
                case "a":
                    addToList(in, arrList);
                    break;
                case "D":
                case "d":
                    deleteFromList(in, arrList);
                    break;
                case "V":
                case "v":
                    printList(arrList);

                    break;

                case "O":
                case "o":
                    openFIle(in, arrList,fileName,deleteOrSave);
                    deleteOrSave = true;
                    break;
                case "S":
                case "s":
                    saveFile(arrList, fileName);
                    break;
                case "C":
                case "c":
                    clearList(arrList);
                    break;

                case "Q":
                case "q":
                    if (SafeInput.getYNConfirm(in, "Are you sure")) {
                        if (deleteOrSave) {
                            saveFile(arrList,fileName);
                        }
                        done = true;
                        System.out.printf("Program Exiting....");
                    } else {
                        System.out.println(" \nBack to Menu Options ");
                    }
                    break;
            }

        } while (!done);
    }

    public static void addToList(Scanner in, ArrayList arrList) {
        String addItem = "";
        addItem = SafeInput.getNonZeroLenString(in, "What do you like to add to the list");
        arrList.add(addItem);
    }

    public static void deleteFromList(Scanner in, ArrayList arrList) {
        int deleteItem;
        deleteItem = SafeInput.getRangedInt(in, "What item do you want to delete", 1, arrList.size());
        arrList.remove(deleteItem - 1);
        System.out.println(deleteItem + " is removed! Save the file");
    }

    public static void printList(ArrayList arrList) {
        if (arrList.isEmpty()) {
            System.out.println("No list Found!.");
        } else {
            System.out.println("Your List:");
            for (int i = 0; i < arrList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, arrList.get(i));
            }
        }
    }

    public static String openFIle(Scanner in, ArrayList arrList,String fileName, boolean deleteOrSave) {
        if(deleteOrSave) {
            String prompt = "Opening New file will delete current list! Are You Sure ";
            if (SafeInput.getYNConfirm(in, prompt)) {
                arrList.clear();


            } else {
                saveFile(arrList, fileName);
                System.out.printf("Your file is saved! open new file\n");
            }
        }



        JFileChooser chooser = new JFileChooser();
        Scanner selectFile;
        String rec = "";



            Path file = new File(System.getProperty("user.dir")).toPath();
            file = file.resolve("src");
            chooser.setCurrentDirectory(file.toFile());

            try {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile().toPath();
                    selectFile = new Scanner(file);
                    System.out.println("\nOpening File: " + file.getFileName());
                    while (selectFile.hasNextLine()) {
                        rec = selectFile.nextLine();
                        arrList.add(rec);
                    }
                    selectFile.close();
                } else {
                    System.out.println("You must select a file! Returning to menu...");
                }
            } catch (IOException e) {
                System.out.println("IOException Error");
            }

            return file.toFile().toString();
        }






    public static void saveFile(ArrayList arrList, String fileName) {

        PrintWriter listSave;
        Path file = new File(System.getProperty("user.dir")).toPath();
        if (fileName.equals("")) {
            file = file.resolve("src\\list.txt");
        } else {
            file = file.resolve(fileName);
        }

        try {
            listSave = new PrintWriter(file.toString());
            for (int i = 0; i < arrList.size(); i++) {
                listSave.println(arrList.get(i));
            }
            listSave.close();
            System.out.printf("File \"%s\" saved!\n", file.getFileName());
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
    }
    public static void clearList(ArrayList arrList)
    {

        arrList.clear();
        System.out.println("Your list is cleared!! Save the file to clear all the file.");

    }
}