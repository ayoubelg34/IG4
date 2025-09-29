package lab2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

public class Commercial extends Employe {
    private double chiffreAffaires;
    private double FixSalary;

    public Commercial(String name, double chiffreAffaires, double FixSalary) {
        super(name);
        this.chiffreAffaires = chiffreAffaires;
        this.FixSalary = FixSalary;
    }

    public Commercial(String name) {
        super(name);
        
    }

    public double getSalary() {
        return FixSalary + (0.01 * chiffreAffaires);
    }

    public void SetSalaryInfos(double chiffreAffaires, double FixSalary){ 
        this.chiffreAffaires = chiffreAffaires;
        this.FixSalary = FixSalary;
    }

    public void enregistreToi() throws IOException {
        try {
            PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("comm.txt")));
            out1.println(super.getName() + " | " + this.FixSalary + "|" + this.chiffreAffaires);
            out1.close();
        } catch (EOFException e) {
            System.err.println("End of stream");
        }

    }

    
    public void lire() throws IOException {
        try {
            BufferedReader in1 = new BufferedReader(new FileReader("comm.txt"));
            String s;
            while ((s = in1.readLine()) != null) {
                String[] res = s.split(" \\| ");
                if (res.length == 3) {
                    String name = res[0];
                    double fixSalary = Double.parseDouble(res[1]);
                    double chiffreAffaires = Double.parseDouble(res[2]);
                    this.SetSalaryInfos(chiffreAffaires, fixSalary);
                    System.out.println("Nom: " + name + ", FixSalary: " + this.FixSalary + ", ChiffreAffaires: " + this.chiffreAffaires);
                }
            }
        } catch (EOFException e) {
            System.err.println("End of stream");
        }

    }

    public void engistreBinaire() throws IOException {
        try {
            DataOutputStream out1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.txt")));
            out1.writeUTF(super.getName());
            out1.writeDouble(this.FixSalary);
            out1.writeDouble(this.chiffreAffaires);
            out1.close();
        } catch (EOFException e) {
            System.err.println("End of stream");
        }

    }

}

