public class Transaction {
    private int id;
    private float value;
    private int destination;
    private int origin;

    private int approved;

    public Transaction(int id, float value, int destination, int origin, int approved) {
        this.id = id;
        this.value = value;
        this.destination = destination;
        this.origin = origin;
        this.approved = approved;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", value=" + value +
                ", destination=" + destination +
                ", origin=" + origin +
                ", approved=" + approved +
                '}';
    }
}