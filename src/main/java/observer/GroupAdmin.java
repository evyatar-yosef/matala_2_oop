package observer;

import java.util.ArrayList;
/**
 * this class implements observer pattern Organize a group of recipients of updates on the status of
 * UndoableStringBuilder and send all updates to it in real time.
 * @ authors : evyatar yosef , itamar gueta.
 * @ version : 0.99.
 */


public class GroupAdmin implements Sender
{
    UndoableStringBuilder ustb = new UndoableStringBuilder(); //situation database
    ArrayList<Member> observers = new ArrayList<Member>(); //Customer Database

    /**
     * this method add a new member to the coustumer databasse.
     * @param obj - The customer we would like to add.
     */
    @Override
    public void register(Member obj)
    {
        obj.update(ustb);
        this.observers.add(obj);
    }

    /**
     * this method remova a member from the coustumer databasse.
     * @param obj - The member we would like to removev.
     */
    @Override
    public void unregister(Member obj)
    {
       this.observers.remove(obj);
    }

    /**
     *this method use to insert a string in a specific place at the UndoableStringBuilder.
     * @param offset - The index from which we want to add the string
     * @param obj - the string we want to insert.
     */
    @Override
    public void insert(int offset, String obj)
    {
        this.ustb.insert(offset,obj);
        notify_All();
    }

    /**
     * this method used to add a new string to the UndoableStringBuilder.
     * @param obj - the string we want to append
     */
    @Override
    public void append(String obj)
    {
        this.ustb.append(obj);
        notify_All();
    }

    /**
     * this method use for delet some part of the UndoableStringBuilder.
     * @param start - The index from which we want to start deleting.
     * @param end - The index up to which we want to delete.
     */
    @Override
    public void delete(int start, int end)
    {
        this.ustb.delete(start,end);
        notify_All();
    }

    /**
     * this method cancels the latest operation and return the previous situation.
     */
    @Override
    public void undo()
    {
        this.ustb.undo();
        notify_All();
    }

    /**
     * this method used In order to notify all users when a change is made
     */
    public void notify_All()
    {
        for (Member m : observers)
        {
            m.update(ustb);
        }
    }

    /**
     * this method return the string of the UndoableStringBuilder
     * @return
     */
    public String toString()
    {
        return ustb.toString();
    }
}
