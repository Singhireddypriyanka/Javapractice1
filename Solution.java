public class Solution {
  public static void main(String[] argv) {
    String[] song1 = {"F4", "B4", "C5"};
    String[] song2 = {"C3", "E3", "G3", "C4", "E4", "G4", "C5"};
    String[] song3 = {"B4", "F5", "B5" };
    String[] song4 = {"B4", "E4", "G4", "G4", "A4", "B4", "E4", 
                      "B4", "E4", "G4", "G4", "A4", "C5", "B4", 
                      "E5", "G4", "G4", "A4", "B4", "C5", "D5", 
                      "C5", "B4", "C5", "E5", "D5", "C5", "C5", 
                      "B4", "B4", "E5", "E4", "G4", "G4", "A4", 
                      "B4", "B4", "B4", "C5", "E5", "A5", "E5", 
                      "C5", "A4", "E5", "D5", "C5", "B4"};
    String[] song5 = {"F4"};

    System.out.println(singable(song1, "F4", "C5"));  // true
    System.out.println(singable(song1, "A4", "C5"));  // false
    System.out.println(singable(song2, "B2", "C5"));  // true
    System.out.println(singable(song2, "C3", "B4"));  // false
    System.out.println(singable(song3, "B4", "B5"));  // true
    System.out.println(singable(song3, "B4", "C5"));  // false
    System.out.println(singable(song4, "D4", "A5"));  // true
    System.out.println(singable(song4, "D4", "G5"));  // false
    System.out.println(singable(song4, "D4", "C6"));  // true
    System.out.println(singable(song4, "F4", "C6"));  // false
    System.out.println(singable(song5, "D4", "E4"));  // false
  }

  public static boolean singable(String[] song, String lowest, String highest) {
    int lowestPitch = getPitchValue(lowest.charAt(0));
    int lowestOctave = getOctave(lowest.charAt(1));

    int highestPitch = getPitchValue(highest.charAt(0));
    int highestOctave = getOctave(highest.charAt(1));

    for (String note : song) {
      int notePitch = getPitchValue(note.charAt(0));
      int noteOctave = getOctave(note.charAt(1));

      if (noteOctave < lowestOctave || noteOctave > highestOctave) {
        return false;
      }

      if (noteOctave == lowestOctave && notePitch < lowestPitch) {
        return false;
      }

      if (noteOctave == highestOctave && notePitch > highestPitch) {
        return false;
      }
    }

    return true;
  }

  private static int getPitchValue(char pitch) {
    switch (pitch) {
      case 'C':
        return 0;
      case 'D':
        return 1;
      case 'E':
        return 2;
      case 'F':
        return 3;
      case 'G':
        return 4;
      case 'A':
        return 5;
      case 'B':
        return 6;
      default:
        throw new IllegalArgumentException("Invalid pitch: " + pitch);
    }
  }

  private static int getOctave(char octave) {
    return octave - '0';
  }
}
