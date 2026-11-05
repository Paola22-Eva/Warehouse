/**
 * Представляет товар — запись в каталоге склада.
 * Содержит базовую информацию о товаре: название, артикул, цену.
 * Не зависит от ячеек и единиц хранения (однонаправленная ассоциация).
 */
public class Product {
    // ... атрибуты ...
    private int id;
    private String name;
    private String sku;
    private double price;

    /**
     * Создаёт новый товар.
     * @param id уникальный идентификатор товара
     * @param name название товара
     * @param sku артикул (SKU) товара
     * @param price цена товара
     */
    public Product(int id, String name, String sku, double price) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    // ... геттеры и сеттеры  ...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private int stock = 0; // Количество товара на всем складе

    /**
     * Проверяет, есть ли товар в наличии на складе.
     * @return true, если остаток больше нуля
     */
    public boolean isInStock() {
        return stock > 0;
    }

    /**
     * Возвращает текущий остаток товара на складе.
     * @return количество единиц товара
     */
    public int getStock() {
        return stock;
    }

    /**
     * Изменяет остаток товара на складе.
     * Положительное значение увеличивает остаток (поступление),
     * отрицательное — уменьшает (отгрузка).
     * @param quantity изменение остатка (может быть отрицательным)
     */
    public void updateStock(int quantity) {
        if (this.stock + quantity < 0) {
            System.out.println("Ошибка: попытка уйти в минус на складе для товара " + this.name);
            return; // Или throw new IllegalArgumentException("Недостаточно товара");
        }
        this.stock += quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                '}';
    }
}
