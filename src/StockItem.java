/**
 * Представляет единицу хранения — конкретную партию товара
 * в ячейке склада. Содержит информацию о количестве и дате
 * поступления. Связан с одним товаром (ассоциация).
 */
import java.util.Date;

public class StockItem {
    // ... атрибуты ...
    private int id;
    private int quantity;
    private Date receivedAt;

    private final Product product;
    /**
     * Создаёт новую единицу хранения.
     * Конструктор package-private: объект создаётся только через Cell.
     * @param id уникальный идентификатор
     * @param quantity количество товара в партии
     * @param receivedAt дата поступления на склад
     * @param product товар, к которому относится партия
     */
    StockItem(int id, int quantity, Date receivedAt, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.receivedAt = receivedAt;
        this.product = product;
    }
    // ... геттеры и сеттеры  ...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    /**
     * Вычисляет общую стоимость партии товара.
     * @return произведение количества на цену товара
     */
    public double getTotalValue() {
        return quantity * product.getPrice();
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", receivedAt=" + receivedAt +
                '}';
    }
}