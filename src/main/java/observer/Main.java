package observer;

public class Main
{
    public static void main(String[] args)
    {
       GroupAdmin ga = new GroupAdmin();
        GroupAdmin ga2 = new GroupAdmin();

        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();
        ConcreteMember cm3 = new ConcreteMember();

        ga.register(cm1);
        ga.register(cm2);
        ga.register(cm3);

        ga.append("");
        ga2.append("null");
        System.out.println(ga2.toString());


//        System.out.println(ga.observers.get(0));
//
//        System.out.println(cm1);
//        System.out.println(cm2);
//        System.out.println(cm3);
    }
}
