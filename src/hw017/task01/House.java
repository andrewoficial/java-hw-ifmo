package hw017.task01;

public class House {
    public House act(Action<House> action) {
        action.action(this);
        return this;
    }
}