/**
 * Представляет ячейку хранения на складе — конкретное место
 * для размещения товаров. Ячейка имеет ограниченную вместимость
 * и содержит единицы хранения (композиция).
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cell {
    // ... атрибуты ...
    private int id;
    private String code;
    private int capacity;
    private int currentLoad;
    /**
     * Создаёт новую ячейку.
     * @param id уникальный идентификатор ячейки
     * @param code код ячейки (например, "A-01-1")
     * @param capacity максимальная вместимость ячейки
     * @param currentLoad текущая загруженность ячейки
     */
    public Cell(int id, String code, int capacity, int currentLoad) {
        this.id = id;
        this.code = code;
        this.capacity = capacity;
        this.currentLoad = currentLoad;
    }
    // ... геттеры и сеттеры  ...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    private void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    private final List<StockItem> stockItems = new ArrayList<>();
    /**
     * Создаёт новую единицу хранения в этой ячейке и добавляет её
     * во внутренний список. Увеличивает текущую загруженность ячейки.
     * Реализует композицию: StockItem не может существовать без Cell.
     * @param quantity количество товара
     * @param receivedAt дата поступления
     * @param product товар, который размещается в ячейке
     * @return созданная единица хранения
     */
    public StockItem addStockItem(int quantity, Date receivedAt, Product product) {
        StockItem item = new StockItem(stockItems.size() + 1, quantity, receivedAt, product);
        stockItems.add(item);
        currentLoad += quantity;
        return item;
    }
    /**
     * Проверяет, есть ли свободное место в ячейке.
     * @return true, если текущая загруженность меньше вместимости
     */
    public boolean isAvailable() {
        return currentLoad < capacity;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", capacity=" + capacity +
                ", currentLoad=" + currentLoad +
                '}';
    }
}
