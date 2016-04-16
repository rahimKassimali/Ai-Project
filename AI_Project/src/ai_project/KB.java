package ai_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class KB {

    public boolean match(String[] flights, String s) // for Consistency check ,, NO two flights at same src at same time
    {
        for (int i = 0; i < flights.length; i++) {
            for (int j = i + 1; j < flights.length; j++) {

                if (flights[i].equals(flights[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean consistent(String[][] variables, int total)// Consistency Check
    {
        for (int i = 0; i < total; i++) {
            if (!variables[i][2].equals("X") && !variables[i][3].equals("X")) {
                if (variables[i][2].equals(variables[i][3])) {
                    return false;
                }
            }

        }
        Stack<String> karachi_s = new Stack<>();
        Stack<String> karachi_d = new Stack<>();

        Stack<String> islamabad_s = new Stack<>();
        Stack<String> islamabad_d = new Stack<>();

        Stack<String> peshawar_s = new Stack<>();
        Stack<String> peshawar_d = new Stack<>();

        Stack<String> lahore_s = new Stack<>();
        Stack<String> lahore_d = new Stack<>();

        Stack<String> quetta_s = new Stack<>();
        Stack<String> quetta_d = new Stack<>();

        //  islamabad karachi lahore peshawar quetta 
        for (int i = 0; i < total; i++) {
            if (!variables[i][2].equals("X") && !variables[i][3].equals("X")) {
                if (variables[i][0].equals("karachi")) {
                    karachi_s.push(variables[i][2]);
                }
                if (variables[i][1].equals("karachi")) {
                    karachi_d.push(variables[i][3]);
                }

                if (variables[i][0].equals("islamabad")) {
                    islamabad_s.push(variables[i][2]);
                }
                if (variables[i][1].equals("islamabad")) {
                    islamabad_d.push(variables[i][3]);
                }
//
                if (variables[i][0].equals("lahore")) {
                    lahore_s.push(variables[i][2]);
                }
                if (variables[i][1].equals("lahore")) {
                    lahore_d.push(variables[i][3]);
                }
//
                if (variables[i][0].equals("peshawar")) {
                    peshawar_s.push(variables[i][2]);
                }
                if (variables[i][1].equals("peshawar")) {
                    peshawar_d.push(variables[i][3]);
                }
//
                if (variables[i][0].equals("quetta")) {
                    quetta_s.push(variables[i][2]);
                }
                if (variables[i][1].equals("quetta")) {
                    quetta_d.push(variables[i][3]);
                }
            }
        }

        String k_s[] = equal(karachi_s);
        String k_d[] = equal(karachi_d);

        String i_s[] = equal(islamabad_s);
        String i_d[] = equal(islamabad_d);

        String p_s[] = equal(peshawar_s);
        String p_d[] = equal(peshawar_d);

        String l_s[] = equal(lahore_s);
        String l_d[] = equal(lahore_d);

        String q_s[] = equal(quetta_s);
        String q_d[] = equal(quetta_d);

        // print_r(k_s);
        // print_r(k_d);
        if (match(k_s, "ks") || match(k_d, "kd")
                || match(i_s, "is") || match(i_d, "id")
                || match(p_s, "ps") || match(p_d, "pd")
                || match(l_s, "ls") || match(l_d, "ld")
                || match(q_s, "qs") || match(q_d, "qd")) {
            return false;
        }

        return true;

    }

    public String[][] start(int total) // initialization purpose
    {

        String[][] variables = new String[total][4];
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < 4; j++) {
                variables[i][j] = "X";
            }
        }
        return variables;
    }

    public static String[][] start_variable(String[][] variables) throws IOException// initialization purpose
    {
        int m = 0, n = 0;
        int itr;
        String[][] input = input_KB();

        for (int i = 0; i < 20; i++) {
            for (int j = 2; j < 3; j++) {
                itr = Integer.parseInt(input[i][j]);
                while (itr > 0) {
                    variables[m][0] = input[i][0];
                    variables[m][1] = input[i][1];
                    m++;
                    itr--;
                }
            }
        }
        return variables;
    }

    public boolean complete(String[][] variables, int total) {
        boolean cond = true;
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < 4; j++) {
                if (variables[i][j].equals("X")) {
                    cond = false;
                }
            }
        }
        return cond;
    }

    public static String[][] data() throws FileNotFoundException, IOException // Knowledge Base , SRC , DEST , Time_To_Reach
    {
        String[][] space = new String[20][3];

        String csvFile = "source-destination.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        br = new BufferedReader(new FileReader(csvFile));
        line = br.readLine();
        int counter = 0;
        while ((line = br.readLine()) != null) {
            String[] country = line.split(cvsSplitBy);
            space[counter][0] = country[1];
            space[counter][1] = country[2];
            space[counter][2] = country[3];
            counter++;
        }
                return space;
    }

    public static String[][] input_KB() throws FileNotFoundException, IOException // User Input
    {
        int i = 15;
        String a = Integer.toString(i);

        int j = 10;
        String b = Integer.toString(j);

        String[][] space = new String[25][4];
        
        String csvFile = "planes_amount.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        br = new BufferedReader(new FileReader(csvFile));
        line = br.readLine();
        int counter = 0;
        while ((line = br.readLine()) != null) {
            String[] country = line.split(cvsSplitBy);
            space[counter][0] = country[1];
            space[counter][1] = country[2];
            space[counter][2] = country[3];
            
            System.out.println(space[counter][0]+"  "+space[counter][1]+"  "+space[counter][2]);
            counter++;
        }
        System.out.println(counter);
        /*
        space[0][0] = "karachi";
        space[0][1] = "islamabad";
        space[0][2] = "15";
        space[2][0] = "karachi";
        space[2][1] = "lahore";
        space[2][2] = "15";
        space[4][0] = "karachi";
        space[4][1] = "peshawar";
        space[4][2] = "15";
        space[6][0] = "karachi";
        space[6][1] = "quetta";
        space[6][2] = "10";

        space[1][0] = "islamabad";
        space[1][1] = "karachi";
        space[1][2] = "15";
        space[11][0] = "islamabad";
        space[11][1] = "lahore";
        space[11][2] = "15";
        space[15][0] = "islamabad";
        space[15][1] = "peshawar";
        space[15][2] = "15";
        space[18][0] = "islamabad";
        space[18][1] = "quetta";
        space[18][2] = "10";

        space[3][0] = "lahore";
        space[3][1] = "karachi";
        space[3][2] = "15";
        space[8][0] = "lahore";
        space[8][1] = "peshawar";
        space[8][2] = "15";
        space[10][0] = "lahore";
        space[10][1] = "islamabad";
        space[10][2] = "15";
        space[12][0] = "lahore";
        space[12][1] = "quetta";
        space[12][2] = "10";

        space[5][0] = "peshawar";
        space[5][1] = "karachi";
        space[5][2] = "15";
        space[9][0] = "peshawar";
        space[9][1] = "lahore";
        space[9][2] = "15";
        space[14][0] = "peshawar";
        space[14][1] = "islamabad";
        space[14][2] = "15";
        space[16][0] = "peshawar";
        space[16][1] = "quetta";
        space[16][2] = "10";

        space[7][0] = "quetta";
        space[7][1] = "karachi";
        space[7][2] = "10";
        space[13][0] = "quetta";
        space[13][1] = "lahore";
        space[13][2] = "10";
        space[17][0] = "quetta";
        space[17][1] = "peshawar";
        space[17][2] = "10";
        space[19][0] = "quetta";
        space[19][1] = "islamabad";
        space[19][2] = "10";
*/
        pre_process_input(space);

        return space;
    }

    public static void pre_process_input(String[][] space) // Input Validation , Is Crossing Maxomum Limit or Not
    {
        int a = 15;
        int b = 10;
        if (Integer.parseInt(space[0][2]) > a
                || Integer.parseInt(space[2][2]) > a
                || Integer.parseInt(space[4][2]) > a
                || Integer.parseInt(space[6][2]) > b
                || Integer.parseInt(space[1][2]) > a
                || Integer.parseInt(space[11][2]) > a
                || Integer.parseInt(space[15][2]) > a
                || Integer.parseInt(space[18][2]) > b
                || Integer.parseInt(space[3][2]) > a
                || Integer.parseInt(space[8][2]) > a
                || Integer.parseInt(space[10][2]) > a
                || Integer.parseInt(space[12][2]) > b
                || Integer.parseInt(space[5][2]) > a
                || Integer.parseInt(space[9][2]) > a
                || Integer.parseInt(space[14][2]) > a
                || Integer.parseInt(space[16][2]) > b
                || Integer.parseInt(space[7][2]) > b
                || Integer.parseInt(space[13][2]) > b
                || Integer.parseInt(space[17][2]) > b
                || Integer.parseInt(space[19][2]) > b) {
            System.exit(1);
        }

    }

    public static int input_KB_total() throws IOException // FInding Total Number of Flights in Input
    {
        int total = 0;
        String[][] space = input_KB();
        for (int i = 0; i < 20; i++) {
            for (int j = 2; j < 3; j++) {
                total += Integer.parseInt(space[i][j]);
            }
        }
        return total;
    }

    public static void show_final(String[][] p, int total) // printing Variables
    {

        //  p=BubbleSort(p,total);
        System.out.print("Source-----Destination-----Start_time-----End_time");
        System.out.println();
        int k = 1;
        for (int i = 0; i < total; i++) {
            System.out.print(k++ + "--");
            System.out.print("--" + p[i][0] + "--");
            System.out.print("--" + p[i][1] + "--");
            System.out.print("--" + p[i][2] + "--");
            System.out.print("--" + p[i][3] + "--");
            System.out.println();
        }
    }

    public static void Pre_process(String[][] p, int total) throws IOException// Variables Output Conversion ,, to time :  25->15m, 75->45m , 50->30m
    {
        //  p=BubbleSort(p,total);
        FileWriter write = new FileWriter("output.csv");

        write.append("S.No");
        write.append(',');
        write.append("Source");
        write.append(',');
        write.append("Destination");
        write.append(',');
        write.append("Start time");
        write.append(',');
        write.append("End time");
        write.append('\n');
        int k = 1;
        for (int i = 0; i < total; i++) {
            write.append("" + k++);
            write.append(',');
            write.append(p[i][0]);
            write.append(',');
            write.append(p[i][1]);
            write.append(',');

            double x, y, x1, x2;
            String a = p[i][2];
            double val = Double.parseDouble(a);
            String[] arr = String.valueOf(val).split("\\.");
            x = Double.parseDouble(arr[1]);
            if (x == 25) {
                x = 15;
            }
            if (x == 75) {
                x = 45;
            }
            if (x == 50) {
                x = 30;
            }
            if (x == 0) {
                x = 0;
            }

            String b = p[i][3];
            double val1 = Double.parseDouble(b);
            String[] arr1 = String.valueOf(val1).split("\\.");
            y = Double.parseDouble(arr1[1]);

            if (y == 25) {
                y = 15;
            }
            if (y == 75) {
                y = 45;
            }
            if (y == 50) {
                y = 30;
            }
            if (y == 0) {
                y = 0;
            }
            write.append("<" + arr[0] + ":" + (int) (x) + ">  ");
            write.append(',');
            write.append("<" + arr1[0] + ":" + (int) (y) + ">");
            write.append('\n');
        }
        write.flush();
        write.close();

    }

    public static double fetch_time(String a, String b) throws IOException// Getting Time to reach from Src to Dest
    {
        double L = 0;
        String[][] space = data();
        for (int i = 0; i < 20; i++) {
            if (space[i][0].equals(a) && space[i][1].equals(b)) {
                L = Double.parseDouble(space[i][2]);
                break;
            }
        }

        return L;
    }

    double[] get_domain(String[][] variables, int index, String src, String dest, double range1)// Finding Domain for Src/Destination
    {
        double arr[] = new double[2];
        arr[0] = 0;
        arr[1] = 0;
        double start = 0, jump = 0, range = range1;
        for (jump = 0; jump < 1;) {
            start = jump;
            while (start + range <= 23) {
                if (avail_src(variables, start, src) && avail_dest(variables, (start + range), dest)) {
                    arr[0] = start;
                    arr[1] = start + range;
                    return arr;
                }
                start += .25;
            }
            jump += 0.25;
        }
        return arr;
    }
//
    
    
    public boolean avail_src(String[][] variables, double start, String src)// Is source airport available at given time
    {
        String a = Double.toString(start);

        for (int i = 0; i < variables.length; i++) {
            if (variables[i][2].equals(a) && variables[i][0].equals(src)) {

                return false;
            }
        }

        return true;
    }

    public boolean avail_dest(String[][] variables, double start, String dest)// Is destination airport available at given time
    {
        String a = Double.toString(start);

        for (int i = 0; i < variables.length; i++) {
            if (variables[i][3].equals(a) && variables[i][1].equals(dest)) {
                return false;
            }
        }
        return true;
    }

    public boolean cant_find_src(String[][] variables, double start, int ind, String src) // finding source with given time
    {
        String a = Double.toString(start);

        for (int i = 0; i < ind; i++) {
            if (variables[i][2].equals(a) && variables[i][0].equals(src)) {

                return true;
            }
        }

        return false;
    }

    public boolean cant_find_dest(String[][] variables, double end, int ind, String dest)// finding destination with given time
    {
        String b = Double.toString(end);

        for (int i = 0; i < ind; i++) {
            if (variables[i][3].equals(b) && variables[i][1].equals(dest)) {
                return true;
            }
        }
        return false;
    }

    public String[][] back_track(String[][] variables, int index, double timer, double time, String src, String dest, double not_this_start, double not_this_end) {
        double[] a = new double[2];
        if (index > 0) {
            int ind = index - 1;
            a = get_domain(variables, timer, time, ind, src, dest, not_this_start, not_this_end);
            variables[ind][2] = Double.toString(a[0]);
            variables[ind][3] = Double.toString(a[1]);
        }
        return variables;
    }

    public double[] get_domain(String[][] variables, double starter_timer, double range, int index, String src, String dest, double n_s, double n_e) {
        double arr[] = new double[2];
        arr[0] = 0;
        arr[1] = 0;
        double jump = 0;
        double start = starter_timer;

        for (jump = 0; jump < 1;) {
            start += jump;
            while (start + range <= 23.75) {
                double j = start + range;
                if (start != n_s && j != n_e && !cant_find_src(variables, start, index, src) && !cant_find_dest(variables, start + range, index, dest)) {
                    arr[0] = start;
                    arr[1] = start + range;
                    return arr;
                }
                start += .25;
            }
            start = starter_timer;
            jump += 0.25;
        }
        return arr;
    }

    public static String[][] swap(String[][] variables, int a, int b)//Swap Two Variables half (only source and destination)
    {
        String a1 = variables[a][0], a2 = variables[a][1], a3 = variables[a][2], a4 = variables[a][3];
        String b1 = variables[b][0], b2 = variables[b][1], b3 = variables[b][2], b4 = variables[b][3];
        variables[b][0] = a1;
        variables[b][1] = a2;
        variables[b][2] = "X";
        variables[b][3] = "X";
        variables[a][0] = b1;
        variables[a][1] = b2;
        variables[a][2] = "X";
        variables[a][3] = "X";
        return variables;
    }

    public static String[][] swap1(String[][] variables, int a, int b)// Swap Two Variables Completely
    {
        String a1 = variables[a][0], a2 = variables[a][1], a3 = variables[a][2], a4 = variables[a][3];
        String b1 = variables[b][0], b2 = variables[b][1], b3 = variables[b][2], b4 = variables[b][3];
        variables[b][0] = a1;
        variables[b][1] = a2;
        variables[b][2] = a3;
        variables[b][3] = a4;
        variables[a][0] = b1;
        variables[a][1] = b2;
        variables[a][2] = b3;
        variables[a][3] = b4;
        return variables;
    }

    public void print_r(String[] flights) // Printng Array
    {
        int i = 0;
        for (i = 0; i < flights.length; i++) {
            System.out.print(flights[i] + " ");
            if (i % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void print_s(Stack<String> dest1) // Printing Stack
    {
        Stack<String> dest = dest1;
        dest.toString();
        String[] src = new String[dest.size()];
        int j = dest.size();
        for (int i = 0; i < j; i++) {
            src[i] = dest.pop();
            System.out.print(src[i] + " ");
            if (i % 8 == 0) {
                System.out.println();
            }
        }
    }

    public static String[][] BubbleSort(String[][] num, int total) // Sorting ,, for Final Result showing
    {
        int j;
        boolean run = true;
        String temp;

        while (run) {
            run = false;
            for (j = 0; j < total - 1; j++) {
                if (Double.parseDouble(num[j][2]) > Double.parseDouble(num[j + 1][2])) {

                    temp = num[j][2];                //swap elements
                    num[j][2] = num[j + 1][2];
                    num[j + 1][2] = temp;
                    run = true;              //shows a swap occurred  

                }
            }
        }
        return num;
    }

    public String[] equal(Stack<String> dest1) // Stack to Array Conversion
    {
        String src[] = new String[dest1.size()];
        int j = dest1.size();
        for (int i = 0; i < j; i++) {
            src[i] = dest1.elementAt(i);
        }
        return src;
    }
}




/*
         space[0][0] = "karachi";
         space[0][1] = "islamabad";
         space[0][2] = "2";
         space[1][0] = "islamabad";
         space[1][1] = "karachi";
         space[1][2] = "2";

         space[2][0] = "karachi";
         space[2][1] = "lahore";
         space[2][2] = "2";
         space[3][0] = "lahore";
         space[3][1] = "karachi";
         space[3][2] = "2";

         space[4][0] = "karachi";
         space[4][1] = "peshawar";
         space[4][2] = "2";
         space[5][0] = "peshawar";
         space[5][1] = "karachi";
         space[5][2] = "2";

         space[6][0] = "karachi";
         space[6][1] = "quetta";
         space[6][2] = "2";
         space[7][0] = "quetta";
         space[7][1] = "karachi";
         space[7][2] = "2";

         space[8][0] = "lahore";
         space[8][1] = "peshawar";
         space[8][2] = "1";
         space[9][0] = "peshawar";
         space[9][1] = "lahore";
         space[9][2] = "1";

         space[10][0] = "lahore";
         space[10][1] = "islamabad";
         space[10][2] = "1";
         space[11][0] = "islamabad";
         space[11][1] = "lahore";
         space[11][2] = "1";

         space[12][0] = "lahore";
         space[12][1] = "quetta";
         space[12][2] = "2";
         space[13][0] = "quetta";
         space[13][1] = "lahore";
         space[13][2] = "2";

         space[14][0] = "peshawar";
         space[14][1] = "islamabad";
         space[14][2] = "2";
         space[15][0] = "islamabad";
         space[15][1] = "peshawar";
         space[15][2] = "2";

         space[16][0] = "peshawar";
         space[16][1] = "quetta";
         space[16][2] = "2";
         space[17][0] = "quetta";
         space[17][1] = "peshawar";
         space[17][2] = "2";

         space[18][0] = "islamabad";
         space[18][1] = "quetta";
         space[18][2] = "1";
         space[19][0] = "quetta";
         space[19][1] = "islamabad";
         space[19][2] = "1";
     
         for(int i=0;i<20;i++){
         for(int j=0;j<3;j++){
         if(space[i][j].equals(space_check[i][j]))
         System.out.println("match");
         else
         System.out.println("not match");
         }
         }*/
