import java.util.Scanner;

class TeaBox {
    private String teaType;
    private TeaBox nextTea;

    public TeaBox(String teaType, TeaBox nextTea) {
        this.teaType = teaType;
        this.nextTea = nextTea;
    }

    public String getTeaType() {
        return teaType;
    }

    public TeaBox getNextTea() {
        return nextTea;
    }
}

class TheDrum {
    private TeaBox nextTeaBox;

    public TheDrum(int jasmine, int earlGrey, int lemon) {
        for (int i = 0; i < jasmine; i++) {
            nextTeaBox = new TeaBox("Jasmine", nextTeaBox);
        }
        for (int i = 0; i < earlGrey; i++) {
            nextTeaBox = new TeaBox("Earl Grey", nextTeaBox);
        }
        for (int i = 0; i < lemon; i++) {
            nextTeaBox = new TeaBox("Lemon", nextTeaBox);
        }
    }

    public TeaBox getNextTeaBox() {
        if (nextTeaBox == null) {
            return null;
        }
        TeaBox currentTeaBox = nextTeaBox;
        nextTeaBox = nextTeaBox.getNextTea();
        return currentTeaBox;
    }
}

class Employee extends Thread {
    private TheDrum pallet;
    private int totalJasmine;
    private int totalEarlGrey;
    private int totalLemon;
    private int id;
    private static int nextId = 0;

    public Employee(TheDrum pallet) {
        this.pallet = pallet;
        this.totalJasmine = 0;
        this.totalEarlGrey = 0;
        this.totalLemon = 0;
        this.id = nextId++;
    }

    public int totalTea() {
        return totalJasmine + totalEarlGrey + totalLemon;
    }

    @Override
    public void run() {
        TeaBox teaBox;
        while ((teaBox = pallet.getNextTeaBox()) != null) {
            String teaType = teaBox.getTeaType();
            switch (teaType) {
                case "Jasmine":
                    totalJasmine++;
                    break;
                case "Earl Grey":
                    totalEarlGrey++;
                    break;
                case "Lemon":
                    totalLemon++;
                    break;
            }
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Employee " + id + " has unloaded " +
                totalJasmine + " boxes of Jasmine Tea, " +
                totalEarlGrey + " boxes of Earl Grey Tea, and " +
                totalLemon + " boxes of Lemon Tea.";
    }
}

public class Assignment7 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[The GatlingTea Factory]");
        System.out.print("How many boxes of Jasmine Tea would you like to order? ");
        int jasmine = scanner.nextInt();
        System.out.print("How many boxes of Earl Grey Tea would you like to order? ");
        int earlGrey = scanner.nextInt();
        System.out.print("How many boxes of Lemon Tea would you like to order? ");
        int lemon = scanner.nextInt();

        TheDrum theDrum = new TheDrum(jasmine, earlGrey, lemon);

        System.out.println("The Drum™ has been created and shipped.");

        System.out.print("How many employees will help unload? ");
        int numEmployees = scanner.nextInt();

        Employee[] employees = new Employee[numEmployees];
        for (int i = 0; i < numEmployees; i++) {
            employees[i] = new Employee(theDrum);
            employees[i].start();
        }

        for (Employee employee : employees) {
            employee.join();
        }

        int totalBoxes = 0;
        for (Employee employee : employees) {
            totalBoxes += employee.totalTea();
        }

        System.out.println("A total of " + totalBoxes + " boxes of tea were stocked.");
    }
}
