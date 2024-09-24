import java.io.*;
import java.util.Scanner;
class StateComplaintException extends Exception {
    public StateComplaintException(String s1) {
        super(s1);
    }
}

class Complaint {
    private String causeOfAction;
    private String plaintiffCitizenship;;
    private String defendandCitizenship;
    private String originalStateOfFilling;
    double amountInControversy;
    int id;
    static int nextId=1;

    public double getAmountInControversy() {
        return amountInControversy;
    }

    public String getCauseOfAction() {
        return causeOfAction;
    }

    public String getDefendandCitizenship() {
        return defendandCitizenship;
    }

    public String getOriginalStateOfFilling() {
        return originalStateOfFilling;
    }

    public String getPlaintiffCitizenship() {
        return plaintiffCitizenship;
    }

    public int getId() {
        return id;
    }
    public Complaint(String s1, String s2, String s3, String s4,double d1){
        s1 = this.causeOfAction;
        s2 = this.defendandCitizenship;
        s3 = this.originalStateOfFilling;
        s4 = this.plaintiffCitizenship;
        d1 = this.amountInControversy;
        id = nextId++;
    }
}
class Driver{
        static void processComplaint(Complaint c)throws StateComplaintException{
            String causeOfAction = c.getCauseOfAction();
            String plaintiffCitizenship = c.getPlaintiffCitizenship();
            String defendantCitizenship = c.getDefendandCitizenship();
            double amountInControversy = c.getAmountInControversy();
            String originalStateOfFiling = c.getOriginalStateOfFilling();
            if(causeOfAction.equals("Equal Protection Challenge")||causeOfAction.equals("Title IX Workplace Discrimination")||causeOfAction.equals("Prisoner Civil Rights Claim")||causeOfAction.equals("Fair Labor Standard Act Claim")
            ){return;}
            if(plaintiffCitizenship.equals(defendantCitizenship)){
                throw new StateComplaintException("Lack of Diversity");
            }
            if(amountInControversy<=75000){
                throw new StateComplaintException("Amount in controverst less than or equal to $75000");
            }
            if(defendantCitizenship.equals(originalStateOfFiling)){
                throw new StateComplaintException("No prejudice through diversity");
            }
        }
}
public class Assignment6 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FileWriter fileWriter = new FileWriter("accepted.txt");
        System.out.println("[Federal Court Complaint Processor]");
        System.out.println("Enter file name to process: ");
        String filename = scanner.nextLine();
        scanner.close();
        try {
            FileWriter acceptedFile = new FileWriter("accepted.txt");
            FileWriter remandedFile = new FileWriter("remanded.txt");
            int acceptedCount = 0;
            int remandedCount = 0;
            File input = new File(filename);
            Scanner fileview = new Scanner(input);
            while (fileview.hasNextLine()) {
                String filechecker = fileview.nextLine();
                String[] parts = filechecker.split(",");
                if (parts.length == 5) {
                    String causeOfAction = parts[0];
                    double amountInControversy = Double.parseDouble(parts[1]);
                    String plaintiffCitizenship = parts[2];
                    String defendantCitizenship = parts[3];
                    String originalStateOfFilling = parts[4];
                    Complaint complaint = new Complaint(causeOfAction, originalStateOfFilling, plaintiffCitizenship, defendantCitizenship, amountInControversy);
                    try {
                        Driver.processComplaint(complaint);
                        acceptedFile.write("Case ID: " + complaint.getId() + "\n");
                        acceptedFile.write("Cause of action: " + complaint.getCauseOfAction() + "\n");
                        acceptedFile.write("Amount in Controversy: $" + complaint.getAmountInControversy() + "\n");
                        acceptedFile.write("Plaintiff’s Citizenship: " + complaint.getPlaintiffCitizenship() + "\n");
                        acceptedFile.write("Defendant’s Citizenship: " + complaint.getDefendandCitizenship() + "\n");
                        acceptedFile.write("Originally filled in: " + complaint.getOriginalStateOfFilling() + "\n");
                        acceptedFile.write("==============================\n");
                        acceptedCount++;
                    } catch (StateComplaintException e) {
                        remandedFile.write("Case ID: " + complaint.getId() + "\n");
                        remandedFile.write("Cause of action: " + complaint.getCauseOfAction() + "\n");
                        remandedFile.write("Amount in Controversy: $" + complaint.getAmountInControversy() + "\n");
                        remandedFile.write("Plaintiff’s Citizenship: " + complaint.getPlaintiffCitizenship() + "\n");
                        remandedFile.write("Defendant’s Citizenship: " + complaint.getDefendandCitizenship() + "\n");
                        remandedFile.write("Originally filled in: " + complaint.getOriginalStateOfFilling() + "\n");
                        remandedFile.write("Reason for remand: " + e.getMessage() + "\n");
                        remandedFile.write("==============================\n");
                        remandedCount++;
                    }
                }
            }

            fileview.close();
            acceptedFile.close();
            remandedFile.close();

            System.out.println("Processing completed.");
            System.out.println("Accepted cases: " + acceptedCount);
            System.out.println("Remanded cases: " + remandedCount);

        } catch (FileNotFoundException e) {
            System.out.println("No file with name \"" + filename + "\"");
            System.out.println("Shutting down...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
