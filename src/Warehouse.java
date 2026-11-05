/**
 * Представляет физический склад — место хранения товаров.
 * Склад состоит из ячеек (агрегация): ячейки могут существовать
 * независимо от склада и быть перенесены на другой склад.
 */
import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    // ... атрибуты ...
    private int id;
    private String name;
    private String address;
    /**
     * Создаёт новый склад.
     * @param id уникальный идентификатор склада
     * @param name название склада
     * @param address физический адрес склада
     */
    public Warehouse(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    // ... геттеры и сеттеры ...
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private final List<Cell> cells = new ArrayList<>();
    /**
     * Добавляет ячейку на склад. Ячейка создаётся независимо
     * и передаётся в склад (агрегация).
     * @param cell ячейка для добавления
     */
    public void addCell(Cell cell) {
        cells.add(cell);
        System.out.println("Ячейка " + cell.getCode() + " добавлена на склад " + name);
    }
    /**
     * Вычисляет общую вместимость всех ячеек склада.
     * @return сумма вместимостей всех ячеек в единицах
     */
    public int getTotalCapacity() {
        int total = 0;
        for (Cell cell : cells) {
            total += cell.getCapacity();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
