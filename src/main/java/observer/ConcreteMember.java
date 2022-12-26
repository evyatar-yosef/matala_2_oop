package observer;

/**
 * this class Describes the customers who receive updates.
 * UndoableStringBuilder and send all updates to it in real time.
 * @ authors : evyatar yosef , itamar gueta.
 * @ version : 0.99.
 */
public class ConcreteMember implements Member
{
    UndoableStringBuilder ustb;

    /**
     * this method update this specific member according to the GroupAdmin.
     * @param usb the GroupAdmin situation.
     */
    public void update(UndoableStringBuilder usb)
    {
        ustb = usb;
    }

    /**
     * this method return the string of the specific member.
     * @return string
     */
    public String toString()
    {
        return ustb.toString();
    }
}
