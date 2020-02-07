package tema3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *  @author Victor Tudose
 *  This class executes the homework logic and is ran from the Makfile
 */
public class Main {

    public static void main(String[] args)
    {
        String option=args[0];
        String input_file_path=args[1];
        String aux_file_path=args[2];
        String output_file_path=args[3];

        Team.initTeams();

        File input_file=new File(input_file_path);
        File output_file= new File(output_file_path);

        try {
            Team.loadAll(new Scanner(input_file));
        }
        catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }

        if(option.equals("inscriere"))
        {
            try {
                Team.showAll(new PrintStream(output_file));
            }
            catch(FileNotFoundException e){ System.out.println("FILE(S) NOT FOUND");}
        }
        else
        {
            try {
                Competition cmp=new Competition(new Scanner(new File(aux_file_path)),new PrintStream(output_file));
                cmp.compete();
            }
            catch(FileNotFoundException e){ System.out.println("FILE(S) NOT FOUND");}
        }

    }
}
