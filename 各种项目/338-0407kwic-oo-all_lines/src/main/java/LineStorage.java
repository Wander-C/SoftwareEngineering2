// -*- Java -*-
/*
 * <copyright>
 *
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 *
 * </copyright>
 *
 * <file>
 *
 *  Name:    LineStorage.java
 *
 *  Purpose: LineStorage holds all input lines and provides a public interface to manipulate the lines.
 *
 *  Created: 19 Sep 2002
 *
 *  $Id$
 *
 *  Description:
 *
 * </file>
*/



/*
 * $Log$
*/

import java.util.ArrayList;

/**
 *  LineStorage holds a number of lines and provides a number of public methods
 *  to manipulate the lines. A line is defined as a set of words, and a word consists of a number of
 *  characters. Methods defined by the LineStorage class allow objects of other classes to:
 *  <ul>
 *  <li>set, read and delete a character from a particular word in a particular line
 *  <li>add a new character to a particular word in a particular line
 *  <li>obtain the number of characters in a particular word in a particular line
 *  <li>set, read and delete a word from a particular line
 *  <li>add a new word to a particular line
 *  <li>add an empty word to a particular line
 *  <li>obtain words count in a particular line
 *  <li>set, read and delete a particular line
 *  <li>add a new line
 *  <li>add an empty line
 *  <li>obtain lines count
 *  </ul>
 *  @author  dhelic
 *  @version $Id$
*/

public class LineStorage{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

/**
 * ArrayList holding all lines. Each line itself is represeneted as an
 * Arraylist object holding all words from that line. The ArrayList class is a
 * standard Java Collection class, which  implements the typical buffer
 * functionality, i.e., it keeps its objects in an array of a fix capacity.
 * When the current capacity is exceeded, ArrayList object resizes its array
 * automatically, and copies the elements of the old array into the new one.
 */

  private ArrayList lines_ = new ArrayList();

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * This method sets a new character on the specified index of
 * a particular word in a particular line.
 */

  public void setChar(char c, int position, int word, int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    StringBuilder current_str = new StringBuilder();
    current_str.append(current_.get(position));
    current_str.replace(position, position+1, String.valueOf(c));
    current_.set(word, current_str.toString());
    lines_.set(line, current_);
  }

//----------------------------------------------------------------------
/**
 * Gets the character from the specified position in the specified word
 * in a particular line.
 */

  public char getChar(int position, int word, int line){
    ArrayList current_line = (ArrayList) lines_.get(line);
    return  current_line.get(word).toString().charAt(position);
  }

//----------------------------------------------------------------------
/**
 * Adds a character at the end of the specified word in a particular line.
 */

  public void addChar(char c, int word, int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    String add= current_.get(word).toString();
    add=add+c;
    current_.set(word, add);
    lines_.set(line, current_);
  }

//----------------------------------------------------------------------
/**
 * Deletes the character from the specified position in the specified word
 * in a particular line.
 * @param position character index in the word
 * @param word word index in the line
 * @param line line index
 * @see #setChar
 * @see #getChar
 * @see #addChar
 */

  public void deleteChar(int position, int word, int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    StringBuilder stringBuilder= new StringBuilder();
    stringBuilder.append(current_.get(position).toString());
    stringBuilder.deleteCharAt(position);
    current_.set(word, stringBuilder.toString());
    lines_.set(line, current_);
  }

//----------------------------------------------------------------------
/**
 * Gets the number of characters in this particular word.
 * @param word word index in the line
 * @param line line index
 * @return int
 */

  public int getCharCount(int word, int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    return current_.get(word).toString().length();
  }

//----------------------------------------------------------------------
/**
 * This method sets a new word on the specified index of a particular line.
 * Character array is taken as an argument for the word.
 * @param chars new word
 * @param word word index in the line
 * @param line line index
 * @see #getWord
 * @see #addWord
 * @see #addEmptyWord
 * @see #deleteWord
 */

  public void setWord(char[] chars, int word, int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    current_.add(word, chars);
    lines_.set(line, current_);
  }

//----------------------------------------------------------------------
/**
 * This method sets a new word on the specified index of a particular line.
 * String is taken as an argument for the word.
 * @param chars new word
 * @param word word index in the line
 * @param line line index
 * @see #getWord
 * @see #addWord
 * @see #addEmptyWord
 * @see #deleteWord
 */

  public void setWord(String chars, int word, int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    current_.set(word, chars);
    lines_.set(line, current_);
  }

//----------------------------------------------------------------------
/**
 * Gets the word from the specified position in a particular line
 * String representing the word is returned.
 */

  public String getWord(int word, int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    return current_.get(word).toString();
  }

//----------------------------------------------------------------------
/**
 * Adds a word at the end of the specified line.
 * The method takes a character array as an argument.
 */

  public void addWord(char[] chars, int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    current_.add(chars.toString());
    lines_.set(line, current_);
  }

//----------------------------------------------------------------------
/**
 * Adds a word at the end of the specified line.
 * The method takes a string as an argument.
 */

  public void addWord(String chars, int line){
    ArrayList current_line = (ArrayList) lines_.get(line);
    current_line.add(chars);
    lines_.set(line, current_line);
  }

//----------------------------------------------------------------------
/**
 * Adds an empty word at the end of the specified line.
 * @param line line index
 */

  public void addEmptyWord(int line){
    ArrayList current_line = (ArrayList) lines_.get(line);
    current_line.add(new String());
    lines_.set(line, current_line);
  }

//----------------------------------------------------------------------
/**
 * Deletes the word from the specified position in a particular line.
 * @param word word index in the line
 */

  public void deleteWord(int word, int line){
    ArrayList  current_ =(ArrayList) lines_.get(line);
    current_.remove(word);
    lines_.set(line, current_);
  }

//----------------------------------------------------------------------
/**
 * Gets the number of words in this particular line.
 * @param line line index
 */

  public int getWordCount(int line){
    ArrayList current_ =(ArrayList) lines_.get(line);
    return current_.size();
  }

//----------------------------------------------------------------------
/**
 * This method sets a new line on the specified index.
 * This method takes two dimensional character array as an argument
 * for the line.
 */

  public void setLine(char[][] words, int line){
    ArrayList current_ =new ArrayList();
    for(int i = 0; i < words.length; i++){
      current_.add(words[i].toString());
    }
    lines_.set(line, current_);
  }

//----------------------------------------------------------------------
/**
 * This method sets a new line on the specified index.
 * This method takes a string array as argument
 */

  public void setLine(String[] words, int line){
    lines_.set(line, words);
  }

//----------------------------------------------------------------------
/**
 * Gets the line from the specified position.
 * String array representing the line is returned.
 */

  public String[] getLine(int line) {
    ArrayList current_ =(ArrayList) lines_.get(line);
    String[] words = new String[current_.size()];
    for(int i = 0; i < current_.size(); i++){
      words[i] = (String) current_.get(i);
    }
    return words;
  }

//----------------------------------------------------------------------
/**
 * Gets the line from the specified position.
 * A single String representing the line is returned.
 */

  public String getLineAsString(int line){
    String[] current_ =(String[]) lines_.get(line);
    StringBuilder current_str = new StringBuilder();
    for(int i = 0; i < current_.length; i++){
      current_str.append(current_[i]).append(" ");
    }
    current_str.deleteCharAt(current_str.length()-1);
    return current_str.toString();
  }

//----------------------------------------------------------------------
/**
 * Adds a line at the end of the line array.
 * Two dimensional array is the argument for the new line
 */

  public void addLine(char[][] words){
    ArrayList current_ =new ArrayList();
    for(int i = 0; i < words.length; i++){
      current_.add(words[i].toString());
    }
    lines_.add(current_);
  }

//----------------------------------------------------------------------
/**
 * Adds a line at the end of the line array.
 * String array is the argument for the new line
 */

  public void addLine(String[] words){
    lines_.add(words);
  }

//----------------------------------------------------------------------
/**
 * Adds an empty line at the end of the lines array.
 */

  public void addEmptyLine(){
    lines_.add(new ArrayList());
  }

//----------------------------------------------------------------------
/**
 * Deletes the line from the specified position.
 */

  public void deleteLine(int line){
    lines_.remove(line);
  }

//----------------------------------------------------------------------
/**
 * Gets the number of lines.
 * @return int
 */

  public int getLineCount(){
    return lines_.size();
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
