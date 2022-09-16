//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DNA Encoder
// Course:   CS 300 Spring 2022
//
// Author:   Aneesh Pandoh
// Email:    Pandoh@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////


/**
 * Creates DNA as a linked queue which can be transcribed and translated
 */
public class DNA {

  protected LinkedQueue<Character> DNA;

  protected static String[][] mRNAtoProteinMap =
    {{"UUU", "F"}, {"UUC", "F"}, {"UUA", "L"}, {"UUG", "L"}, {"UCU", "S"}, {"UCC", "S"},
      {"UCA", "S"}, {"UCG", "S"}, {"UAU", "Y"}, {"UAC", "Y"}, {"UAA", "STOP"}, {"UAG", "STOP"},
      {"UGU", "C"}, {"UGC", "C"}, {"UGA", "STOP"}, {"UGG", "W"}, {"CUU", "L"}, {"CUC", "L"},
      {"CUA", "L"}, {"CUG", "L"}, {"CCU", "P"}, {"CCC", "P"}, {"CCA", "P"}, {"CCG", "P"},
      {"CAU", "H"}, {"CAC", "H"}, {"CAA", "Q"}, {"CAG", "Q"}, {"CGU", "R"}, {"CGC", "R"},
      {"CGA", "R"}, {"CGG", "R"}, {"AUU", "I"}, {"AUC", "I"}, {"AUA", "I"}, {"AUG", "M"},
      {"ACU", "T"}, {"ACC", "T"}, {"ACA", "T"}, {"ACG", "T"}, {"AAU", "N"}, {"AAC", "N"},
      {"AAA", "K"}, {"AAG", "K"}, {"AGU", "S"}, {"AGC", "S"}, {"AGA", "R"}, {"AGG", "R"},
      {"GUU", "V"}, {"GUC", "V"}, {"GUA", "V"}, {"GUG", "V"}, {"GCU", "A"}, {"GCC", "A"},
      {"GCA", "A"}, {"GCG", "A"}, {"GAU", "D"}, {"GAC", "D"}, {"GAA", "E"}, {"GAG", "E"},
      {"GGU", "G"}, {"GGC", "G"}, {"GGA", "G"}, {"GGG", "G"}};


  /**
   * Sets the sequence of the DNA
   * @param sequence of DNA
   */
  public DNA(String sequence) {
    DNA = new LinkedQueue<>();
    for (int i = 0; i < sequence.length(); ++i) {
      DNA.enqueue(sequence.charAt(i));
    }
  }

  /**
   * Transcribes the DNA to mRNA
   * @return mRNA queue
   */
  public LinkedQueue<Character> transcribeDNA() {
    LinkedQueue<Character> mRNA = new LinkedQueue<>();
    LinkedQueue<Character> tempDNA = new LinkedQueue<>();

    while (!DNA.isEmpty()) {
      Character tempChar = DNA.dequeue();
      tempDNA.enqueue(tempChar);
      if (tempChar == 'A') {
        mRNA.enqueue('U');
      } else if (tempChar == 'T') {
        mRNA.enqueue('A');
      } else if (tempChar == 'C') {
        mRNA.enqueue('G');
      } else if (tempChar == 'G') {
        mRNA.enqueue('C');
      }
    }

    DNA = tempDNA;
    return mRNA;
  }

  /**
   * Translates mRNA to protein queue
   * @param mRNA which is going to be translated
   * @return queue of protein
   */
  public LinkedQueue<String> mRNATranslate(LinkedQueue<Character> mRNA) {
    LinkedQueue<String> proteins = new LinkedQueue<>();

    if (mRNA.isEmpty()) {
      return proteins;
    }

    int loopAmount = (mRNA.size() / 3);
    for (int i = 0; i < loopAmount; i++) {
      String codon = "";
      codon += mRNA.dequeue();
      codon += mRNA.dequeue();
      codon += mRNA.dequeue();

      String protein = findProtein(codon); //uses private method to find the corresponding protein
      if (protein.equals("STOP")) {
        break;
      }
      proteins.enqueue(protein);
    }
    return proteins;
  }

  /**
   * Private method used to find the corresponding protein given a codon
   * @param codon which will be translated to a protein
   * @return protein of corresponding codon
   */
  private String findProtein(String codon) {
    for (int i = 0; i < mRNAtoProteinMap.length; ++i) {
      if (codon.equals(mRNAtoProteinMap[i][0])) {
        return mRNAtoProteinMap[i][1];
      }
    }
    return null;
  }

  /**
   * Transcribes and translates DNA to Protein
   * @return protein queue.
   */
  public LinkedQueue<String> translateDNA() {
    return mRNATranslate(transcribeDNA());
  }

}
