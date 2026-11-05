/**
 * Позиция накладной — одна строка в документе.
 * Содержит количество и цену товара на момент оформления.
 * Связана с одним товаром (ассоциация).
 * Создаётся только через Document (композиция).
 */
public class DocumentItem {
    // ... атрибуты ...
    private int quantity;
    private double price;
    private final Product product;
    /**
     * Создаёт новую позицию накладной.
     * Конструктор package-private: объект создаётся только через Document.
     * @param quantity количество товара в позиции
     * @param price цена товара на момент оформления
     * @param product товар, к которому относится позиция
     */
     DocumentItem(int quantity, double price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    // ... геттеры и сеттеры  ...
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Вычисляет сумму по позиции.
     * @return произведение количества на цену
     */
    public double getSubtotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "DocumentItem{" +
                "productName='" + (product != null ? product.getName() : "Неизвестно") + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}