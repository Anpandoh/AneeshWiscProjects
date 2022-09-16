///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           
// Course:          CS200 Winter 2021
//
// Author:          Aneesh Pandoh
// Email:           pandoh@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Description
// Examples:
// Jane Doe; helped me with for loop in reverse method
// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html; 
//         counting for loop
// John Doe; I helped with switch statement in main method.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

public class Candidate {
    private String name = "";
    private String party = "";
    private String office = "";

    private boolean incumbence;
    private int contributors;
    private double dollars;

    Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        incumbence = false;
        contributors = 0;
        dollars = 0;
    }

    Candidate(String name, String party, String office, boolean incumbence){
        this.name = name;
        this.party = party;
        this.office = office;
        this.incumbence = incumbence;
        contributors = 0;
        dollars = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setParty(String party) {
        this.party = party;
    }

    public String getParty() {
        return party;
    }



    public void setOffice(String office) {
        this.office = office;
    }

    public String getOffice() {
        return office;
    }


    public boolean getIncumbence() {
        return incumbence;
    }
    public int getNumContributors() {
        return contributors;
    }
    public double getAmountDollars() {
        return dollars;
    }

    public boolean acceptContributions(double amount){
        if (amount > 0){
            dollars += amount;
            contributors += 1;
        }
        else {
            return false;
        }
        return true;
    }

    public double averageContribution(){
        return (dollars/contributors);
    }

    public void setIncumbence(boolean incumbence){
        this.incumbence = incumbence;
    }

}
