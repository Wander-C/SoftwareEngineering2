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
 *  Name:    Alphabetizer.java
 * 
 *  Purpose: Sorts circular shifts alphabetically
 * 
 *  Created: 23 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Sorts circular shifts alphabetically
 * </file>
*/



/*
 * $Log$
*/

/**
 *  An object of the Alphabetizer class sorts all lines, that it gets
 *  from CircularShifter. Methods to access sorted lines are provided.
 *  @author  dhelic
 *  @version $Id$
*/

public class Alphabetizer{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

/**
 * Array holding sorted indices of lines
 *
 */

  private int sorted_[];

/**
 * CircularShifter that provides lines
 *
 */

  private CircularShifter shifter_;

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
 * Sorts all lines from the shifter.
 * @param shifter the source of lines
 */

public void alpha(CircularShifter shifter) {
  this.shifter_ = shifter;
  int count = shifter_.getLineCount();

  // 初始化索引数组
  sorted_ = new int[count];
  for (int i = 0; i < count; i++) {
    sorted_[i] = i;
  }

  // 构建最大堆
  for (int i = (count / 2) - 1; i >= 0; i--) {
    siftDown(i, count - 1);
  }

  // 逐步提取堆顶元素完成排序
  for (int i = count - 1; i > 0; i--) {
    // 交换堆顶和当前元素
    int temp = sorted_[0];
    sorted_[0] = sorted_[i];
    sorted_[i] = temp;

    // 调整剩余堆
    siftDown(0, i - 1);
  }
}

//----------------------------------------------------------------------
/**
 * This method builds and reconstucts the heap for the heap sort algorithm.
 * @param root heap root
 * @param bottom heap bottom
 */

private void siftDown(int root, int bottom) {
  int maxChild = root * 2 + 1; // 假设左子节点是较大子节点

  while (maxChild <= bottom) {
    // 比较左右子节点
    if (maxChild < bottom) {
      String left = shifter_.getLineAsString(sorted_[maxChild]);
      String right = shifter_.getLineAsString(sorted_[maxChild + 1]);
      if (left.compareTo(right) < 0) {
        maxChild++;
      }
    }

    // 比较父节点与最大子节点
    String rootStr = shifter_.getLineAsString(sorted_[root]);
    String maxChildStr = shifter_.getLineAsString(sorted_[maxChild]);
    if (rootStr.compareTo(maxChildStr) < 0) {
      // 交换父节点和子节点
      int temp = sorted_[root];
      sorted_[root] = sorted_[maxChild];
      sorted_[maxChild] = temp;

      // 继续向下调整
      root = maxChild;
      maxChild = root * 2 + 1;
    } else {
      break;
    }
  }
}

//----------------------------------------------------------------------
/**
 * Gets the line from the specified position.
 * String array representing the line is returned.
 * @param line line index
 * @return String[]
 * @see #getLineAsString
 */

  public String[] getLine(int line){
    return shifter_.getLine(sorted_[line]);
  }

//----------------------------------------------------------------------
/**
 * Gets the line from the specified position.
 * String representing the line is returned.
 * @param line line index
 * @return String[]
 * @see #getLine
 */

  public String getLineAsString(int line){
    return shifter_.getLineAsString(sorted_[line]);
  }

//----------------------------------------------------------------------
/**
 * Gets the number of lines.
 * @return int
 */

  public int getLineCount(){
    return shifter_.getLineCount();
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
