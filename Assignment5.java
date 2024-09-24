import java.util.Scanner;

public class DNALib {
    public static boolean validator(String string) {
        if(string.isEmpty()){
            return true;
        }
        if (string.length() < 3 || !string.matches("[ACTG]+")) {
            return false;
        }
        return validator(string.substring(3));
    }
    public static String reverser(String dna) {
        if (dna.length()==1) {
            return dna;
        }
        return reverser(dna.substring(1))+dna.charAt(0);
    }

    public static String inverser(String dna) {
        if (dna.isEmpty()){
            return dna;
        }
            char currentChar = dna.charAt(0);
            if (currentChar == 'A') {
                return "T"+inverser(dna.substring(1));
            } else if (currentChar == 'T') {
                return "A"+inverser(dna.substring(1));
            } else if (currentChar == 'C') {
                return "G"+inverser(dna.substring(1));
            } else if (currentChar == 'G') {
                return "C"+inverser(dna.substring(1));
            }
        return inverser(dna.substring(1));
    }



    public static String translator(String dna) {
        if(dna.length()<3){
            return dna;
        }
            String sequence = dna.substring(0,3);
            String aminoacid = "";
        switch (sequence) {
            case "GCA":
            case "GCC":
            case "GCG":
            case "GCT":
                aminoacid = "A";
                break;
            case "TGC":
            case "TGT":
                aminoacid = "C";
                break;
            case "GAC":
            case "GAT":
                aminoacid = "D";
                break;
            case "GAA":
            case "GAG":
                aminoacid = "E";
                break;
            case "TTC":
            case "TTT":
                aminoacid = "F";
                break;
            case "GGA":
            case "GGC":
            case "GGG":
            case "GGT":
                aminoacid = "G";
                break;
            case "CAC":
            case "CAT":
                aminoacid = "H";
                break;
            case "ATA":
            case "ATC":
            case "ATT":
                aminoacid = "I";
                break;
            case "AAA":
            case "AAG":
                aminoacid = "K";
                break;
            case "CTA":
            case "CTC":
            case "CTG":
            case "CTT":
            case "TTA":
            case "TTG":
                aminoacid = "L";
                break;
            case "ATG":
                aminoacid = "M";
                break;
            case "AAT":
            case "AAC":
                aminoacid = "N";
                break;
            case "CCA":
            case "CCC":
            case "CCG":
            case "CCT":
                aminoacid = "P";
                break;
            case "CAA":
            case "CAG":
                aminoacid = "Q";
                break;
            case "AGA":
            case "AGG":
            case "CGA":
            case "CGC":
            case "CGG":
            case "CGT":
                aminoacid = "R";
                break;
            case "AGC":
            case "AGT":
            case "TCA":
            case "TCC":
            case "TCG":
            case "TCT":
                aminoacid = "S";
                break;
            case "ACA":
            case "ACC":
            case "ACG":
            case "ACT":
                aminoacid = "T";
                break;
            case "GTA":
            case "GTC":
            case "GTG":
            case "GTT":
                aminoacid = "V";
                break;
            case "TGG":
                aminoacid = "W";
                break;
            case "TAC":
            case "TAT":
                aminoacid = "Y";
                break;
            case "TAA":
            case "TAG":
            case "TGA":
                aminoacid = "-";
                break;
        }

        return aminoacid+translator(dna.substring(3));
    }

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("[DNA Reverser and Translator]\nEnter a sequence: ");
            String input = scanner.nextLine();
            if (validator(input)) {
                System.out.println("Your DNA sequence reversed and translated is: \n" + translator(inverser(reverser(input))));
            } else {
                System.out.println("Your DNA sequence is not valid.");
                scanner.close();
        }
    }
}


