package ai_project;

import java.io.IOException;

public class AI_Project {

    public static void main(String[] args) throws IOException {

        KB k = new KB(); // Knowledge Base Class

        int total = KB.input_KB_total(); // total variables/flights to schedule
        String[][] variables = k.start(total); //initialize the variables
        variables = KB.start_variable(variables);//adjust variable regarding Input // assign Source/Destination Airports and TIME=="X"
//  SRC DEST "X"  "X"
        int ind = 0; // variable index 
        String src, dest; // Source/Destination Airports
        double time;  // for Source/Destination Airport -->  Time To Reach
        double a[]; // Start time and End Time Assignment

        while (!k.complete(variables, total)) // Util Assignment is not complete
        {
            while (!variables[ind][2].equals("X")) // Find An Unassigned Variable
            {
                ind++;
                if (ind == total) {
                    ind = 0;
                }
            }
            src = variables[ind][0]; // Get Source
            dest = variables[ind][1]; // Get Destination
            time = KB.fetch_time(src, dest); // Get Time to reach from source to destination

            a = new double[2];
            if (ind > 0) 
            {
                a = k.get_domain(variables, ind, src, dest, time);     // gettting domain values [start and end time]
            } else {
                a[0] = 0;
                a[1] = time;
            }

            if (a[0] != a[1]) // Consistent assignment and Domain has capacity to assign variables
            {
                variables[ind][2] = Double.toString(a[0]); // start_time
                variables[ind][3] = Double.toString(a[1]); //end_time

                ind++;
                if (ind == total) {
                    ind = 0;
                }
            }
            else // Cant assign value to domain ,,
            {
                variables[ind][2] = Double.toString(-1); // start_time
                variables[ind][3] = Double.toString(-1); //end_time
                ind++;
                if (ind == total) {
                    ind = 0;
                }
            }
        }

        if (!k.consistent(variables, total)) // At Last , Consistency Check
        {
            System.out.println("Not consistent");
        } else {
            KB.Pre_process(variables, total); // Showing With Timings , Conversion
            System.out.println("Consistent");
        }
    }
}
