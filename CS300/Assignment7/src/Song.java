//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SongPlayerProject
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
 * Creates a song with a title, artist and duration
 */
public class Song {
  private String songName; // name or title of the song
  private String artist; // artist of the song
  private String duration; // duration of the song

  /**
   * Constructs song with given name, artist and duration
   * @param songName is the name of the song
   * @param artist is the song's author
   * @param duration is how long the song is
   * @throws IllegalArgumentException if duration is not a valid time
   */
  public Song(String songName, String artist, String duration) throws IllegalArgumentException {
    if (songName == null || songName.isBlank()) {
      throw new IllegalArgumentException("No song name entered");
    }
    if (artist == null || artist.isBlank()) {
      throw new IllegalArgumentException("No artist entered");
    }
    if (duration == null || duration.isBlank()) {
      throw new IllegalArgumentException("No duration entered");
    }
    try {
      int colonLocation = duration.indexOf(":");
      Integer mm = Integer.valueOf(duration.substring(0, colonLocation));
      Integer ss = Integer.valueOf(duration.substring(colonLocation + 1));

      if (mm > 59 || mm < 0 || ss > 59 || ss < 0) {
        throw new IllegalArgumentException("Duration is incorrect");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Duration is incorrect");
    }

    this.songName = songName;
    this.artist = artist;
    this.duration = duration;
  }

  /**
   * Getter for song name
   * @return song name
   */
  public String getSongName() {
    return songName;
  }

  /**
   * Getter for artist name
   * @return artist name
   */
  public String getArtist() {
    return artist;
  }

  /**
   * Duration for artist name
   * @return duration name
   */
  public String getDuration() {
    return duration;
  }

  /**
   * Checks if song is equal to another song
   * @param other is the other song being compared
   * @return true if song is the same, false otherwise
   */
  @Override public boolean equals(Object other) {
    if (!(other instanceof Song)) {
      return false;
    }
    if (((Song) other).getSongName().equals(songName) && ((Song) other).getArtist()
      .equals(artist)) {
      return true;
    }

    return false;
  }

  /**
   * Turns the song into a string
   * @return string version of song
   */
  @Override public String toString() {
    return songName + "---" + artist + "---" + duration;
  }



}
