package observer;

import java.util.Stack;

/*
Use the class you've implemented in previous assignment
 */
public class UndoableStringBuilder
{
    protected StringBuilder stb;
    protected Stack<String> stack;

    public UndoableStringBuilder()
    {
        stb = new StringBuilder();
        stack = new Stack();
    }

    /**
     * Appends the specified string received from user to this character sequence.
     * @param  str - the string we add to the StringBuilder
     * @return this class
     */
    public UndoableStringBuilder append(String str)
    {
        stb.append(str);
        stack.push(stb.toString());
        return this;
    }

    /**
     * Removes the characters in a substring of this sequence
     * @param start - The substring begins at the specified start index
     * @param end - The substring end at the specified end -1 index
     * @return this class
     */
    public UndoableStringBuilder delete(int start, int end)
    {
        try
        {
            stb.delete(start, end);
            stack.push(stb.toString());
            return this;
        }
        catch (Exception IndexOutOfBoundsException)
        {
            System.err.println("start or end  index are wrong");
            return null;
        }


    }

    /**
     * Inserts the string into this character sequence.
     * @param offset - the insert start from the offset index
     * @param str - the string the method received
     * @return this class
     */
    public UndoableStringBuilder insert(int offset, String str)
    {   try
    {
        stb.insert(offset , str);
        stack.push(stb.toString());
        return this;
    }
    catch (Exception IndexOutOfBoundsException)
    {
        System.err.println("offset index is wrong");
        return null;
    }
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String.
     * @param start - The substring begins at the specified start
     * @param end - extends to the character at index end - 1 or to the end of the sequence if
     * no such character exists.
     * @param str the string that replace the substring.
     * @return
     */
    public UndoableStringBuilder replace(int start,int end, String str)
    {
        try
        {
            stb.replace(start,end,str);
            stack.push(stb.toString());
            return this;
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println("start or end index are wrong");
            return null;
        }
        catch( NullPointerException e)
        {
            System.err.println("cant use replace with null");
            return null;

        }
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the
     * sequence.
     * @return this class
     */
    public UndoableStringBuilder reverse()
    {
        try
        {
            stb.reverse();
            stack.push(stb.toString());
            return this;
        }
        catch(NullPointerException e)
        {
            System.err.println("no reverse");
            return null;
        }


    }

    /**
     * this method cancels the latest operation and return the previous StringBuilder
     */
    public void undo ()
    {
        if(stack.isEmpty()) return;

        stack.pop();

        if(stack.isEmpty())
        {
            stb.delete(0,stb.length());
            return;
        }
        stb.delete(0,stb.length());
        stb.insert(0,stack.peek().toString());

    }

    /**
     * this method return the string of the StringBuilder
     * @return string
     */
    public String toString()
    {
        if(stack.empty()) return "";
        return stack.peek().toString();
    }
}
