package hw06.travel;

public class TravelGroup {
    private boolean isOpen;
    private Human [] members;

    private Mountain mountain;

    public TravelGroup(boolean isOpen, Human members[], Mountain mountain){
        if(mountain == null){
            throw new IllegalArgumentException("mountain не null");
        }
        this.isOpen = isOpen;
        this.members = members;
        this.mountain = mountain;

    }
    public TravelGroup(boolean isOpen,Mountain mountain){
        if(mountain == null){
            throw new IllegalArgumentException("mountain не null");
        }
        this.isOpen = isOpen;
        this.mountain = mountain;
    }
    public void addMember(Human member){
        if(member == null){
            throw new IllegalArgumentException("member не null");
        }
        if(isOpen){
            Human[] newArray;
            if(this.members != null){
                newArray = new Human[this.members.length + 1];
                for (int i = 0; i < this.members.length; i++) {
                    newArray [i] = this.members[i];
                }
                newArray [this.members.length] = member;
            }else{
                newArray = new Human[1];
                newArray [0] = member;
            }
            this.members = newArray;

        }
    }
    /* для отладки добавлю метод вывода информации о группе */
    public void printGroupInfo(){
        if(isOpen){
            System.out.println("Приём в группу открыт");
        }else{
            System.out.println("Приём в группу закрыт");
        }
        System.out.println("Группа собирается посетить гору: " + this.mountain.getName());
        System.out.println("Список участников группы:");
        for (int i = 0; i < members.length; i++) {
            System.out.print('\t');
            System.out.print(members[i].getName());
            System.out.print('\t');
            System.out.print("из");
            System.out.print('\t');
            System.out.println(members[i].getAddress());
        }


    }

}
