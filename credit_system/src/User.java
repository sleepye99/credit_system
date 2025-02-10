import java.util.Scanner;
import java.lang.Math;
public class User {
    Scanner sc = new Scanner(System.in);

    private double c;
    private boolean insurance_type;
    private double interest;
    private int[] maturity_rate_list;
    private int maturity_rate;


    public User() {
        this.c = 0.0;
        this.insurance_type = false;
        this.interest = 0.0159;
        this.maturity_rate_list = new int[]{6, 18, 24};
        this.maturity_rate=0;
    }

    public void setCredit(double c) {
        this.c = c;
    }

    public double getCredit() {
        return c;
    }

    public void setInsuranceType(boolean insurance_type) {
        this.insurance_type = insurance_type;
    }

    public boolean getInsuranceType() {
        return insurance_type;
    }

    public void setInterest() {
        if (insurance_type) {
            this.interest = 0.0129;
        }
    }

    public double getInterest() {
        return interest;
    }

    public void setMaturity_rate_list() {
        if (getInsuranceType()) {
            this.maturity_rate_list = new int[]{12, 24, 36};
        }
    }

    public int[] getMaturity_rate_list() {
        return maturity_rate_list;
    }
    public void setMaturity_rate(int maturity_rate){
        this.maturity_rate=maturity_rate;
    }
    public int getMaturity_rate(){
        return maturity_rate;
    }

    public void takeLoan(User user) {
        boolean key = true;
        while (key) {
            System.out.print("Enter the amount you want to loan");
            sc = new Scanner(System.in);
            if (sc.hasNextDouble()) {
                double credit = sc.nextDouble();
                user.setCredit(credit);
                System.out.println("You will take " + user.getCredit() + " TL credit.");
                key = false;
            } else {
                System.out.println("Not valid input try again.");

            }
        }
    }

    public void checkInsurance(User user) {
        System.out.println("Do you have insurance ? enter 'y' if you have an insurance, enter 'n' if you do not have an insurance.");
        sc = new Scanner(System.in);
        String answer = sc.next();
        if (answer.equals("y")) {
            user.setInsuranceType(true);
            user.setInterest();
            user.setMaturity_rate_list();
            if(user.getCredit()<=250){
                System.out.println("You don't have to pay, since your credit is less than 250 TL your insurance has covered your payment.");
                System.exit(1);
            }
        }

    }

    public void chooseMaturity(User user) {
        boolean checker = false;
        boolean key = true;
        while (key) {
            for (int i = 0; i < user.getMaturity_rate_list().length; i++) {
                System.out.println("You can have " + user.getMaturity_rate_list()[i] + " months of maturity.");
            }
            System.out.println("Enter the amount of maturity you want");
            sc = new Scanner(System.in);
            int m = sc.nextInt();
            for (int mat : user.getMaturity_rate_list()) {
                if (m == mat) {
                    checker = true;

                }
            }
            if (checker) {
                System.out.println("You choose to have " + m + " months of maturity.");
                user.setMaturity_rate(m);
                key = false;
            } else {
                System.out.println("It is not possible to have " + m + "months of maturity. Try Again.");

            }
        }
    }



    public double calculatePayment(User user){
        double monthlyPayment = 0;
        double mi = user.getInterest()/12;
        int mrate = user.getMaturity_rate();
        double cred = user.getCredit();
        if(user.getInsuranceType()){

            cred = cred-250;

        }




        monthlyPayment = (cred*mi)/(1-(Math.pow(1+mi,-mrate)));

        return monthlyPayment;


    }
    public void f(User user){
        takeLoan(user);
        checkInsurance(user);
        chooseMaturity(user);
        System.out.println("You initially take"+ user.getCredit()+ " TL credit.");
        System.out.println("Your monthly payment is "+ user.calculatePayment(user));
        System.out.println("You will pay " + user.calculatePayment(user)*user.getMaturity_rate() +" in total");

    }
}



