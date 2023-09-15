package vn.edu.iuh.fit.entities;

public enum Status {
    Active(1),
    Deactive(0),
    Delete(-1);

    private final int value;

    Status(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
