/**
 * Накладная отгрузки товара со склада.
 * Наследует Document и добавляет информацию о покупателе.
 * При отгрузке уменьшает остаток товара на складе.
 */
import java.util.Date;

public class ShipmentDocument extends Document {
    // ... атрибуты ...
    private String customer;

    /**
     * Создаёт новую накладную отгрузки.
     * @param id уникальный идентификатор
     * @param number номер накладной
     * @param createdAt дата создания
     * @param status начальный статус
     * @param customer название покупателя
     */
    public ShipmentDocument(int id, String number, Date createdAt, DocumentStatus status, String customer) {
        super(id, number, createdAt, status);
        this.customer = customer;
    }

    // ... геттеры и сеттеры  ...
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Отгружает товар со склада, создавая позицию в накладной
     * и уменьшая остаток товара. Проверяет наличие товара
     * и достаточность количества перед отгрузкой.
     * @param product отгружаемый товар
     * @param qty количество товара
     */
    public void removeProduct(Product product, int qty) {
        if (!product.isInStock()) {
            System.out.println("Ошибка: товар " + product.getName() + " отсутствует");
            return;
        }

        if (product.getStock() < qty) {
            System.out.println("Ошибка: недостаточно " + product.getName());
            return;
        }

        DocumentItem item = new DocumentItem(qty, product.getPrice(), product);
        addItem(item);
        product.updateStock(-qty);

        System.out.println("Отгружен товар: " + product.getName() +
                ", количество: " + qty);
    }

    @Override
    public String toString() {
        return "ShipmentDocument{" +
                "customer='" + customer + '\'' +
                ", number='" + getNumber() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}