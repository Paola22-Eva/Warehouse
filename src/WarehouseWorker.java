/**
 * Представляет кладовщика — сотрудника склада, который выполняет
 * операции с документами (приёмка, отгрузка).
 * Связан с документами через ассоциацию: работает с ними,
 * но не владеет ими.
 */
public class WarehouseWorker {
    // ... атрибуты ...
    private int id;
    private String name;
    private String position;

    /**
     * Создаёт нового кладовщика.
     * @param id уникальный идентификатор сотрудника
     * @param name имя сотрудника
     * @param position должность
     */
    public WarehouseWorker(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Обрабатывает документ: проверяет его статус,
     * валидность и при необходимости подтверждает.
     * @param document документ для обработки
     */
    public void processDocument(Document document) {
        System.out.println("Кладовщик " + name + " обрабатывает документ №" + document.getNumber());

        // Проверяем, валиден ли документ
        if (document.getItems().isEmpty()) {
            System.out.println("Ошибка: документ пустой, невозможно обработать");
            return;
        }

        // Проверяем текущий статус
        if (document.getStatus() == DocumentStatus.DRAFT) {
            document.confirm();
            System.out.println("Документ №" + document.getNumber() + " подтвержден. Статус: " + document.getStatus());
            System.out.println("Общая сумма: " + document.getTotalAmount());
        } else if (document.getStatus() == DocumentStatus.CONFIRMED) {
            System.out.println("Документ №" + document.getNumber() + " уже подтвержден, обработка завершена");
        } else {
            System.out.println("Документ №" + document.getNumber() + " имеет статус " + document.getStatus() + ", обработка невозможна");
        }
    }

    @Override
    public String toString() {
        return "WarehouseWorker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}