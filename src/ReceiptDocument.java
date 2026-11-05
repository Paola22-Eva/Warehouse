/**
 * Накладная поступления товара на склад.
 * Наследует Document и добавляет информацию о поставщике.
 * При добавлении товара увеличивает его остаток на складе.
 */

import java.util.Date;

public class ReceiptDocument extends Document {
    // ... атрибуты ...
    private String supplier;

    /**
     * Создаёт новую накладную поступления.
     * @param id уникальный идентификатор
     * @param number номер накладной
     * @param createdAt дата создания
     * @param status начальный статус
     * @param supplier название поставщика
     */
    public ReceiptDocument(int id, String number, Date createdAt, DocumentStatus status, String supplier) {
        super(id, number, createdAt, status);
        this.supplier = supplier;
    }

    // ... геттеры и сеттеры  ...
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * Добавляет товар в накладную поступления и увеличивает
     * его остаток на складе. Проверяет, что количество больше нуля.
     * @param product добавляемый товар
     * @param qty количество товара
     */
    public void addProduct(Product product, int qty) {
        if (qty <= 0) {
            System.out.println("Ошибка: количество должно быть больше нуля");
            return;
        }

        DocumentItem item = new DocumentItem(qty, product.getPrice(), product);
        addItem(item);
        product.updateStock(qty);

        System.out.println("Принят товар: " + product.getName() +
                ", количество: " + qty);
    }

    @Override
    public String toString() {
        return "ReceiptDocument{" +
                "supplier='" + supplier + '\'' +
                ", number='" + getNumber() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}