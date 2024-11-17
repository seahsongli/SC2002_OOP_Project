package hospitalManagement;

import java.util.Scanner;

public class Pharmacist extends Staff {
    Scanner sc = new Scanner(System.in);

    public Pharmacist() {
        super("", "", "", "", "", "", 0); // super() has to be at the first statement of constructor.
        super.updateRole("Pharmacist");

        System.out.println("Enter pharmacist name: ");
        String pharmacistName = sc.nextLine();
        super.updateName(pharmacistName);

        System.out.println("Enter pharmacist gender: ");
        String pharmacistGender = sc.nextLine();
        super.updateGender(pharmacistGender);

        System.out.println("Enter pharmacist age: ");
        int pharmacistAge = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        super.updateAge(pharmacistAge);
    }
}