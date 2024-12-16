public class Director {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Режиссер: " + fullName;
    }

}
