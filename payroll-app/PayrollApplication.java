import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Scanner;
// import java.util.Locale;
class PayrollApplication {
    static Locale locale;
    static String properCase(String name)
    {
        String fullName = "";
        String names[] = name.split(" ");
        for(int i=0; i<names.length;i++)
        {

            String n = String.valueOf(names[i].charAt(0)).toUpperCase()+names[i].substring(1).toLowerCase();
            fullName = fullName+n+" ";

            // String val = names[i];
            // char firstletter = val.charAt(0);
            // String firstCapLetter = String.valueOf(firstletter).toUpperCase();
            // String remName = names[i].substring(1) ;
            // String n = firstCapLetter + remName;
            // fullName = fullName+n+" ";
        }
        return fullName;
    }
   static  String currencyFormat(double val){
         NumberFormat obj = NumberFormat.getInstance(locale);
          return obj.format(val);
    }
    static String dateFormat(){
        Date date = new Date();
        // System.out.println(date);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,locale);
        return df.format(date);

    }
    //SRP
//    @SuppressWarnings("deprecation")
// @SuppressWarnings("deprecation")
static  void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 1 for English");
        System.out.println("हिंदी के लिए 2 दबाएँ");
        int choice = scanner.nextInt();
       
        if(choice==1){
             locale = new Locale("hi","IN");
        }
        else if(choice==2){
             locale = new Locale("hi","IN");
        }
        else
        {
            locale = new Locale("en","US");
            System.out.println("Wrong Choice... Taking English Only");
        }
        System.out.println("Enter the Id:");
        int id = scanner.nextInt();

        scanner.nextLine(); //eat
        System.out.println("Enter the Name:");
        String name = scanner.nextLine();
        System.out.println("Enter the Basic Salary:");
        double basicSalary = scanner.nextDouble();
        compute(name , basicSalary);
        scanner.close();
    }
   static  void compute(String name , double basicSalary){
        double hra = basicSalary*0.50;
        double ta = basicSalary*0.40;
        double ma = basicSalary*0.25;
        double da = basicSalary*0.20;
        double pf = basicSalary*0.05;
        double gs = basicSalary + hra + da + ta + ma;
        double tax = computeTax(gs);
        double ns = gs - pf - tax;
        print(name,gs,hra,da,ma,ta,pf,ns,tax);
    }
    static double computeTax(double gs)
    {
        double tax = 0.0;
        double annual = gs * 12;
        if(annual>500000 && annual<700000)
        {
            return (annual * 0.10)/12;
        }
        else if(annual>700000 && annual<900000)
        {
            return (annual * 0.20)/12;
        }
        else if(annual>900000)
        {
            return (annual * 0.30)/12;
        }
        return 0.0;
    }
   static  void print(String name,double gs,double hra,double pf, double da,double ma, double ta,double ns,double tax){
        // dateFormat();
        System.out.println("Today: "+dateFormat());
        System.out.println("Emp Name: "+properCase(name));
        System.out.println("Earning Allowance \t Deduction ");
        System.out.println("Hra " +currencyFormat(hra)+"\t\t PF " +currencyFormat(pf));
        System.out.println("DA " +currencyFormat(da)+"\t\t Tax " +currencyFormat(tax));
        System.out.println("MA " +currencyFormat(ma));
        System.out.println("TA " +currencyFormat(ta));
        System.out.println("GS " +currencyFormat(gs));

        System.out.println("NS " +currencyFormat(ns));
    }
    public static void main(String[] args) {

        // Scanner scanner = new Scanner(System.in);
       
        
        input();
        // System.out.println("Deduction");
        // System.out.println("PF " +pf);
       
    }
}