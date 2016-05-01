import java.util.Objects;

public final class Product {
    private final int id;
    private final String name;
    private final  String producer;
    private final  double price;

    public Product(int id, String name, String producer, double price) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
